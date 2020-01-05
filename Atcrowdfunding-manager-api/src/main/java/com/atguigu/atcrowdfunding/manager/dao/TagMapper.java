package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Tag;
import java.util.List;

public abstract interface TagMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(Tag paramTag);
  
  public abstract Tag selectByPrimaryKey(Integer paramInteger);
  
  public abstract List<Tag> selectAll();
  
  public abstract int updateByPrimaryKey(Tag paramTag);
  
  public abstract Tag selectByName(String paramString);
}
