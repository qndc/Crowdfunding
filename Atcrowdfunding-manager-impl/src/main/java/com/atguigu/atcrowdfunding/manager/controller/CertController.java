package com.atguigu.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.TAccountTypeCert;
import com.atguigu.atcrowdfunding.manager.service.CertService;
import com.atguigu.atcrowdfunding.util.AjaxResult;

@Controller
public class CertController {
	
	@Autowired
	private CertService certServiceImpl;
	
	@RequestMapping("/certtype/index")
	public String toCerttype() {
		return "/cert/certtype";
	}
	
	@RequestMapping("/cert/index")
	public String toCert() {
		return "/cert/index";
	}

	@RequestMapping("/cert/toAdd")
	public String toAdd() {
		return "/cert/add";
	}
	
	@RequestMapping("/cert/toUpdate")
	public String toUpdate(Integer id,Map map) {
		Cert cert = certServiceImpl.queryOne(id);
		map.put("cert", cert);
		return "/cert/update";
	}
	
	@RequestMapping("/certtype/addOrDel")
	@ResponseBody
	public Object addOrDel(Boolean flag,Integer accttype,Integer certid) {
		AjaxResult result = new AjaxResult();
		try {
			if (flag) {
				certServiceImpl.insertCerttype(accttype,certid);
			}else {
				certServiceImpl.deleteCerttype(accttype,certid);
			}
			result.setStatus(200);
			result.setMessage("操作成功！");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("操作失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/certtype/list")
	@ResponseBody
	public Object certtypeList() {
		AjaxResult result = new AjaxResult();
		try {
			List<TAccountTypeCert> accountTypeCertList = certServiceImpl.queryCertTypeList();
			List<Cert> certList = certServiceImpl.queryCerts();
			for (Cert cert : certList) {
				List<String> type = new ArrayList<>();
				for (TAccountTypeCert tAccountTypeCert : accountTypeCertList) {
					if (tAccountTypeCert.getCertid() == cert.getId()) {
						type.add(tAccountTypeCert.getAccttype());
					}
				}
				cert.setType(type);
			}
			result.setStatus(200);
			result.setMessage(certList);
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("数据加载失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/cert/update")
	@ResponseBody
	public Object update(Cert cert) {
		AjaxResult result = new AjaxResult();
		try {
			certServiceImpl.updateCert(cert);
			result.setStatus(200);
			result.setMessage("修改成功");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("修改失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/cert/add")
	@ResponseBody
	public Object add(Cert cert) {
		AjaxResult result = new AjaxResult();
		try {
			certServiceImpl.insertCert(cert);
			result.setStatus(200);
			result.setMessage("添加成功");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("加载失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("cert/list")
	@ResponseBody
	public Object list() {
		AjaxResult result = new AjaxResult();
		try {
			List<Cert> list = certServiceImpl.queryCerts();
			result.setStatus(200);
			result.setMessage(list);
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("加载失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/cert/delete")
	@ResponseBody
	public Object delete(@RequestParam(required = true)Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			certServiceImpl.deleteCert(id);
			result.setStatus(200);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("加载失败！");
			e.printStackTrace();
		}
		return result;
	}

}
