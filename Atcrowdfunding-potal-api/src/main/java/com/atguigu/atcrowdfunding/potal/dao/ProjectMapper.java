package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.Project;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface ProjectMapper
{
  int deleteByPrimaryKey(Integer paramInteger);
  
  int insert(Project paramProject);
  
  Project selectByPrimaryKey(Integer paramInteger);
  
  Project selectActrowIng(@Param("id") Integer paramInteger, @Param("status") String paramString);
  
  List<Project> selectAll();
  
  int updateByPrimaryKey(Project paramProject);

  List<Project> selectByPage(@Param("typeid")Integer typeid,@Param("status") Integer status,@Param("sort") Integer sort,@Param("keyWords")String keyWords,@Param("startIndex") Integer startIndex,@Param("pagesize") Integer pagesize);

  Integer selectNotByPage(@Param("typeid")Integer typeid, @Param("status")Integer status,@Param("keyWords") String keyWords);
}
