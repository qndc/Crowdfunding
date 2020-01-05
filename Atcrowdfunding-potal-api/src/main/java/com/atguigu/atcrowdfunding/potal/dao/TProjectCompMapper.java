package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TProjectCompExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface TProjectCompMapper
{
  public abstract int countByExample(TProjectCompExample paramTProjectCompExample);
  
  public abstract int deleteByExample(TProjectCompExample paramTProjectCompExample);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(TProjectComp paramTProjectComp);
  
  public abstract int insertSelective(TProjectComp paramTProjectComp);
  
  public abstract List<TProjectComp> selectByExample(TProjectCompExample paramTProjectCompExample);
  
  public abstract TProjectComp selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(@Param("record") TProjectComp paramTProjectComp, @Param("example") TProjectCompExample paramTProjectCompExample);
  
  public abstract int updateByExample(@Param("record") TProjectComp paramTProjectComp, @Param("example") TProjectCompExample paramTProjectCompExample);
  
  public abstract int updateByPrimaryKeySelective(TProjectComp paramTProjectComp);
  
  public abstract int updateByPrimaryKey(TProjectComp paramTProjectComp);
}