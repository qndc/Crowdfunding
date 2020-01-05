package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Type;
import java.util.List;

public abstract interface TypeMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(Type paramType);
  
  public abstract Type selectByPrimaryKey(Integer paramInteger);
  
  public abstract List<Type> selectAll();
  
  public abstract List<Type> selectByQuery(String paramString);
  
  public abstract int updateByPrimaryKey(Type paramType);
}
