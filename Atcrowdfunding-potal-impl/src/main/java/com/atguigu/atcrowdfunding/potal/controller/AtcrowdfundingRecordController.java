package com.atguigu.atcrowdfunding.potal.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TImgs;
import com.atguigu.atcrowdfunding.bean.TMemberProjectFollow;
import com.atguigu.atcrowdfunding.bean.TOrder;
import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TReturn;
import com.atguigu.atcrowdfunding.potal.service.AtcrowdfundingRecordService;
import com.atguigu.atcrowdfunding.potal.service.AtcrowdfundingService;
import com.atguigu.atcrowdfunding.potal.service.HomePageService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.google.gson.Gson;

@Controller
@RequestMapping({"/record"})
public class AtcrowdfundingRecordController {
	
	@Autowired
	private AtcrowdfundingRecordService recordService;
	@Autowired
	private HomePageService homePageService;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	/**
	 * 	我的关注
	 * @return
	 */
	@RequestMapping("/myfollow")
	@ResponseBody
	public Object myFollow(HttpSession session) {
		AjaxResult result = new AjaxResult();
		Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
		String myfollow = (String)redisTemplate.opsForValue().get(Const.MYFOLLOW);
		try {
			if (StringUtils.isBlank(myfollow)) {
				List<Project> follows = new ArrayList<Project>();
				for (TMemberProjectFollow tMemberProjectFollow : recordService.selectAllFollows(loginMember.getId())) {
					Project project = homePageService.getProsById(tMemberProjectFollow.getProjectid());
					project.setEnddate(formatDate(project));
					follows.add(project);
					result.setMessage(follows);
				}
				redisTemplate.opsForValue().set(Const.MYFOLLOW, JSON.toJSONString(follows));
				//设置过期时间
				redisTemplate.expire(Const.MYFOLLOW, Const.DEFAULT_EXPIRE, TimeUnit.SECONDS);
			}else {
				result.setMessage(JSONObject.parseArray(myfollow,Project.class));
			}
			result.setStatus(200);
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
	@ResponseBody
	public Object myLaunch(@RequestParam(defaultValue = "0")Integer status,HttpSession session) {
		AjaxResult result = new AjaxResult();
		Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
		String mylaunch = (String)redisTemplate.opsForValue().get(Const.MYLAUNCH);
		List<Project> pros = new ArrayList<>();
		try {
			//先查询redis中是否存在，如果不存在就从数据库查询并添加到redis中
			if (StringUtils.isBlank(mylaunch)) {
				pros = recordService.selectByMemberId(loginMember.getId());
				for (Project project : pros) {
					project.setEnddate(formatDate(project));
				}
				redisTemplate.opsForValue().set(Const.MYLAUNCH, JSON.toJSONString(pros));
				//设置过期时间
				redisTemplate.expire(Const.MYLAUNCH, Const.DEFAULT_EXPIRE, TimeUnit.SECONDS);
			}else {
				pros = JSONObject.parseArray(mylaunch,Project.class);
			}
			//0 - 全部 1 - 众筹中， 2 - 众筹成功， 3 - 众筹失败，判断是那种类型并根据类型返回值
			if (status == 0) {
				result.setMessage(pros);
			}else {
				List<Project> temp = new ArrayList<>();
				for (Project project : pros) {
					if (Integer.parseInt(project.getStatus()) == status) {
						temp.add(project);
					}
				}
				result.setMessage(temp);
			}
			result.setStatus(200);
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("查询失败！");
		}
		return result;
	}
	
	/**
	 * 我的支持
	 * @param status
	 * @param session
	 * @return
	 */
	@RequestMapping("/mysupport")
	@ResponseBody
	public Object mySupport(@RequestParam(defaultValue = "0")Integer status,HttpSession session) {
		AjaxResult result = new AjaxResult();
		Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
		String mysupport = (String)redisTemplate.opsForValue().get(Const.MYSUPPORT);
		List<TOrder> orders = new ArrayList<>();
		try {
			//redis中不存在就查询数据库，并添加到redis中
			if (StringUtils.isBlank(mysupport)) {
				orders = homePageService.getOrderByMemberId(loginMember.getId());
				redisTemplate.opsForValue().set(Const.MYSUPPORT,JSON.toJSONString(orders));
				//设置过期时间
				redisTemplate.expire(Const.MYSUPPORT, Const.DEFAULT_EXPIRE, TimeUnit.SECONDS);
			}else {
				orders = JSONObject.parseArray(mysupport,TOrder.class);
			}
			//0 - 全部 ，1 - 已付款， 2 - 未付款， 3 - 交易关闭
			if (status == 0) {
				for (TOrder tOrder : orders) {
					Project project = homePageService.getProsById(tOrder.getProjectid());
					tOrder.setProject(project);
				}
				result.setMessage(orders);
			}else {
				List<TOrder> temp = new ArrayList<>();
				for (TOrder tOrder : orders) {
					if (Integer.parseInt(tOrder.getStatus()) == status) {
						Project project = homePageService.getProsById(tOrder.getProjectid());
						tOrder.setProject(project);
						temp.add(tOrder);
					}
				}
				result.setMessage(temp);
			}
			result.setStatus(200);
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("查询失败！");
		}
		return result;
	}
	
	/**
	 * 	删除项目
	 * @param proid
	 * @return
	 */
	@RequestMapping("/delPro")
	@ResponseBody
	public Object delProject(@RequestParam(required = true) Integer proId) {
		AjaxResult result = new AjaxResult();
		try {
			recordService.delProject(proId);
			//删除成功后要移除redis缓存
			redisTemplate.delete(Const.MYLAUNCH);
			result.setStatus(200);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("删除失败！");
		}
		return result;
	}
	
	/**
	 * 	项目预览
	 * @param proId
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/{proId}/preview" })
	public String detailInfo(@PathVariable Integer proId, Model model,HttpSession session) throws Exception {
		Project project = recordService.getProById(proId);
		project.setEnddate(formatDate(project));
		List<TImgs> imgs = this.homePageService.getProDetailImg(proId);
		List<TReturn> returntList = this.homePageService.getReturnByProId(proId);
		TProjectComp comp = this.homePageService.getCompByProId(proId);
		Member loginMember = (Member) session.getAttribute("member");
		List<TMemberProjectFollow> list = homePageService.getProjectFollowe(loginMember.getId(),proId);
		model.addAttribute("project", project);
		model.addAttribute("imgs", imgs);
		model.addAttribute("returntList", returntList);
		model.addAttribute("comp", comp);
		return "/project/preview";
	}
	
	/**
	 * 	剩余时间转换器
	 * @param project
	 * @return
	 * @throws ParseException 
	 */
	private String formatDate(Project project) throws ParseException {
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
		
		return "剩余" + days + "天" + hours + "小时" + minutes + "分" + seconds + "秒";
		
	}
	
	
}
