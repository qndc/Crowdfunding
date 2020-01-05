package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.TMemberInvoice;
import com.atguigu.atcrowdfunding.bean.TMemberInvoiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface TMemberInvoiceMapper
{
  public abstract int countByExample(TMemberInvoiceExample paramTMemberInvoiceExample);
  
  public abstract int deleteByExample(TMemberInvoiceExample paramTMemberInvoiceExample);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(TMemberInvoice paramTMemberInvoice);
  
  public abstract int insertSelective(TMemberInvoice paramTMemberInvoice);
  
  public abstract List<TMemberInvoice> selectByExample(TMemberInvoiceExample paramTMemberInvoiceExample);
  
  public abstract TMemberInvoice selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(@Param("record") TMemberInvoice paramTMemberInvoice, @Param("example") TMemberInvoiceExample paramTMemberInvoiceExample);
  
  public abstract int updateByExample(@Param("record") TMemberInvoice paramTMemberInvoice, @Param("example") TMemberInvoiceExample paramTMemberInvoiceExample);
  
  public abstract int updateByPrimaryKeySelective(TMemberInvoice paramTMemberInvoice);
  
  public abstract int updateByPrimaryKey(TMemberInvoice paramTMemberInvoice);
}


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\dao\TMemberInvoiceMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */