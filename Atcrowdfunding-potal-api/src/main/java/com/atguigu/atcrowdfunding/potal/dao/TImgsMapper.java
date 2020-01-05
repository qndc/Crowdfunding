package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.TImgs;
import com.atguigu.atcrowdfunding.bean.TImgsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface TImgsMapper
{
  public abstract int countByExample(TImgsExample paramTImgsExample);
  
  public abstract int deleteByExample(TImgsExample paramTImgsExample);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(TImgs paramTImgs);
  
  public abstract int insertSelective(TImgs paramTImgs);
  
  public abstract List<TImgs> selectByExample(TImgsExample paramTImgsExample);
  
  public abstract TImgs selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(@Param("record") TImgs paramTImgs, @Param("example") TImgsExample paramTImgsExample);
  
  public abstract int updateByExample(@Param("record") TImgs paramTImgs, @Param("example") TImgsExample paramTImgsExample);
  
  public abstract int updateByPrimaryKeySelective(TImgs paramTImgs);
  
  public abstract int updateByPrimaryKey(TImgs paramTImgs);
}


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\dao\TImgsMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */