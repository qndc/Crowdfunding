package com.atguigu.atcrowdfunding.potal.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TMemberProjectFollow;
import com.atguigu.atcrowdfunding.potal.service.AtcrowdfundingRecordService;
import com.atguigu.atcrowdfunding.potal.service.AtcrowdfundingService;
import com.atguigu.atcrowdfunding.potal.service.HomePageService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;

@Controller
@RequestMapping({"/record"})
public class AtcrowdfundingRecordController {
	
	@Autowired
	private AtcrowdfundingRecordService recordService;
	@Autowired
	private HomePageService homePageService;
	
	/**
	 * 	我的关注
	 * @return
	 */
	@RequestMapping("/myfollow")
	@ResponseBody
	public Object myFollow(HttpSession session) {
		AjaxResult result = new AjaxResult();
		Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
		List<Project> myfollow = new ArrayList<Project>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			for (TMemberProjectFollow tMemberProjectFollow : recordService.selectAllFollows(loginMember.getId())) {
				Project project = homePageService.getProsById(tMemberProjectFollow.getProjectid());
				//时间显示
				LocalDateTime start = LocalDateTime.parse(project.getDeploydate(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				LocalDateTime end = start.plusDays(project.getDay());
				LocalDateTime now = LocalDateTime.now();
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date beginTime = null;
				Date endTime = null;
				beginTime = sdf.parse(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				endTime = sdf.parse(end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				long diff = endTime.getTime() - beginTime.getTime();
				long days = diff / 86400000L;
				long hours = diff % 86400000L / 3600000L;
				long minutes = diff % 3600000L / 60000L;
				long seconds = diff % 60000L / 1000L;
				project.setEnddate("剩余" + days + "天" + hours + "小时" + minutes + "分" + seconds + "秒");
				myfollow.add(project);
			}
			
			result.setStatus(200);
			result.setMessage(myfollow);
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("查询失败！");
		}
		return result;
	}
	
	/**
	 * 	我的发起
	 * @return
	 */
	@RequestMapping("/mylaunch")
	public Object myLaunch() {
		AjaxResult result = new AjaxResult();
		try {
			
		} catch (Exception e) {
			
		}
		return result;
	}
	
	
	@RequestMapping("/mySupport")
	public Object mySupport() {
		AjaxResult result = new AjaxResult();
		try {
			
		} catch (Exception e) {
			
		}
		return result;
	}
}
