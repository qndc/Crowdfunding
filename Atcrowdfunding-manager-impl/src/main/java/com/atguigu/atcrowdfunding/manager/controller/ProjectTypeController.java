package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.manager.service.ProjectTypeService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjectTypeController
{
  @Autowired
  private ProjectTypeService projectTypeService;
  
  @RequestMapping({"/projecttype/toIndex"})
  public String toIndex()
  {
    return "/projecttype/index";
  }
  
  @RequestMapping({"/projecttype/toAdd"})
  public String toAdd()
  {
    return "/projecttype/add";
  }
  
  @RequestMapping({"/projecttype/toUpdate"})
  public String toUpdate(@RequestParam(required=true) Integer id, Map map)
  {
    Type type = this.projectTypeService.selectById(id);
    map.put("type", type);
    return "/projecttype/update";
  }
  
  @RequestMapping({"/projecttype/all"})
  @ResponseBody
  public Object list(String content)
  {
    AjaxResult result = new AjaxResult();
    try
    {
      List<Type> types = this.projectTypeService.selectAll(content);
      
      result.setStatus(Integer.valueOf(200));
      result.setMessage(types);
    }
    catch (Exception e)
    {
      result.setStatus(Integer.valueOf(500));
      result.setMessage("数据加载失败！");
      e.printStackTrace();
    }
    return result;
  }
  
  @RequestMapping({"/projecttype/addTag"})
  @ResponseBody
  public Object addTag(Integer typeId, String content)
  {
    AjaxResult result = new AjaxResult();
    try
    {
      this.projectTypeService.addTag(typeId, content);
      
      result.setStatus(Integer.valueOf(200));
      result.setMessage("标签添加成功!");
    }
    catch (Exception e)
    {
      result.setStatus(Integer.valueOf(500));
      result.setMessage("标签添加失败！");
      e.printStackTrace();
    }
    return result;
  }
  
  @RequestMapping({"/projecttype/deleteTag"})
  @ResponseBody
  public Object deleteAll(Integer typeId, Integer tagId)
  {
    AjaxResult result = new AjaxResult();
    try
    {
      this.projectTypeService.deleteTag(typeId, tagId);
      
      result.setStatus(Integer.valueOf(200));
      result.setMessage("标签删除成功!");
    }
    catch (Exception e)
    {
      result.setStatus(Integer.valueOf(500));
      result.setMessage("标签删除失败！");
      e.printStackTrace();
    }
    return result;
  }
  
  @RequestMapping({"/projecttype/deleteAll"})
  @ResponseBody
  public Object deleteAll(Integer[] id)
  {
    AjaxResult result = new AjaxResult();
    try
    {
      this.projectTypeService.deleteAll(id);
      
      result.setStatus(Integer.valueOf(200));
      result.setMessage("分类修改成功!");
    }
    catch (Exception e)
    {
      result.setStatus(Integer.valueOf(500));
      result.setMessage("分类修改失败！");
      e.printStackTrace();
    }
    return result;
  }
  
  @RequestMapping({"/projecttype/updateType"})
  @ResponseBody
  public Object updateType(Type type)
  {
    AjaxResult result = new AjaxResult();
    try
    {
      this.projectTypeService.updateType(type);
      
      result.setStatus(Integer.valueOf(200));
      result.setMessage("分类修改成功!");
    }
    catch (Exception e)
    {
      result.setStatus(Integer.valueOf(500));
      result.setMessage("分类修改失败！");
      e.printStackTrace();
    }
    return result;
  }
  
  @RequestMapping({"/projecttype/deleteById"})
  @ResponseBody
  public Object deleteById(Integer id)
  {
    AjaxResult result = new AjaxResult();
    try
    {
      this.projectTypeService.deleteById(id);
      
      result.setStatus(Integer.valueOf(200));
      result.setMessage("分类删除成功!");
    }
    catch (Exception e)
    {
      result.setStatus(Integer.valueOf(500));
      result.setMessage("分类删除失败！");
      e.printStackTrace();
    }
    return result;
  }
  
  @RequestMapping({"/projecttype/addType"})
  @ResponseBody
  public Object addType(Type type)
  {
    AjaxResult result = new AjaxResult();
    try
    {
      this.projectTypeService.insertType(type);
      
      result.setStatus(Integer.valueOf(200));
      result.setMessage("分类添加成功!");
    }
    catch (Exception e)
    {
      result.setStatus(Integer.valueOf(500));
      result.setMessage("分类添加失败！");
      e.printStackTrace();
    }
    return result;
  }
}
