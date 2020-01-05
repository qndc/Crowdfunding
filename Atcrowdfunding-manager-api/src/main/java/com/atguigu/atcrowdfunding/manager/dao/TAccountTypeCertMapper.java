package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.TAccountTypeCert;
import com.atguigu.atcrowdfunding.bean.TAccountTypeCertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface TAccountTypeCertMapper
{
  public abstract int countByExample(TAccountTypeCertMapper paramTAccountTypeCertMapper);
  
  public abstract int deleteByExample(TAccountTypeCertExample paramTAccountTypeCertExample);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(TAccountTypeCert paramTAccountTypeCert);
  
  public abstract int insertSelective(TAccountTypeCert paramTAccountTypeCert);
  
  public abstract List<TAccountTypeCert> selectByExample(TAccountTypeCertExample paramTAccountTypeCertExample);
  
  public abstract TAccountTypeCert selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(@Param("record") TAccountTypeCert paramTAccountTypeCert, @Param("example") TAccountTypeCertExample paramTAccountTypeCertExample);
  
  public abstract int updateByExample(@Param("record") TAccountTypeCert paramTAccountTypeCert, @Param("example") TAccountTypeCertExample paramTAccountTypeCertExample);
  
  public abstract int updateByPrimaryKeySelective(TAccountTypeCert paramTAccountTypeCert);
  
  public abstract int updateByPrimaryKey(TAccountTypeCert paramTAccountTypeCert);
}
