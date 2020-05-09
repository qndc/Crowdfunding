package com.atguigu.atcrowdfunding.potal.controller;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TProjectTicket;
import com.atguigu.atcrowdfunding.bean.TReturn;
import com.atguigu.atcrowdfunding.bean.Tag;
import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.potal.listener.NoListener;
import com.atguigu.atcrowdfunding.potal.listener.YesListener;
import com.atguigu.atcrowdfunding.potal.service.AtcrowdfundingService;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.potal.service.PicService;
import com.atguigu.atcrowdfunding.potal.service.ProjectTicketService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.UploadResult;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AtcrowdfundingController {
	@Autowired
	private AtcrowdfundingService actService;
	@Autowired
	private ProjectTicketService ptService;
	@Autowired
	private PicService picService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private MemberService memberService;

	@RequestMapping({ "/atcrowdfunding/toIndex" })
	@ResponseBody
	public Object toIndex(HttpSession session) {
		AjaxResult result = new AjaxResult();

		try {
			Member loginMember = (Member) session.getAttribute("member");
			if (loginMember.getAuthstatus().equals("2")) {
				result.setStatus(Integer.valueOf(200));
			} else {
				result.setMessage("请先实名认证");
				result.setStatus(Integer.valueOf(500));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("跳转失败");
			result.setStatus(Integer.valueOf(500));
		}
		return result;
	}

	@RequestMapping({ "/atcrowdfunding/index" })
	public String index() {
		return "/atcrowdfunding/index";
	}
	
	@RequestMapping({ "/atcrowdfunding/personal" })
	public String personal() {
		return "/atcrowdfunding/personal";
	}

	@RequestMapping({ "/atcrowdfunding/start" })
	public String start() {
		return "/atcrowdfunding/start";
	}

	@RequestMapping({ "/atcrowdfunding/return" })
	public String toReturn() {
		return "/atcrowdfunding/return";
	}

	@RequestMapping({ "/atcrowdfunding/confirm" })
	public String toConfirm(HttpSession session,Model model) {
		Member member = (Member) session.getAttribute("member");
		model.addAttribute("member", member);
		return "/atcrowdfunding/confirm";
	}

	@RequestMapping({ "/atcrowdfunding/complete" })
	public String toComplete() {
		return "/atcrowdfunding/complete";
	}

	@RequestMapping({ "/atcrowdfunding/getType" })
	@ResponseBody
	public Object projectInfo() {
		AjaxResult result = new AjaxResult();
		try {
			List<Type> typeList = this.actService.typeList();
			result.setMessage(typeList);
			result.setStatus(Integer.valueOf(200));
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("查询失败！");
			result.setStatus(Integer.valueOf(500));
		}
		return result;
	}

	@RequestMapping({ "/atcrowdfunding/getTagById" })
	@ResponseBody
	public Object getTagById(Integer typeid) {
		AjaxResult result = new AjaxResult();
		try {
			List<Tag> tags = this.actService.getTagById(typeid);
			result.setStatus(Integer.valueOf(200));
			result.setMessage(tags);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 	发起项目流程总控制器
	 * @param session
	 * @param projectId
	 * @param map
	 * @return
	 */
	@RequestMapping({ "/atcrowdfunding/apply" })
	public String ActivityControlle(HttpSession session, Integer projectId, Map map) {
		Member member = (Member) session.getAttribute("member");
		TProjectTicket projectTicket = this.ptService.getTicketByMemId(member.getId());
		if (projectTicket == null) {
			projectTicket = new TProjectTicket();
			projectTicket.setMemberid(member.getId());
			projectTicket.setPstep("init");
			projectTicket.setStatus("0");
			this.ptService.createTicket(projectTicket);
		} else {
			String step = projectTicket.getPstep();

			Member m = (Member) session.getAttribute("member");
			TProjectTicket ticket = this.ptService.getTicketByMemId(member.getId());
			map.put("projectId", ticket.getProid());
			if (step.equals("init"))
				return "/atcrowdfunding/start";
			if (step.equals("agreement"))
				return "/atcrowdfunding/projectInfo";
			if (step.equals("projectInfo")) {
				//查询回报列表
				List<TReturn> returns = this.ptService.getReturns(ticket.getProid());
				map.put("returns", returns);
				return "/atcrowdfunding/return";
			}
			if (step.equals("return"))
				return "/atcrowdfunding/confirm";
			if (step.equals("confirmation")) {
				return "/atcrowdfunding/complete";
			}
		}
		return "/atcrowdfunding/start";
	}

	@RequestMapping({ "/atcrowdfunding/agree" })
	@ResponseBody
	public Object agree(HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			Member member = (Member) session.getAttribute("member");
			TProjectTicket ticket = this.ptService.getTicketByMemId(member.getId());
			ticket.setPstep("agreement");
			this.ptService.updateProjectTicket(ticket);
			result.setStatus(Integer.valueOf(200));
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Integer.valueOf(500));
			result.setMessage("程序错误！");
		}
		return result;
	}

	/**
	 * 文件上传
	 * @param session
	 * @param file
	 * @return
	 */
	@RequestMapping({ "/atcrowdfunding/upload" })
	@ResponseBody
	public Object upload(HttpSession session, @RequestParam List<MultipartFile> file) {
		UploadResult result = new UploadResult();
		try {
			Member member = (Member) session.getAttribute("member");

			if (file != null) {
				for (MultipartFile uploadFile : file) {
					Map pic = this.picService.uploadPic(uploadFile);
					Map<String, String> map = new HashMap();
					map.put("src", pic.get("url").toString());
					result.setData(map);
				}
				result.setCode(Integer.valueOf(200));
				result.setMsg("上传成功");
			} else {
				result.setCode(Integer.valueOf(500));
				result.setMsg("文件上传为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(Integer.valueOf(500));
			result.setMsg("上传失败");
		}
		return result;
	}

	/**
	 * 文件下载
	 * @param response
	 * @param fileName
	 */
	@RequestMapping(value = { "/atcrowdfunding/{fileName}/download" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public void downLoad(HttpServletResponse response, @PathVariable String fileName) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("multipart/form-data;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		try {
			OutputStream output = response.getOutputStream();
			this.picService.downLoad(fileName, output);
			output.close();
		} catch (Exception localException) {
		}
	}

	/**
	 * 添加项目
	 * @param session
	 * @param project
	 * @return
	 */
	@RequestMapping({ "/atcrowdfunding/proInfo" })
	@ResponseBody
	public Object proInfo(HttpSession session, Project project) {
		AjaxResult result = new AjaxResult();
		try {
			Member member = (Member) session.getAttribute("member");
			project.setStatus("0");
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			project.setCreatedate(format.format(date));
			project.setMemberid(member.getId());
			project.setCompletion(0);
			project.setSupportmoney(0l);
			project.setSupporter(0);
			project.setFollower(0);
			ptService.insertProject(project);
			Map<String, Object> param = new HashMap<>();
			param.put("loginacct", member.getLoginacct());
			param.put("passListener", new YesListener());
			param.put("refuseListener", new NoListener());
			ProcessDefinitionQuery processDefinitionQuery = this.repositoryService.createProcessDefinitionQuery();
			ProcessDefinition processDefinition = (ProcessDefinition) processDefinitionQuery
					.processDefinitionKey("project").latestVersion().singleResult();
			ProcessInstance processInstance = this.runtimeService.startProcessInstanceById(processDefinition.getId(),
					param);
			TProjectTicket ticket = this.ptService.getTicketByMemId(member.getId());
			ticket.setPstep("projectInfo");
			ticket.setProid(project.getId());
			ticket.setPid(processInstance.getId());
			ptService.updateProjectTicket(ticket);
			result.setStatus(Integer.valueOf(200));
			result.setMessage(project.getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Integer.valueOf(500));
			result.setMessage("项目发布失败！");
		}
		return result;
	}

	/**
	 * 添加回报
	 * @param session
	 * @param ret
	 * @return
	 */
	@RequestMapping({ "/atcrowdfunding/addReturn" })
	@ResponseBody
	public Object addReturn(HttpSession session, TReturn ret) {
		AjaxResult result = new AjaxResult();
		try {
			this.ptService.insertReturn(ret);
			result.setStatus(Integer.valueOf(200));
			result.setMessage("回报添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Integer.valueOf(500));
			result.setMessage("回报添加失败");
		}
		return result;
	}

	/**
	 * 根据回报id查询回报
	 * @param returnId
	 * @return
	 */
	@RequestMapping({ "/atcrowdfunding/getReturnById" })
	@ResponseBody
	public Object getRetById(Integer returnId) {
		AjaxResult result = new AjaxResult();
		try {
			TReturn ret = this.actService.getRetById(returnId);
			result.setStatus(Integer.valueOf(200));
			result.setMessage(ret);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Integer.valueOf(500));
			result.setMessage("查询失败！");
		}
		return result;
	}

	/**
	 * 更新回报
	 * @param ret
	 * @return
	 */
	@RequestMapping({ "/atcrowdfunding/updateReturn" })
	@ResponseBody
	public Object updateReturn(TReturn ret) {
		AjaxResult result = new AjaxResult();
		try {
			this.actService.updateReturn(ret);

			result.setStatus(Integer.valueOf(200));
			result.setMessage("回报修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Integer.valueOf(500));
			result.setMessage("回报修改失败");
		}
		return result;
	}

	/**
	 * 删除回报
	 * @param returnId
	 * @return
	 */
	@RequestMapping({ "/atcrowdfunding/delReturn" })
	@ResponseBody
	public Object delReturn(Integer returnId) {
		AjaxResult result = new AjaxResult();
		try {
			this.actService.delReturn(returnId);
			result.setStatus(Integer.valueOf(200));
			result.setMessage("回报删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Integer.valueOf(500));
			result.setMessage("回报删除失败");
		}
		return result;
	}

	/**
	 * 更新项目审批单到回报填写
	 * @param session
	 * @return
	 */
	@RequestMapping({ "/atcrowdfunding/proReturn.do" })
	public String proReturn(HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		TProjectTicket ticket = this.ptService.getTicketByMemId(member.getId());
		ticket.setPstep("return");
		this.ptService.updateProjectTicket(ticket);

		Task task = (Task) this.taskService.createTaskQuery().processInstanceId(ticket.getPid())
				.taskAssignee(member.getLoginacct()).singleResult();
		this.taskService.complete(task.getId());

		return "redirect:/atcrowdfunding/apply.do";
	}

	/**
	 * 添加公司信息
	 * @param comp
	 * @param session
	 * @return
	 */
	@RequestMapping({ "/atcrowdfunding/addComp" })
	@ResponseBody
	public Object addComp(TProjectComp comp, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			if (StringUtils.isBlank(comp.getUserexist())) {
				comp.setUserexist("0");
			}
			comp.setServicetime(comp.getStart_time() + "-" + comp.getEnd_time());
			this.actService.addComp(comp);

			Member member = (Member) session.getAttribute("member");
			TProjectTicket ticket = this.ptService.getTicketByMemId(member.getId());
			ticket.setPstep("confirmation");
			this.ptService.updateProjectTicket(ticket);

			Task task = (Task) this.taskService.createTaskQuery().processInstanceId(ticket.getPid())
					.taskAssignee(member.getLoginacct()).singleResult();
			this.taskService.complete(task.getId());

			Member mm = this.memberService.selectMemberByLoginacct(member.getLoginacct());
			Map map = new HashMap();
			map.put("name", mm.getRealname());

			result.setStatus(Integer.valueOf(200));
			result.setMessage("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Integer.valueOf(500));
			result.setMessage("公司信息添加失败");
		}
		return result;
	}
}
