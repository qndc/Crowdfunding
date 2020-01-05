package com.atguigu.atcrowdfunding.manager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.atcrowdfunding.util.AjaxResult;

@Controller
public class ProcessController {
	
	@Autowired
	private RepositoryService repositoryService;

	/**
	 * 跳转到流程管理页面
	 * @return
	 */
	@RequestMapping("/process/index")
	public String index() {
		return "/process/index";
	}
	
	/**
	 * 跳转到流程管理页面
	 * @return
	 */
	@RequestMapping("/process/list")
	@ResponseBody
	public Object list() {
		AjaxResult result = new AjaxResult();
		try {
			ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
			List<ProcessDefinition> list = processDefinitionQuery.list();
			List<Map<String,Object>> processes = new ArrayList<Map<String,Object>>();
			for (ProcessDefinition processDefinition : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", processDefinition.getId());
				map.put("name", processDefinition.getName());
				map.put("key", processDefinition.getKey());
				map.put("version", processDefinition.getVersion());
				processes.add(map);
			}
			result.setStatus(200);
			result.setMessage(processes);
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("查询失败！");
		}
		return result;
	}
	
	/**
	 * 部署流程
	 * @return
	 */
	@RequestMapping("/process/deploy")
	@ResponseBody
	public Object deploy(HttpServletRequest request) {
		AjaxResult result = new AjaxResult();
		try {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartHttpServletRequest.getFile("processDefFile");
			repositoryService.createDeployment().addInputStream(file.getOriginalFilename(), file.getInputStream()).deploy();
			result.setStatus(200);
			result.setMessage("部署成功");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("部署失败！");
		}
		return result;
	}
	
	/**
	 * 删除部署流程
	 * @return
	 */
	@RequestMapping("/process/delete")
	@ResponseBody
	public Object delete(String id) {
		AjaxResult result = new AjaxResult();
		try {
			ProcessDefinition singleResult = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
			repositoryService.deleteDeployment(singleResult.getDeploymentId(), true);	//true代表级联删除，所有的相关依赖都删除
			result.setStatus(200);
			result.setMessage("删除成功");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("删除失败！！！");
		}
		return result;
	}

	
	/**
	 * 	查看部署流程的图片
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/process/showImg")
	@ResponseBody
	public String showImg(String id,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
		InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024*8];
			while ((count = resourceAsStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, count);
				outputStream.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceAsStream.close();
			outputStream.close();
		}
		
		return "ok";
		
	}
}
