package com.atguigu.atcrowdfunding.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.manager.dao.MemberManagerService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;

@Controller
@RequestMapping("/member")
public class MemberManagerController {
	
	@Autowired
	private MemberManagerService memberManagerServiceImpl;
	
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
	public String name(@RequestParam Integer id) {
		System.err.println(id);
		return "/member/info";
	}

}
