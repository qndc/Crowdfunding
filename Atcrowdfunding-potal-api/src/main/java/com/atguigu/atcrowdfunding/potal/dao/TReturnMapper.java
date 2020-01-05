package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.TReturn;
import com.atguigu.atcrowdfunding.bean.TReturnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface TReturnMapper
{
  public abstract int countByExample(TReturnExample paramTReturnExample);
  
  public abstract int deleteByExample(TReturnExample paramTReturnExample);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(TReturn paramTReturn);
  
  public abstract int insertSelective(TReturn paramTReturn);
  
  public abstract List<TReturn> selectByExample(TReturnExample paramTReturnExample);
  
  public abstract TReturn selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(@Param("record") TReturn paramTReturn, @Param("example") TReturnExample paramTReturnExample);
  
  public abstract int updateByExample(@Param("record") TReturn paramTReturn, @Param("example") TReturnExample paramTReturnExample);
  
  public abstract int updateByPrimaryKeySelective(TReturn paramTReturn);
  
  public abstract int updateByPrimaryKey(TReturn paramTReturn);
}


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\dao\TReturnMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */