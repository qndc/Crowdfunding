package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.TProjectTicket;
import com.atguigu.atcrowdfunding.bean.TProjectTicketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface TProjectTicketMapper
{
  public abstract int countByExample(TProjectTicketExample paramTProjectTicketExample);
  
  public abstract int deleteByExample(TProjectTicketExample paramTProjectTicketExample);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(TProjectTicket paramTProjectTicket);
  
  public abstract int insertSelective(TProjectTicket paramTProjectTicket);
  
  public abstract List<TProjectTicket> selectByExample(TProjectTicketExample paramTProjectTicketExample);
  
  public abstract TProjectTicket selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(@Param("record") TProjectTicket paramTProjectTicket, @Param("example") TProjectTicketExample paramTProjectTicketExample);
  
  public abstract int updateByExample(@Param("record") TProjectTicket paramTProjectTicket, @Param("example") TProjectTicketExample paramTProjectTicketExample);
  
  public abstract int updateByPrimaryKeySelective(TProjectTicket paramTProjectTicket);
  
  public abstract int updateByPrimaryKey(TProjectTicket paramTProjectTicket);
}


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\dao\TProjectTicketMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */