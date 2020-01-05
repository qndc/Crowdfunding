package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.ProjectTag;
import com.atguigu.atcrowdfunding.bean.ProjectType;
import com.atguigu.atcrowdfunding.bean.TImgs;
import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TProjectTicket;
import com.atguigu.atcrowdfunding.bean.TReturn;
import com.atguigu.atcrowdfunding.bean.TTicket;
import com.atguigu.atcrowdfunding.bean.Tag;
import com.atguigu.atcrowdfunding.bean.Type;
import java.util.List;

public abstract interface AuthService
{
  public abstract TTicket selectTicketByPiid(String paramString);
  
  public abstract Project getProjectById(Integer paramInteger);
  
  public abstract List<TImgs> getImgsByProId(Integer paramInteger);
  
  public abstract List<TReturn> getRetByProId(Integer paramInteger);
  
  public abstract List<TProjectComp> getCompByProId(Integer paramInteger);
  
  public abstract List<MemberCert> getMemberCert(Integer paramInteger);
  
  public abstract Cert getCertById(Integer paramInteger);
  
  public abstract ProjectType getProType(Integer paramInteger);
  
  public abstract Type getType(Integer paramInteger);
  
  public abstract List<ProjectTag> getTagByProId(Integer paramInteger);
  
  public abstract Tag getTagById(Integer paramInteger);
  
  public abstract TProjectTicket getTicketByProId(Integer paramInteger);
}
