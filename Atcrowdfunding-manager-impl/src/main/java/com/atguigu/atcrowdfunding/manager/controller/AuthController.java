package com.atguigu.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.ProjectTag;
import com.atguigu.atcrowdfunding.bean.ProjectType;
import com.atguigu.atcrowdfunding.bean.TImgs;
import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TProjectTicket;
import com.atguigu.atcrowdfunding.bean.TTicket;
import com.atguigu.atcrowdfunding.bean.Tag;
import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.manager.service.AuthService;
import com.atguigu.atcrowdfunding.manager.service.CertService;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.potal.service.ProjectTicketService;
import com.atguigu.atcrowdfunding.util.AjaxResult;

@Controller
public class AuthController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private AuthService authServiceImpl;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CertService certServiceImpl;
	@Autowired
	private ProjectTicketService ptSerivce;

	@RequestMapping("/toAuthCert")
	public String authCert() {
		return "/auth/auth_cert";
	}

	@RequestMapping("/toAuthAdv")
	public String authAdv() {
		return "/auth/auth_adv";
	}

	@RequestMapping("/toAuthProject")
	public String authProgect() {
		return "/auth/auth_project";
	}

	/**
	 * 查询实名认证列表
	 * 
	 * @return
	 */
	@RequestMapping("/auth/list")
	@ResponseBody
	public Object list() {
		AjaxResult result = new AjaxResult();
		try {
			List<Map<String, Object>> authList = new ArrayList<Map<String, Object>>();

			TaskQuery query = taskService.createTaskQuery();
			List<Task> list = query.processDefinitionKey("auth").taskCandidateGroup("backuser").list();
			for (Task task : list) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", task.getId()); // 任务id
				map.put("name", task.getName()); // 任务名称

				// 通过任务表的流程定义id查询流程定义
				ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
						.processDefinitionId(task.getProcessDefinitionId()).singleResult();
				map.put("procDefName", processDefinition.getName());
				map.put("procDefVersion", processDefinition.getVersion());

				// 通过任务中的流程id查询流程审批单，然后在查询会员信息
				TTicket tTicket = authServiceImpl.selectTicketByPiid(task.getProcessInstanceId());
				System.out.println(tTicket);
				Member member = memberService.queryMemberByMid(tTicket.getMemberid());
				map.put("memberName", member.getUsername());
				map.put("memberid", member.getId());

				authList.add(map);
			}

			result.setStatus(200);
			result.setMessage(authList);
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("数据加载失败！");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 	查看当前用户需要审核的信息
	 * 
	 * @return
	 */
	@RequestMapping("/auth/showRealNameInfo")
	public String show(String taskId, Integer memberId, Model model) {

		try {

			// 查询用户详细信息
			Member member = memberService.queryMemberByMid(memberId);
			// 查询用户上传的资质信息以及文件
			List<MemberCert> list = certServiceImpl.selectMemberCert(memberId);
			for (MemberCert memberCert : list) {
				Cert cert = certServiceImpl.queryOne(memberCert.getCertid());
				memberCert.setCertname(cert.getName());
			}

			model.addAttribute("member", member);
			model.addAttribute("list", list);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return "/auth/show_auth_cert";
	}

	/**
	 * 审核通过
	 * 
	 * @return
	 */
	@RequestMapping("/auth/pass")
	@ResponseBody
	public Object pass(Integer memberid, String taskid) {
		AjaxResult result = new AjaxResult();
		try {

			// 设置flag、memberid，flag参数用于执行排他网关，memberid用于执行数据库状态更新操作
			taskService.setVariable(taskid, "flag", true);
			taskService.setVariable(taskid, "memberid", memberid);
			// 传递参数，让流程继续执行
			taskService.complete(taskid);
			result.setStatus(200);
			result.setMessage("实名认证审核通过！");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("审核通过出错，请稍后再试！");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 审核拒绝
	 * 
	 * @return
	 */
	@RequestMapping("/auth/refuse")
	@ResponseBody
	public Object refuse(Integer memberid, String taskid) {
		AjaxResult result = new AjaxResult();
		try {

			// 设置flag、memberid，flag参数用于执行排他网关，memberid用于执行数据库状态更新操作
			taskService.setVariable(taskid, "flag", false);
			taskService.setVariable(taskid, "memberid", memberid);
			// 传递参数，让流程继续执行
			taskService.complete(taskid);

			result.setStatus(200);
			result.setMessage(null);
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("审核拒绝出错，请稍后再试！");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取需要审核的项目列表
	 * @return
	 */
	@RequestMapping({ "/auth/proList" })
	@ResponseBody
	public Object proList() {
		AjaxResult result = new AjaxResult();
		try {
			List<Project> proList = new ArrayList();
			List<Task> list = this.taskService.createTaskQuery().processDefinitionKey("project")
					.taskCandidateGroup("backuser").list();
			for (Task task : list) {
				TProjectTicket ticket = this.ptSerivce.getTicketByPId(task.getProcessInstanceId());
				Project project = this.ptSerivce.getProjectByProId(ticket.getProid());
				proList.add(project);
			}
			result.setStatus(Integer.valueOf(200));
			result.setMessage(proList);
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(500));
			result.setMessage(null);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取项目信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping({"/auth/{id}/showInfo"})
	  public String showProInfo(@PathVariable Integer id, Model model)
	  {
	    Map<String, Object> map = new HashMap();
	    
	    Project project = this.authServiceImpl.getProjectById(id);
	    map.put("project", project);
	    
	    List<TImgs> imgs = this.authServiceImpl.getImgsByProId(id);
	    map.put("imgs", imgs);
	    
	    ProjectType pt = this.authServiceImpl.getProType(id);
	    Type type = this.authServiceImpl.getType(pt.getTypeid());
	    map.put("type", type);
	    
	    List<ProjectTag> pTags = this.authServiceImpl.getTagByProId(id);
	    for (ProjectTag projectTag : pTags)
	    {
	      Tag tag = this.authServiceImpl.getTagById(projectTag.getTagid());
	      projectTag.setTagName(tag.getName());
	    }
	    map.put("tags", pTags);
	    
	    Object returns = this.authServiceImpl.getRetByProId(id);
	    map.put("returns", returns);
	    
	    List<TProjectComp> comps = this.authServiceImpl.getCompByProId(id);
	    if (((TProjectComp)comps.get(0)).getUserexist().equals("1"))
	    {
	      List<MemberCert> mcs = this.authServiceImpl.getMemberCert(project.getMemberid());
	      List<Map<String, Object>> certInfos = new ArrayList();
	      for (MemberCert memberCert : mcs)
	      {
	        Map<String, Object> certInfo = new HashMap();
	        Cert cert = this.authServiceImpl.getCertById(memberCert.getCertid());
	        certInfo.put("id", memberCert.getId());
	        certInfo.put("iconpath", memberCert.getIconpath());
	        certInfo.put("name", cert.getName());
	        certInfos.add(certInfo);
	      }
	      map.put("certInfos", certInfos);
	    }
	    map.put("comp", comps.get(0));
	    model.addAttribute("proInfo", map);
	    
	    return "/auth/show_auth_project";
	  }
	
	/**
	 * 项目审核通过
	 * @param proId
	 * @return
	 */
	@RequestMapping({"/auth/proPass"})
	  @ResponseBody
	  public Object proPass(Integer proId)
	  {
	    AjaxResult result = new AjaxResult();
	    try
	    {
	      TProjectTicket pt = this.authServiceImpl.getTicketByProId(proId);
	      Task task = (Task)this.taskService.createTaskQuery().processInstanceId(pt.getPid()).singleResult();
	      
	      this.taskService.setVariable(task.getId(), "flag", Boolean.valueOf(true));
	      this.taskService.setVariable(task.getId(), "proId", proId);
	      
	      this.taskService.complete(task.getId());
	      result.setStatus(Integer.valueOf(200));
	      result.setMessage("认证审核通过！");
	    }
	    catch (Exception e)
	    {
	      result.setStatus(Integer.valueOf(500));
	      result.setMessage("审核出错，请稍后再试！");
	      e.printStackTrace();
	    }
	    return result;
	  }
	  
	/**
	 * 项目拒绝
	 * @param proId
	 * @param result
	 * @return
	 */
	  @RequestMapping({"/auth/proRefuse"})
	  @ResponseBody
	  public Object proRefuse(Integer proId, String result)
	  {
	    AjaxResult res = new AjaxResult();
	    try
	    {
	      TProjectTicket pt = this.authServiceImpl.getTicketByProId(proId);
	      Task task = (Task)this.taskService.createTaskQuery().processInstanceId(pt.getPid()).singleResult();
	      
	      this.taskService.setVariable(task.getId(), "flag", Boolean.valueOf(false));
	      this.taskService.setVariable(task.getId(), "proId", proId);
	      this.taskService.setVariable(task.getId(), "result", result);
	      
	      this.taskService.complete(task.getId());
	      res.setStatus(Integer.valueOf(200));
	    }
	    catch (Exception e)
	    {
	      res.setStatus(Integer.valueOf(500));
	      res.setMessage("审核拒绝出错，请稍后再试！");
	      e.printStackTrace();
	    }
	    return res;
	  }

}
