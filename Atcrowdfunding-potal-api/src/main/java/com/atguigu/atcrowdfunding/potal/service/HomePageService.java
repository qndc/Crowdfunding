package com.atguigu.atcrowdfunding.potal.service;

import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.ProjectType;
import com.atguigu.atcrowdfunding.bean.TImgs;
import com.atguigu.atcrowdfunding.bean.TMemberAddress;
import com.atguigu.atcrowdfunding.bean.TMemberInvoice;
import com.atguigu.atcrowdfunding.bean.TOrder;
import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TReturn;
import com.atguigu.atcrowdfunding.bean.Type;
import java.util.List;

public abstract interface HomePageService
{
  public abstract List<Type> typeList();
  
  public abstract List<ProjectType> getProsIdByType(Integer paramInteger);
  
  public abstract Project getProsById(Integer paramInteger);
  
  public abstract List<TImgs> getProImg(Integer paramInteger);
  
  public abstract List<TImgs> getProDetailImg(Integer paramInteger);
  
  public abstract List<TReturn> getReturnByProId(Integer paramInteger);
  
  public abstract TProjectComp getCompByProId(Integer paramInteger);
  
  public abstract TReturn getReturnById(Integer paramInteger);
  
  public abstract void addAddress(TMemberAddress paramTMemberAddress);
  
  public abstract List<TMemberAddress> getAddrByMemberId(Integer paramInteger);
  
  public abstract void addInvoice(TMemberInvoice paramTMemberInvoice);
  
  public abstract void addOrder(TOrder paramTOrder);
  
  public abstract List<TOrder> selectOrder(String paramString);
  
  public abstract void updatePro(Project paramProject);
  
  public abstract void updateOrder(TOrder paramTOrder);
}


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\service\HomePageService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */