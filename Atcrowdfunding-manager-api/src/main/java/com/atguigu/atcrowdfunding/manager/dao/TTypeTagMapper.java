package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.TTypeTag;
import com.atguigu.atcrowdfunding.bean.TTypeTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface TTypeTagMapper
{
  public abstract int countByExample(TTypeTagExample paramTTypeTagExample);
  
  public abstract int deleteByExample(TTypeTagExample paramTTypeTagExample);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(TTypeTag paramTTypeTag);
  
  public abstract int insertSelective(TTypeTag paramTTypeTag);
  
  public abstract List<TTypeTag> selectByExample(TTypeTagExample paramTTypeTagExample);
  
  public abstract TTypeTag selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(@Param("record") TTypeTag paramTTypeTag, @Param("example") TTypeTagExample paramTTypeTagExample);
  
  public abstract int updateByExample(@Param("record") TTypeTag paramTTypeTag, @Param("example") TTypeTagExample paramTTypeTagExample);
  
  public abstract int updateByPrimaryKeySelective(TTypeTag paramTTypeTag);
  
  public abstract int updateByPrimaryKey(TTypeTag paramTTypeTag);
}
