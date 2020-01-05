package com.atguigu.atcrowdfunding.bean;

public class ProjectTag
{
  private Integer id;
  private Integer projectid;
  private Integer tagid;
  private String tagName;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Integer getProjectid()
  {
    return this.projectid;
  }
  
  public void setProjectid(Integer projectid)
  {
    this.projectid = projectid;
  }
  
  public Integer getTagid()
  {
    return this.tagid;
  }
  
  public void setTagid(Integer tagid)
  {
    this.tagid = tagid;
  }
  
  public String getTagName()
  {
    return this.tagName;
  }
  
  public void setTagName(String tagName)
  {
    this.tagName = tagName;
  }
  
  public String toString()
  {
    return "ProjectTag [id=" + this.id + ", projectid=" + this.projectid + ", tagid=" + this.tagid + ", tagName=" + this.tagName + "]";
  }
}
