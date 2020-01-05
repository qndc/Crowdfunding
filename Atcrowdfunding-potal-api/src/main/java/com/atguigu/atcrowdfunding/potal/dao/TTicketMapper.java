package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.TTicket;
import com.atguigu.atcrowdfunding.bean.TTicketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface TTicketMapper
{
  public abstract int countByExample(TTicketExample paramTTicketExample);
  
  public abstract int deleteByExample(TTicketExample paramTTicketExample);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(TTicket paramTTicket);
  
  public abstract int insertSelective(TTicket paramTTicket);
  
  public abstract List<TTicket> selectByExample(TTicketExample paramTTicketExample);
  
  public abstract TTicket selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(@Param("record") TTicket paramTTicket, @Param("example") TTicketExample paramTTicketExample);
  
  public abstract int updateByExample(@Param("record") TTicket paramTTicket, @Param("example") TTicketExample paramTTicketExample);
  
  public abstract int updateByPrimaryKeySelective(TTicket paramTTicket);
  
  public abstract int updateByPrimaryKey(TTicket paramTTicket);
}


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\dao\TTicketMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */