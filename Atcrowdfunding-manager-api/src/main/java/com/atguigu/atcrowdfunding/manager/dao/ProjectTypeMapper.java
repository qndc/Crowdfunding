package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.ProjectType;
import java.util.List;

public abstract interface ProjectTypeMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(ProjectType paramProjectType);
  
  public abstract ProjectType selectByPrimaryKey(Integer paramInteger);
  
  public abstract List<ProjectType> selectAll();
  
  public abstract int updateByPrimaryKey(ProjectType paramProjectType);
  
  public abstract ProjectType selectByProId(Integer paramInteger);
  
  public abstract List<ProjectType> selectProsByType(Integer paramInteger);
}
