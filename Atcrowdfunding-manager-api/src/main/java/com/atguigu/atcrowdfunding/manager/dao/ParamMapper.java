package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Param;
import java.util.List;

public abstract interface ParamMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(Param paramParam);
  
  public abstract Param selectByPrimaryKey(Integer paramInteger);
  
  public abstract List<Param> selectAll();
  
  public abstract int updateByPrimaryKey(Param paramParam);
}
