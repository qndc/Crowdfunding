package com.atguigu.atcrowdfunding.potal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.TMemberAddress;
import com.atguigu.atcrowdfunding.potal.service.PersonalService;
import com.atguigu.atcrowdfunding.util.AjaxResult;

@Controller
public class PersonalController {
	
	@Autowired
	private PersonalService personalService;
	
	/**
	 * 	根据用户id获取地址列表
	 * @return
	 */
	@RequestMapping({ "/personal/Loadaddr" })
	@ResponseBody
	public Object Loadaddr(HttpSession session) {
		AjaxResult result = new AjaxResult();
		Member loginMember = (Member) session.getAttribute("member");
		try {
			List<TMemberAddress> addresses = personalService.addrList(loginMember.getId());
			addresses.forEach(addr -> {
				String[] split = addr.getAddress().split(" ");
				addr.setProvince(split[0]);
				addr.setCity(split[1]);
				addr.setDistrict(split[2]);
				addr.setDetail(split[3]);
			});
			result.setMessage(addresses);
			result.setStatus(200);
		} catch (Exception e) {
			result.setMessage("地址获取失败");
			result.setStatus(500);
		}
		return result;
	}

}
