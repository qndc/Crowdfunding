package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.Type;
import java.util.List;

import org.apache.ibatis.annotations.Select;

public abstract interface TypeMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(Type paramType);
  
  public abstract Type selectByPrimaryKey(Integer paramInteger);
  
  public abstract List<Type> selectAll();
  
  public abstract List<Type> selectByQuery(String paramString);
  
  public abstract int updateByPrimaryKey(Type paramType);

  @Select("select * from t_project")
  public abstract List<Project> selectPros();

  @Select("select * from t_project_comp where proid = #{id}")
  public abstract TProjectComp selectComp(Integer id);

  @Select("select * from t_member where id = #{memberid}")
  public abstract Member selectMMember(Integer memberid);
  
  
}
