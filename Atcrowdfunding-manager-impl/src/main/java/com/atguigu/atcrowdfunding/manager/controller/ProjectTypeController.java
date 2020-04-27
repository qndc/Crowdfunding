package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.manager.service.ProjectTypeService;
import com.atguigu.atcrowdfunding.potal.service.QuartzService;
import com.atguigu.atcrowdfunding.util.AjaxResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjectTypeController {
	@Autowired
	private ProjectTypeService projectTypeService;
	@Autowired
	private QuartzService quartzService;

	@RequestMapping({ "/projecttype/toIndex" })
	public String toIndex() {
		return "/projecttype/index";
	}

	@RequestMapping({ "/project/list" })
	public String proList() {
		return "/project/list";
	}

	@RequestMapping({ "/projecttype/toAdd" })
	public String toAdd() {
		return "/projecttype/add";
	}

	@RequestMapping({ "/project/proList" })
	@ResponseBody
	public Object list() {
		AjaxResult result = new AjaxResult();
		try {
			List<Project> list = projectTypeService.selectPros();
			list.forEach(project -> {
				project.setProjectComp(projectTypeService.selectComp(project.getId()));
				project.setMember(projectTypeService.selectMember(project.getMemberid()));
				LocalDateTime dateTime = LocalDateTime.parse(project.getCreatedate(),
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				dateTime = dateTime.plusDays(project.getDay());
				String end = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				project.setEnddate(end);
				// 判断当前时间与结束时间的差值，如果现在时间已经过了当前时间就无法手动触发
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date edate = format.parse(end);
					Date ndate = format.parse(now);
					if (edate.getTime() > ndate.getTime()) {
						// 表示当前项目可手动触发审核
						project.setIsExpire(false);
					}
				} catch (ParseException e) {

					e.printStackTrace();
				}

			});
			result.setStatus(Integer.valueOf(200));
			result.setMessage(list);
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(500));
			result.setMessage("数据加载失败！");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 主动触发定时任务
	 * @param proid
	 * @param memberid
	 * @return
	 */
	@RequestMapping({ "/project/trigger" })
	@ResponseBody
	public Object trigger(@RequestParam Integer proid,@RequestParam Integer memberid) {
		try {
			quartzService.startSchedule(proid+"_jname", memberid+"_jgname");
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}

	@RequestMapping({ "/projecttype/toUpdate" })
	public String toUpdate(@RequestParam(required = true) Integer id, Map map) {
		Type type = this.projectTypeService.selectById(id);
		map.put("type", type);
		return "/projecttype/update";
	}

	@RequestMapping({ "/projecttype/all" })
	@ResponseBody
	public Object list(String content) {
		AjaxResult result = new AjaxResult();
		try {
			List<Type> types = this.projectTypeService.selectAll(content);

			result.setStatus(Integer.valueOf(200));
			result.setMessage(types);
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(500));
			result.setMessage("数据加载失败！");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping({ "/projecttype/addTag" })
	@ResponseBody
	public Object addTag(Integer typeId, String content) {
		AjaxResult result = new AjaxResult();
		try {
			this.projectTypeService.addTag(typeId, content);

			result.setStatus(Integer.valueOf(200));
			result.setMessage("标签添加成功!");
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(500));
			result.setMessage("标签添加失败！");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping({ "/projecttype/deleteTag" })
	@ResponseBody
	public Object deleteAll(Integer typeId, Integer tagId) {
		AjaxResult result = new AjaxResult();
		try {
			this.projectTypeService.deleteTag(typeId, tagId);

			result.setStatus(Integer.valueOf(200));
			result.setMessage("标签删除成功!");
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(500));
			result.setMessage("标签删除失败！");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping({ "/projecttype/deleteAll" })
	@ResponseBody
	public Object deleteAll(Integer[] id) {
		AjaxResult result = new AjaxResult();
		try {
			this.projectTypeService.deleteAll(id);

			result.setStatus(Integer.valueOf(200));
			result.setMessage("分类修改成功!");
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(500));
			result.setMessage("分类修改失败！");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping({ "/projecttype/updateType" })
	@ResponseBody
	public Object updateType(Type type) {
		AjaxResult result = new AjaxResult();
		try {
			this.projectTypeService.updateType(type);

			result.setStatus(Integer.valueOf(200));
			result.setMessage("分类修改成功!");
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(500));
			result.setMessage("分类修改失败！");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping({ "/projecttype/deleteById" })
	@ResponseBody
	public Object deleteById(Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			this.projectTypeService.deleteById(id);

			result.setStatus(Integer.valueOf(200));
			result.setMessage("分类删除成功!");
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(500));
			result.setMessage("分类删除失败！");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping({ "/projecttype/addType" })
	@ResponseBody
	public Object addType(Type type) {
		AjaxResult result = new AjaxResult();
		try {
			this.projectTypeService.insertType(type);

			result.setStatus(Integer.valueOf(200));
			result.setMessage("分类添加成功!");
		} catch (Exception e) {
			result.setStatus(Integer.valueOf(500));
			result.setMessage("分类添加失败！");
			e.printStackTrace();
		}
		return result;
	}
}
