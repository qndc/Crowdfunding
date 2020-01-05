package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.CertExample;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface TCertMapper
{
  public abstract int countByExample(CertExample paramCertExample);
  
  public abstract int deleteByExample(CertExample paramCertExample);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(Cert paramCert);
  
  public abstract int insertSelective(Cert paramCert);
  
  public abstract List<Cert> selectByExample(CertExample paramCertExample);
  
  public abstract Cert selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(@Param("record") Cert paramCert, @Param("example") CertExample paramCertExample);
  
  public abstract int updateByExample(@Param("record") Cert paramCert, @Param("example") CertExample paramCertExample);
  
  public abstract int updateByPrimaryKeySelective(Cert paramCert);
  
  public abstract int updateByPrimaryKey(Cert paramCert);
  
  public abstract void insertMemberCert(MemberCert paramMemberCert);
  
  public abstract List<MemberCert> selectMemberCert(Integer paramInteger);
}
