package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.ProjectTag;
import java.util.List;

public abstract interface ProjectTagMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(ProjectTag paramProjectTag);
  
  public abstract ProjectTag selectByPrimaryKey(Integer paramInteger);
  
  public abstract List<ProjectTag> selectAll();
  
  public abstract int updateByPrimaryKey(ProjectTag paramProjectTag);
  
  public abstract List<ProjectTag> selectByProId(Integer paramInteger);
}
