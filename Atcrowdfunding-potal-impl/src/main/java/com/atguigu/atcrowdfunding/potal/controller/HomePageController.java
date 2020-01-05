package com.atguigu.atcrowdfunding.potal.controller;

import com.alipay.api.internal.util.AlipaySignature;
import com.atguigu.atcrowdfunding.bean.AliPayBean;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TImgs;
import com.atguigu.atcrowdfunding.bean.TMemberAddress;
import com.atguigu.atcrowdfunding.bean.TMemberInvoice;
import com.atguigu.atcrowdfunding.bean.TOrder;
import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TReturn;
import com.atguigu.atcrowdfunding.potal.service.HomePageService;
import com.atguigu.atcrowdfunding.potal.service.QuartzService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.AliPayUtil;
import com.atguigu.atcrowdfunding.util.AlipayConfig;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/homepage" })
public class HomePageController {
	@Autowired
	private HomePageService homePageService;
	@Autowired
	private QuartzService quartzService;

	@RequestMapping({ "/{proId}/detailInfo" })
	public String detailInfo(@PathVariable Integer proId, Model model) {
		Project project = this.homePageService.getProsById(proId);

		List<TImgs> imgs = this.homePageService.getProDetailImg(proId);

		List<TReturn> returntList = this.homePageService.getReturnByProId(proId);

		TProjectComp comp = this.homePageService.getCompByProId(proId);

		LocalDateTime start = LocalDateTime.parse("2019-12-26 11:29:40",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime end = start.plusDays(30L);
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

		model.addAttribute("project", project);
		model.addAttribute("imgs", imgs);
		model.addAttribute("returntList", returntList);
		model.addAttribute("comp", comp);

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
}