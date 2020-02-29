package com.atguigu.atcrowdfunding.manager.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import com.atguigu.atcrowdfunding.bean.TTicket;
import com.atguigu.atcrowdfunding.manager.dao.MemberManagerService;
import com.atguigu.atcrowdfunding.manager.service.AuthService;
import com.atguigu.atcrowdfunding.manager.service.CertService;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;

@Controller
@RequestMapping("/member")
public class MemberManagerController {
	
	@Autowired
	private MemberManagerService memberManagerServiceImpl;
	@Autowired
	private MemberService memberServiceImpl;
	@Autowired
	private CertService certServiceImpl;
	@Autowired
	private AuthService authServiceImpl;
	
	
	@RequestMapping("/toIndex")
	public String toUserIndex() {
		return "member/index";	
	}
	
	/**
	 * 	异步分页查询用户列表
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1")Integer pageTotal,@RequestParam(defaultValue = "10")Integer pageSeize,String queryText) {
		AjaxResult result = new AjaxResult();
		try {
			Page queryPage = memberManagerServiceImpl.queryPage(pageTotal,pageSeize,queryText);
			result.setStatus(200);
			result.setMessage(queryPage);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
			result.setMessage("查询数据失败！");
		}
		return result;	
	}
	
	/**
	 * 	查看用户详细信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public String name(@RequestParam Integer id,Model model) {
		Member member = memberServiceImpl.queryMemberByMid(id);
		// 查询用户上传的资质信息以及文件
		List<MemberCert> list = certServiceImpl.selectMemberCert(id);
		for (MemberCert memberCert : list) {
			Cert cert = certServiceImpl.queryOne(memberCert.getCertid());
			memberCert.setCertname(cert.getName());
		}
		model.addAttribute("member", member);
		model.addAttribute("list", list);
		return "/member/info";
	}
	
	/**
	 * 停用
	 * @param id
	 * @return
	 */
	@RequestMapping("/prohibition")
	@ResponseBody
	public Object prohibition(@RequestParam Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			Member member = memberServiceImpl.queryMemberByMid(id);
			if (member.getAuthstatus().equals("3")) {
				result.setMessage("当前用户已禁用");
			}else {
				member.setAuthstatus("3");
				memberServiceImpl.updateMember(member);
				result.setMessage("禁用成功");
			}
			result.setStatus(200);
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("禁用失败");
		}
		return result;	
	}

	/**
	 * 恢复，要根据实名认证审批单的状态设置用户状态
	 * @param id
	 * @return
	 */
	@RequestMapping("/recover")
	@ResponseBody
	public Object recover(@RequestParam Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			Member member = memberServiceImpl.queryMemberByMid(id);
			if (member.getAuthstatus().equals("3")) {
				List<TTicket> tickets = authServiceImpl.getTicketByMemid(member.getId());
				//为空代表未实名-0
				if (tickets.isEmpty()) {
					member.setAuthstatus("0");
				}else if(tickets.get(0).getStatus().equals("0")){
					//审批单状态为0代表审核中-1
					member.setAuthstatus("1");
				}else if(tickets.get(0).getStatus().equals("1")){
					//审批单状态为0代表审核完成-2
					member.setAuthstatus("2");
				}
				memberServiceImpl.updateMember(member);
				result.setMessage("恢复成功");
			}else {
				result.setMessage("当前用户可用");
			}
			result.setStatus(200);
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("恢复失败");
		}
		return result;	
	}
}
