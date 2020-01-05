package com.atguigu.atcrowdfunding.bean;

import java.util.List;

public class Type {
    private Integer id;

    private String name;

    private String remark;
    
    private List<Tag> tags;
    private List<Project> pros;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    public List<Tag> getTags()
    {
      return this.tags;
    }
    
    public void setTags(List<Tag> tags)
    {
      this.tags = tags;
    }
    
    public List<Project> getPros()
    {
      return this.pros;
    }
    
    public void setPros(List<Project> pros)
    {
      this.pros = pros;
    }
    
    public String toString()
    {
      return "Type [id=" + this.id + ", name=" + this.name + ", remark=" + this.remark + ", tags=" + this.tags + ", pros=" + this.pros + "]";
    }
}