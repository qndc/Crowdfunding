package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.Type;
import java.util.List;

public abstract interface ProjectTypeService
{
  public abstract List<Type> selectAll(String paramString);
  
  public abstract void deleteById(Integer paramInteger);
  
  public abstract void insertType(Type paramType);
  
  public abstract Type selectById(Integer paramInteger);
  
  public abstract void updateType(Type paramType);
  
  public abstract void deleteAll(Integer[] paramArrayOfInteger);
  
  public abstract void deleteTag(Integer paramInteger1, Integer paramInteger2);
  
  public abstract void addTag(Integer paramInteger, String paramString);

public abstract List<Project> selectPros();

public abstract TProjectComp selectComp(Integer id);

public abstract Member selectMember(Integer memberid);
}
