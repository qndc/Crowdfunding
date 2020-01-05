package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import com.atguigu.atcrowdfunding.bean.TAccountTypeCert;
import java.util.List;

public abstract interface CertService
{
  public abstract List<Cert> queryCerts();
  
  public abstract void deleteCert(Integer paramInteger);
  
  public abstract void insertCert(Cert paramCert);
  
  public abstract Cert queryOne(Integer paramInteger);
  
  public abstract void updateCert(Cert paramCert);
  
  public abstract List<TAccountTypeCert> queryCertTypeList();
  
  public abstract void insertCerttype(Integer paramInteger1, Integer paramInteger2);
  
  public abstract void deleteCerttype(Integer paramInteger1, Integer paramInteger2);
  
  public abstract List<Cert> queryCertByType(String paramString);
  
  public abstract void insertMemberCert(MemberCert paramMemberCert);
  
  public abstract List<MemberCert> selectMemberCert(Integer paramInteger);
}
