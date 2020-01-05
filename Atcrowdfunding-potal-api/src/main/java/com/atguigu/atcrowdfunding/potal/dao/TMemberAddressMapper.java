package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.TMemberAddress;
import com.atguigu.atcrowdfunding.bean.TMemberAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface TMemberAddressMapper
{
  public abstract int countByExample(TMemberAddressExample paramTMemberAddressExample);
  
  public abstract int deleteByExample(TMemberAddressExample paramTMemberAddressExample);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(TMemberAddress paramTMemberAddress);
  
  public abstract int insertSelective(TMemberAddress paramTMemberAddress);
  
  public abstract List<TMemberAddress> selectByExample(TMemberAddressExample paramTMemberAddressExample);
  
  public abstract TMemberAddress selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(@Param("record") TMemberAddress paramTMemberAddress, @Param("example") TMemberAddressExample paramTMemberAddressExample);
  
  public abstract int updateByExample(@Param("record") TMemberAddress paramTMemberAddress, @Param("example") TMemberAddressExample paramTMemberAddressExample);
  
  public abstract int updateByPrimaryKeySelective(TMemberAddress paramTMemberAddress);
  
  public abstract int updateByPrimaryKey(TMemberAddress paramTMemberAddress);
}


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\dao\TMemberAddressMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */