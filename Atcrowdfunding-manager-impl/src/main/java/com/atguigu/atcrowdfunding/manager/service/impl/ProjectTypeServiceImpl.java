package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.TTypeTag;
import com.atguigu.atcrowdfunding.bean.TTypeTagExample;
import com.atguigu.atcrowdfunding.bean.TTypeTagExample.Criteria;
import com.atguigu.atcrowdfunding.bean.Tag;
import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.manager.dao.TTypeTagMapper;
import com.atguigu.atcrowdfunding.manager.dao.TagMapper;
import com.atguigu.atcrowdfunding.manager.dao.TypeMapper;
import com.atguigu.atcrowdfunding.manager.service.ProjectTypeService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTypeServiceImpl
  implements ProjectTypeService
{
  @Autowired
  private TypeMapper typeMapper;
  @Autowired
  private TTypeTagMapper tTypeTagMapper;
  @Autowired
  private TagMapper tagMapper;
  
  public List<Type> selectAll(String content)
  {
    List<Type> types = null;
    if (StringUtils.isEmpty(content)) {
      types = this.typeMapper.selectAll();
    } else {
      types = this.typeMapper.selectByQuery(content);
    }
    for (Type type : types)
    {
      TTypeTagExample example = new TTypeTagExample();
      TTypeTagExample.Criteria criteria = example.createCriteria();
      criteria.andTypeidEqualTo(type.getId());
      
      List<TTypeTag> tags = this.tTypeTagMapper.selectByExample(example);
      List<Tag> list = new ArrayList();
      for (TTypeTag typeTag : tags)
      {
        Tag tag = this.tagMapper.selectByPrimaryKey(typeTag.getTagid());
        list.add(tag);
      }
      type.setTags(list);
    }
    return types;
  }
  
  public void deleteById(Integer id)
  {
    this.typeMapper.deleteByPrimaryKey(id);
  }
  
  public void insertType(Type type)
  {
    this.typeMapper.insert(type);
  }
  
  public Type selectById(Integer id)
  {
    return this.typeMapper.selectByPrimaryKey(id);
  }
  
  public void updateType(Type type)
  {
    this.typeMapper.updateByPrimaryKey(type);
  }
  
  public void deleteAll(Integer[] id)
  {
    for (Integer i : id) {
      this.typeMapper.deleteByPrimaryKey(i);
    }
  }
  
  public void deleteTag(Integer typeId, Integer tagId)
  {
    TTypeTagExample example = new TTypeTagExample();
    TTypeTagExample.Criteria criteria = example.createCriteria();
    criteria.andTypeidEqualTo(typeId);
    criteria.andTagidEqualTo(tagId);
    this.tTypeTagMapper.deleteByExample(example);
  }
  
  public void addTag(Integer typeId, String content)
  {
    Tag tag = this.tagMapper.selectByName(content);
    
    TTypeTag tt = new TTypeTag();
    tt.setTypeid(typeId);
    if (tag == null)
    {
      tag = new Tag();
      tag.setName(content);
      this.tagMapper.insert(tag);
      tt.setTagid(tag.getId());
    }
    else
    {
      tt.setTagid(tag.getId());
    }
    this.tTypeTagMapper.insert(tt);
  }
}
