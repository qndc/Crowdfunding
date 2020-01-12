package com.atguigu.atcrowdfunding.potal.controller;

import com.atguigu.atcrowdfunding.bean.AliPayBean;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.ProjectType;
import com.atguigu.atcrowdfunding.bean.TImgs;
import com.atguigu.atcrowdfunding.bean.TMemberAddress;
import com.atguigu.atcrowdfunding.bean.TMemberInvoice;
import com.atguigu.atcrowdfunding.bean.TMemberProjectFollow;
import com.atguigu.atcrowdfunding.bean.TOrder;
import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TReturn;
import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.potal.service.HomePageService;
import com.atguigu.atcrowdfunding.potal.service.QuartzService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.AliPayUtil;
import com.atguigu.atcrowdfunding.util.PageVo;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/homepage" })
public class HomePageController {
	@Autowired
	private HomePageService homePageService;

	@RequestMapping({ "/{proId}/detailInfo" })
	public String detailInfo(@PathVariable Integer proId, Model model,HttpSession session) {
		
		boolean isFollow = false;
		
		Project project = this.homePageService.getProsById(proId);

		List<TImgs> imgs = this.homePageService.getProDetailImg(proId);

		List<TReturn> returntList = this.homePageService.getReturnByProId(proId);

		TProjectComp comp = this.homePageService.getCompByProId(proId);
		
		//时间显示
		LocalDateTime start = LocalDateTime.parse(project.getDeploydate(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime end = start.plusDays(project.getDay());
		LocalDateTime now = LocalDateTime.now();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginTime = null;
		Date endTime = null;
		try {
			beginTime = sdf.parse(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			endTime = sdf.parse(end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

			long diff = endTime.getTime() - beginTime.getTime();

			long days = diff / 86400000L;

			long hours = diff % 86400000L / 3600000L;

			long minutes = diff % 3600000L / 60000L;

			long seconds = diff % 60000L / 1000L;

			model.addAttribute("time", "剩余" + days + "天" + hours + "小时" + minutes + "分" + seconds + "秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Member loginMember = (Member) session.getAttribute("member");
		List<TMemberProjectFollow> list = homePageService.getProjectFollowe(loginMember.getId(),proId);
		if (!list.isEmpty()) {
			isFollow = true;
		}
		model.addAttribute("project", project);
		model.addAttribute("imgs", imgs);
		model.addAttribute("returntList", returntList);
		model.addAttribute("comp", comp);
		model.addAttribute("isFollow", isFollow);

		return "/project/detailInfo";
	}

	@RequestMapping({ "/{proId}/{retId}/confirmReturn" })
	public String confirmReturn(@PathVariable Integer retId, @PathVariable Integer proId, Model model) {
		Project project = this.homePageService.getProsById(proId);

		TReturn ret = this.homePageService.getReturnById(retId);

		TProjectComp comp = this.homePageService.getCompByProId(proId);
		model.addAttribute("ret", ret);
		model.addAttribute("comp", comp);
		model.addAttribute("project", project);
		return "/project/confirmReturn";
	}

	@RequestMapping({ "/{proId}/{retId}/{num}/confirmOrder" })
	public String confirmOrder(@PathVariable Integer retId, @PathVariable Integer proId, @PathVariable Integer num,
			Model model, HttpSession session) {
		Member loginMember = (Member) session.getAttribute("member");

		Project project = this.homePageService.getProsById(proId);

		TReturn ret = this.homePageService.getReturnById(retId);

		TProjectComp comp = this.homePageService.getCompByProId(proId);

		List<TMemberAddress> addresses = this.homePageService.getAddrByMemberId(loginMember.getId());

		model.addAttribute("ret", ret);
		model.addAttribute("comp", comp);
		model.addAttribute("project", project);
		model.addAttribute("num", num);
		model.addAttribute("addresses", addresses);
		return "/project/confirmOrder";
	}

	@RequestMapping({ "/addAddr" })
	@ResponseBody
	public Object addAttr(TMemberAddress address, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			Member loginMember = (Member) session.getAttribute("member");
			address.setMemberid(loginMember.getId());
			address.setAddress(address.getProvince() + " " + address.getCity() + " " + address.getDistrict() + " "
					+ address.getDetail());
			this.homePageService.addAddress(address);
			result.setStatus(Integer.valueOf(200));
			result.setMessage(address.getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Integer.valueOf(500));
			result.setMessage("添加失败，请稍后再试");
		}
		return result;
	}

	@RequestMapping({ "/addInvoice" })
	@ResponseBody
	public Object addInvoice(TMemberInvoice tMemberInvoice, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			Member loginMember = (Member) session.getAttribute("member");
			tMemberInvoice.setMemberid(loginMember.getId());
			this.homePageService.addInvoice(tMemberInvoice);
			result.setStatus(Integer.valueOf(200));
			result.setMessage(tMemberInvoice.getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Integer.valueOf(500));
			result.setMessage("添加失败，请稍后再试");
		}
		return result;
	}

	@RequestMapping({ "/{proid}/{retid}/{invoiceid}/{count}/{addrid}/{orderNum}/toPay" })
	public Object toPay(@PathVariable Integer proid, @PathVariable Integer retid, @PathVariable String invoiceid,
			@PathVariable String addrid, @PathVariable String orderNum, @PathVariable Integer count,
			HttpSession session, Model model) {
		Member loginMember = (Member) session.getAttribute("member");

		TReturn ret = this.homePageService.getReturnById(retid);

		TOrder order = new TOrder();
		order.setAddressid(addrid.toString());
		order.setInvoice(invoiceid);
		order.setMemberid(loginMember.getId());
		order.setMoney(Integer.valueOf(ret.getSupportmoney().intValue() * count.intValue()));
		order.setOrdernum(orderNum);
		order.setProjectid(proid);
		order.setReturnid(retid);
		order.setRtncount(count);
		order.setStatus("0");
		LocalDateTime time = LocalDateTime.now();
		order.setCreatedate(time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		this.homePageService.addOrder(order);

		AliPayBean bean = new AliPayBean();
		bean.setOut_trade_no(orderNum);
		bean.setTotal_amount(ret.getSupportmoney().intValue() * count.intValue() + "");
		bean.setSubject(ret.getContent());
		bean.setBody(ret.getReturndesc());
		String result = "";
		try {
			result = AliPayUtil.pay(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("result", result);
		return "temp";
	}

	@RequestMapping({ "/paySuccess" })
	public String succes(HttpServletRequest request, Model model) {
		String out_trade_no = request.getParameter("out_trade_no");
		String timestamp = request.getParameter("timestamp");
		String total_amount = request.getParameter("total_amount");
		Map<String, Object> map = new HashMap();

		List<TOrder> order = this.homePageService.selectOrder(out_trade_no);
		for (TOrder tOrder : order) {
			TReturn tReturn = this.homePageService.getReturnById(tOrder.getReturnid());
			map.put("content", tReturn.getContent());
			map.put("rtndate", tReturn.getRtndate());
		}
		map.put("out_trade_no", out_trade_no);
		map.put("timestamp", timestamp);
		map.put("total_amount", total_amount);
		model.addAttribute("map", map);
		return "/project/paySuccess";
	}

	@RequestMapping({ "/notifyOrder" })
	public void notifyOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Map<String, String> params = new HashMap<String, String>();
//		Map requestParams = request.getParameterMap();
//		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//			String name = (String) iter.next();
//			String[] values = (String[]) requestParams.get(name);
//			String valueStr = "";
//			for (int i = 0; i < values.length; i++) {
//				valueStr = valueStr + values[i] + ",";
//			}
//			params.put(name, valueStr);
//		}
//		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, "UTF-8","RSA2");
		try {
			
			String trade_status = request.getParameter("trade_status");
			if (trade_status.equals("TRADE_SUCCESS")) {
				List<TOrder> order = this.homePageService.selectOrder(request.getParameter("out_trade_no"));
				System.err.println(request.getParameter("out_trade_no"));
				System.err.println(order);
				for (TOrder tOrder : order) {
					tOrder.setStatus("1");
					tOrder.setTradeno(request.getParameter("trade_no"));
					homePageService.updateOrder(tOrder);
					Project project = this.homePageService.getProsById(tOrder.getProjectid());
					Double supportmoney = Double.valueOf(project.getSupportmoney().doubleValue());
					Double totalamount = Double.valueOf(Double.parseDouble(request.getParameter("total_amount")));
					project.setSupportmoney(Long.valueOf((long) (supportmoney.doubleValue() + totalamount.doubleValue())));
					project.setSupporter(Integer.valueOf(project.getSupporter().intValue() + 1));
					Double completion = Double.valueOf(
					project.getSupportmoney().doubleValue() / project.getMoney().doubleValue() * 100.0D);
					project.setCompletion(Integer.valueOf(completion.intValue()));
					homePageService.updatePro(project);
					response.setContentType("text/html;charset=utf-8");
					PrintWriter writer = response.getWriter();
					writer.print("SUCCESS");
					writer.flush();
					writer.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *	 关注
	 * @return
	 */
	@RequestMapping("/follower")
	@ResponseBody
	public Object follower(Integer proId,HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			Member loginMember = (Member) session.getAttribute("member");
			List<TMemberProjectFollow> list = homePageService.getProjectFollowe(loginMember.getId(),proId);
			Project project = homePageService.getProsById(proId);
			Map<String, Object> map = new HashMap<>();
			TMemberProjectFollow mpf = new TMemberProjectFollow();
			if (list.isEmpty()) {
				//添加关注
				mpf.setMemberid(loginMember.getId());
				mpf.setProjectid(proId);
				homePageService.addFollower(mpf);
				//修改项目关注数量
				project.setFollower(project.getFollower() + 1);
				homePageService.updatePro(project);
				map.put("followers", project.getFollower());
			}else {
				map.put("followers", project.getFollower());
			}
			result.setStatus(200);
			result.setMessage(map);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
			result.setMessage("关注失败");
		}
		return result;
	}
	
	/**
	 * 	取消关注
	 * @return
	 */
	@RequestMapping("/cancel")
	@ResponseBody
	public Object cancelFollower(Integer proId,HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			Member loginMember = (Member) session.getAttribute("member");
			List<TMemberProjectFollow> list = homePageService.getProjectFollowe(loginMember.getId(),proId);
			Project project = homePageService.getProsById(proId);
			if (!list.isEmpty()) {
				if (homePageService.cancelFollow(loginMember.getId(),proId)) {
					project.setFollower(project.getFollower() - 1);
					homePageService.updatePro(project);
				}
			}
			result.setStatus(200);
			result.setMessage(project.getFollower());
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	/**
	 * 	查询全部
	 * 	redis缓存
	 * @param model
	 * @return
	 */
	@RequestMapping({"/{typeid}/{status}/{pageno}/projects"})
	public String getProsByTypeId(@PathVariable Integer typeid,@PathVariable Integer status,@PathVariable Integer pageno,Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		//总记录数、页数
		PageVo<Project> vo = new PageVo<>(pageno, 12);
		//分页查询
		vo = homePageService.getProsByPage(vo,typeid,status);
		for (Project project : vo.getData()) {
			List<TImgs> img = homePageService.getProImg(project.getId());
			if (!img.isEmpty()) {
				project.setImgs(img.get(0));
			}
			if (!StringUtils.isBlank(project.getDeploydate())) {
				LocalDateTime start = LocalDateTime.parse(project.getDeploydate(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				LocalDateTime end = start.plusDays(project.getDay());
				project.setEnddate(end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}else {
				project.setEnddate("即将开始");
			}
		}
		//查询对应的类型的项目总数:此处查询不能分页查询
		if (typeid == 0) {
			List<Project> list = homePageService.getPros();
			vo.setTotalsize(list.size());
		}else {
			List<ProjectType> all = homePageService.getProsIdByType(typeid);
			vo.setTotalsize(all.size());
		}
		List<Type> types = homePageService.typeList();
		map.put("types", types);
		map.put("page", vo);
		map.put("typeid", typeid);
		map.put("status", status);
		model.addAttribute("map", map);
		return "/project/projects";
	}
	
}
