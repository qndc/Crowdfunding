package com.atguigu.atcrowdfunding.potal.controller;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.Data;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import com.atguigu.atcrowdfunding.bean.TTicket;
import com.atguigu.atcrowdfunding.manager.service.CertService;
import com.atguigu.atcrowdfunding.potal.listener.PassListener;
import com.atguigu.atcrowdfunding.potal.listener.RefuseListener;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.potal.service.PicService;
import com.atguigu.atcrowdfunding.potal.service.TicketService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/member" })
public class MemberController {
	@Autowired
	private MemberService memberServiceImpl;
	@Autowired
	private TicketService ticketServiceImpl;
	@Autowired
	private CertService certServiceImpl;
	@Autowired
	private PicService picServiceImpl;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;

	@RequestMapping({ "/finish" })
	@ResponseBody
	public Object finish(String code, HttpSession session) {
		AjaxResult result = new AjaxResult();
		Member member = (Member) session.getAttribute("member");
		try {
			TTicket tTicket = this.ticketServiceImpl.queryTicketByMid(member.getId());
			if (code.equals(tTicket.getAuthcode())) {
				Task task = (Task) this.taskService.createTaskQuery().processInstanceId(tTicket.getPiid())
						.taskAssignee(member.getLoginacct()).singleResult();
				this.taskService.complete(task.getId());

				member.setAuthstatus("1");
				this.memberServiceImpl.updateMember(member);

				result.setStatus(Integer.valueOf(200));
			} else {
				result.setStatus(Integer.valueOf(500));
				result.setMessage("验证码错误！");
			}
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(500));
			result.setMessage("验证码错误！");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping({ "/sendEmail" })
	@ResponseBody
	public Object sendEmail(String email, HttpSession session) {
		AjaxResult result = new AjaxResult();
		Member member = (Member) session.getAttribute("member");
		try {
			if (!email.equals(member.getEmail())) {
				member.setEmail(email);
				this.memberServiceImpl.updateMember(member);
			}

			Map<String, Object> param = new HashMap();

			StringBuilder authcode = new StringBuilder();
			for (int i = 0; i < 4; i++) {
				authcode.append(new Random().nextInt(10));
			}
			param.put("toEmail", email);
			param.put("authcode", authcode.toString());
			param.put("loginacct", member.getLoginacct());
			param.put("passListener", new PassListener());
			param.put("refuseListener", new RefuseListener());

			ProcessDefinitionQuery processDefinitionQuery = this.repositoryService.createProcessDefinitionQuery();
			ProcessDefinition processDefinition = (ProcessDefinition) processDefinitionQuery
					.processDefinitionKey("auth").latestVersion().singleResult();

			ProcessInstance processInstance = this.runtimeService.startProcessInstanceById(processDefinition.getId(),
					param);

			TTicket tTicket = this.ticketServiceImpl.queryTicketByMid(member.getId());
			tTicket.setPstep("checkemail");
			tTicket.setPiid(processInstance.getId());
			tTicket.setAuthcode(authcode.toString());
			this.ticketServiceImpl.updateTicket(tTicket);
			result.setStatus(Integer.valueOf(200));
			result.setMessage("验证码发送成功，请前往邮箱查看！");
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(500));
			result.setMessage("验证码发送失败！");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping({ "/uploadImg" })
	@ResponseBody
	public Object uploadImg(Data data, HttpSession session) {
		AjaxResult result = new AjaxResult();
		Member member = (Member) session.getAttribute("member");
		try {
			for (MemberCert tMemberCert : data.getList()) {
				Map map = this.picServiceImpl.uploadPic(tMemberCert.getImgfile());
				tMemberCert.setMemberid(member.getId());
				tMemberCert.setIconpath(map.get("url").toString());

				this.certServiceImpl.insertMemberCert(tMemberCert);
			}

			TTicket tTicket = this.ticketServiceImpl.queryTicketByMid(member.getId());
			tTicket.setPstep("uploadfile");

			this.ticketServiceImpl.updateTicket(tTicket);

			result.setStatus(Integer.valueOf(200));
			result.setMessage("图片上传成功！");
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(200));
			result.setMessage("图片上传失败！");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping({ "/toAcctType" })
	public String toAcctType(HttpSession session, HttpServletRequest request) {
		Member member = (Member) session.getAttribute("member");
		TTicket tTicket = this.ticketServiceImpl.queryTicketByMid(member.getId());

		if (tTicket == null) {
			tTicket = new TTicket();
			tTicket.setMemberid(member.getId());
			tTicket.setStatus("0");
			tTicket.setPstep("apply");
			this.ticketServiceImpl.insertTicket(tTicket);
		} else {
			String step = tTicket.getPstep();
			if (step.equals("accttype"))
				return "/member/basicinfo";
			if (step.equals("basicinfo")) {
				List<Cert> certs = this.certServiceImpl.queryCertByType(member.getAccttype());
				request.setAttribute("certs", certs);
				return "/member/uploadfile";
			}
			if (step.equals("uploadfile"))
				return "/member/checkemail";
			if (step.equals("checkemail")) {
				return "/member/checkemail";
			}
		}
		return "/member/accttype";
	}

	@RequestMapping({ "/tocheckcode" })
	public String tocheckcode() {
		return "/member/checkcode";
	}

	@RequestMapping({ "/toApply" })
	@ResponseBody
	public Object toApply(String accttype, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			Member member = (Member) session.getAttribute("member");
			member.setAccttype(accttype);
			this.memberServiceImpl.updateMember(member);

			TTicket tTicket = this.ticketServiceImpl.queryTicketByMid(member.getId());
			tTicket.setPstep("accttype");
			this.ticketServiceImpl.updateTicket(tTicket);

			session.setAttribute("member", member);

			result.setStatus(Integer.valueOf(200));
			result.setMessage("修改账户类型成功！");
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(200));
			result.setMessage("修改账户类型失败！");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping({ "/updateBasicInfo" })
	@ResponseBody
	public Object basicInfo(Member info, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			Member member = (Member) session.getAttribute("member");
			member.setRealname(info.getRealname());
			member.setCardnum(info.getCardnum());
			member.setTel(info.getTel());
			this.memberServiceImpl.updateMember(member);

			TTicket tTicket = this.ticketServiceImpl.queryTicketByMid(member.getId());
			tTicket.setPstep("basicinfo");
			this.ticketServiceImpl.updateTicket(tTicket);

			session.setAttribute("member", member);

			result.setStatus(Integer.valueOf(200));
			result.setMessage("修改账户类型成功！");
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(200));
			result.setMessage("修改账户类型失败！");
			e.printStackTrace();
		}
		return result;
	}
}
