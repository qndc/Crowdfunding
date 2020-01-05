package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Message;
import java.util.List;

public abstract interface MessageMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(Message paramMessage);
  
  public abstract Message selectByPrimaryKey(Integer paramInteger);
  
  public abstract List<Message> selectAll();
  
  public abstract int updateByPrimaryKey(Message paramMessage);
}
