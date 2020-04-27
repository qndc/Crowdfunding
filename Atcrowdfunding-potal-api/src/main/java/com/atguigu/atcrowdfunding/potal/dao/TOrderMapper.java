package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.TOrder;
import com.atguigu.atcrowdfunding.bean.TOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public abstract interface TOrderMapper
{
  public abstract int countByExample(TOrderExample paramTOrderExample);
  
  public abstract int deleteByExample(TOrderExample paramTOrderExample);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(TOrder paramTOrder);
  
  public abstract int insertSelective(TOrder paramTOrder);
  
  public abstract List<TOrder> selectByExample(TOrderExample paramTOrderExample);
  
  public abstract TOrder selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(@Param("record") TOrder paramTOrder, @Param("example") TOrderExample paramTOrderExample);
  
  public abstract int updateByExample(@Param("record") TOrder paramTOrder, @Param("example") TOrderExample paramTOrderExample);
  
  public abstract int updateByPrimaryKeySelective(TOrder paramTOrder);
  
  public abstract int updateByPrimaryKey(TOrder paramTOrder);

  @Select("select * from t_order where ordernum = #{ordernum}")
  public abstract TOrder selectByOrderNum(String ordernum);
}