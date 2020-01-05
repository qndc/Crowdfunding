package com.atguigu.atcrowdfunding.potal.service;

import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TReturn;
import com.atguigu.atcrowdfunding.bean.Tag;
import com.atguigu.atcrowdfunding.bean.Type;
import java.util.List;

public abstract interface AtcrowdfundingService
{
  public abstract List<Type> typeList();
  
  public abstract List<Tag> getTagById(Integer paramInteger);
  
  public abstract TReturn getRetById(Integer paramInteger);
  
  public abstract void updateReturn(TReturn paramTReturn);
  
  public abstract void delReturn(Integer paramInteger);
  
  public abstract void addComp(TProjectComp paramTProjectComp);
}


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\service\AtcrowdfundingService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */