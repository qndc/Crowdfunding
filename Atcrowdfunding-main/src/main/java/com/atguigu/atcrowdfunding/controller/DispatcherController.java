package com.atguigu.atcrowdfunding.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.h2.util.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.ProjectType;
import com.atguigu.atcrowdfunding.bean.TImgs;
import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.potal.service.HomePageService;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.atguigu.atcrowdfunding.util.SmsUtil;
import com.google.gson.Gson;

@Controller
public class DispatcherController {

	@Autowired
	private UserService userService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private HomePageService hpService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	@RequestMapping({ "/index" })
	public Object index(Model model) {
		String indexPros = (String)redisTemplate.opsForValue().get(Const.INDEXPROS);
		//redis取出来的为空，就查询数据库
		if (StringUtils.isBlank(indexPros)) {
			List<Type> types = hpService.typeList();
			for (Type type : types) {
				List<ProjectType> pTypeS = hpService.getProsIdByType(type.getId());
				List<Project> pros = new ArrayList<>();
				for (ProjectType projectType : pTypeS) {
					Project pro = this.hpService.getProsById(projectType.getProjectid());
					if (pro != null) {
						List<TImgs> imgs = this.hpService.getProImg(pro.getId());
						for (TImgs img : imgs) {
							pro.setImgs(img);
						}
						LocalDateTime start = LocalDateTime.parse(pro.getDeploydate(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
						LocalDateTime end = start.plusDays(pro.getDay());
						pro.setEnddate(end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
						pros.add(pro);
					}
				}
//				type.setPros(pros.subList(0, 3));
				type.setPros(pros);
			}
			redisTemplate.opsForValue().set(Const.INDEXPROS, new Gson().toJson(types));
			redisTemplate.expire(Const.INDEXPROS, Const.DEFAULT_EXPIRE, TimeUnit.SECONDS);
			model.addAttribute("types", types);
		}else {
			List<Type> types = JSONObject.parseArray(indexPros,Type.class);
			model.addAttribute("types", types);
		}
		return "iindex";
	}

	/**
	 * 	点击主页登录按钮，如果有在cookie中记住登录，就自动登录 没有记住登录就跳转到登录页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpSession session) {
		boolean needLogin = true;
		String logincode = null;
		Map<String, Object> map = new HashMap<>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			// 判断cookie是否包含登录信息
			for (Cookie cookie : cookies) {
				if ("logincode".equals(cookie.getName())) {
					logincode = cookie.getValue();
					String[] loginInfo = logincode.split("&");
					// 将用户名密码类型以键值对方式保存
					for (String info : loginInfo) {
						String[] split = info.split("=");
						map.put(split[0], split[1]);
					}

					// 如果用户类型是admin,就跳转到管理员页面
					if ("admin".equals(map.get("type"))) {
						User user = userService.queryUserlogin(map);
						if (user != null) {
							session.setAttribute(Const.LOGIN_USER, user);
							// 查询用户权限
							getPermissions(user, session);
							needLogin = false;
						}
					} else if ("member".equals(map.get("type"))) {
						Member member = memberService.queryMemberlogin(map);
						if (member != null) {
							session.setAttribute(Const.LOGIN_MEMBER, member);
							needLogin = false;
						}
					}
				}
			}
		}

		if (needLogin == false) {
			if ("admin".equals(map.get("type"))) {
				return "redirect:/main.htm";
			} else if ("member".equals(map.get("type"))) {
				return "redirect:/index.htm";
			}
		}
		return "login";
	}

	@RequestMapping("/reg")
	public String reg() {
		return "reg";
	}

	@RequestMapping("/main")
	public String main() {
		return "main";
	}

	@RequestMapping("/member")
	public String member() {
		return "member/member";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.htm";
	}

	/**
	 * 	记住两周，自动登录
	 * @param loginacct
	 * @param userpswd
	 * @param ftype
	 * @param rememberme
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping("/doLogin")
	@ResponseBody
	public Object doLogin(String loginacct, String userpswd, String ftype, Integer rememberme, HttpSession session,
			HttpServletResponse response) {
		AjaxResult result = new AjaxResult();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loginacct", loginacct);
			paramMap.put("userpswd", MD5Util.getMD5(userpswd));
			paramMap.put("type", ftype);
			if ("member".equals(ftype)) {
				Member member = memberService.queryMemberlogin(paramMap);

				if (member == null) {
					result.setStatus(500);
					result.setMessage("登录失败！请检查账户及密码！");
				} else {
					session.setAttribute(Const.LOGIN_MEMBER, member);
					if (rememberme == 1) {
						String logincode = "loginacct=" + member.getLoginacct() + "&userpswd=" + member.getUserpswd()
								+ "&type=" + ftype;
						Cookie cookie = new Cookie("logincode", logincode);
						cookie.setMaxAge(60 * 60 * 24 * 14);
						cookie.setPath("/"); // 表示任何请求路径都可以访问cookie
						response.addCookie(cookie);
					}
					result.setStatus(200);
					result.setMessage(ftype);
				}
			} else if ("admin".equals(ftype)) {
				User user = userService.queryUserlogin(paramMap);

				if (user == null) {
					result.setStatus(500);
					result.setMessage("不存在该管理员！");
				} else {
					// session中保存登录用户信息
					session.setAttribute(Const.LOGIN_USER, user);
					if (rememberme == 1) {
						String logincode = "loginacct=" + user.getLoginacct() + "&userpswd=" + user.getUserpswd()
								+ "&type=" + ftype;
						Cookie cookie = new Cookie("logincode", logincode);
						cookie.setMaxAge(60 * 60 * 24 * 14);
						cookie.setPath("/"); // 表示任何请求路径都可以访问cookie
						response.addCookie(cookie);
					}
					// 查询用户权限
					getPermissions(user, session);
					result.setStatus(200);
					result.setMessage(ftype);
				}
			}
			return result;
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("登录失败!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询用户权限方法抽取
	 * 
	 * @param user
	 * @param session
	 */
	private void getPermissions(User user, HttpSession session) {
		// 根据用户id查询用户拥有的权限
		List<Permission> list = userService.selectPermissionByUserId(user.getId());
		// 菜单根节点
		Permission permissionroot = null;
		Map<Integer, Permission> map = new HashMap<Integer, Permission>();
		Set<String> uris = new HashSet<String>();
		for (Permission permission : list) {
			map.put(permission.getId(), permission);
			if (StringUtils.isNotEmpty(permission.getUrl())) {
				uris.add("/" + permission.getUrl());
			}
		}
		session.setAttribute(Const.LOGIN_AUTH_PERMISSION, uris);
		for (Permission permission : list) {
			// 假设当前元素为子菜单
			Permission child = permission;
			if (child.getPid() == 0) {
				permissionroot = permission;
			} else {
				Permission parent = map.get(child.getPid());
				parent.getChildren().add(child);
			}
		}
		session.setAttribute("permissionroot", permissionroot);
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param tel
	 * @param session
	 * @return
	 */
	@RequestMapping("/sendCode")
	@ResponseBody
	public Object sendCode(String tel, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			String code = randomCode();

			Map<String, String> map = new HashMap();
			map.put("code", code);

			String bizId = SmsUtil.sendSms("SMS_180355463", new Gson().toJson(map), tel, "我的学习分享");

			SmsUtil.querySendDetails(bizId);

			session.setAttribute("code", code);
			result.setStatus(Integer.valueOf(200));
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Integer.valueOf(500));
		}
		return result;
	}

	/**
	 * 注册用户
	 * @param member
	 * @param session
	 * @return
	 */
	@RequestMapping("/signUp" )
	@ResponseBody
	public Object signUp(Member member, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			String smsCode = (String) session.getAttribute("code");
			if ((smsCode.equals(member.getCode())) && (StringUtils.isNotBlank(smsCode))) {
				Member exist = this.memberService.selectMemberByTel(member.getTel());
				if (exist == null) {
					member.setAuthstatus("0");
					member.setUsertype("0");
					member.setLoginacct(member.getTel());
					member.setUsername(member.getTel());
					member.setUserpswd(MD5Util.getMD5(member.getUserpswd()));
					this.memberService.insertMember(member);
					result.setStatus(Integer.valueOf(200));
				} else {
					result.setStatus(Integer.valueOf(500));
					result.setMessage("当前号码已注册");
				}
			} else {
				result.setStatus(Integer.valueOf(500));
				result.setMessage("验证码错误，请重新输入");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Integer.valueOf(500));
			result.setMessage("注册失败，请稍后再试");
		}
		return result;
	}

	/**
	 * 校验当前号码是否已经注册
	 * 
	 * @param tel
	 * @return
	 */
	@RequestMapping("/check")
	@ResponseBody
	public Object check(String tel) {
		AjaxResult result = new AjaxResult();
		try {
			Member member = this.memberService.selectMemberByTel(tel);
			if (member == null) {
				result.setStatus(Integer.valueOf(200));
				result.setMessage("当前号码可用");
			} else {
				result.setStatus(Integer.valueOf(500));
				result.setMessage("当前号码已注册");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Integer.valueOf(500));
			result.setMessage("校验失败");
		}
		return result;
	}

	/**
	 * 随机生成四位验证码
	 * 
	 * @return
	 */
	public static String randomCode() {
		StringBuilder str = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			str.append(random.nextInt(10));
		}
		return str.toString();
	}
	
	
}
