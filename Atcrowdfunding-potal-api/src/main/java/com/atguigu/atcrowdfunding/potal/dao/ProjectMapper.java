package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.Project;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface ProjectMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(Project paramProject);
  
  public abstract Project selectByPrimaryKey(Integer paramInteger);
  
  public abstract Project selectActrowIng(@Param("id") Integer paramInteger, @Param("status") String paramString);
  
  public abstract List<Project> selectAll();
  
  public abstract int updateByPrimaryKey(Project paramProject);
}
