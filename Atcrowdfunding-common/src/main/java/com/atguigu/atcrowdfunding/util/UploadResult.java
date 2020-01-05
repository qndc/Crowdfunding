package com.atguigu.atcrowdfunding.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UploadResult
  extends AjaxResult
{
  private Integer code;
  private String msg;
  private List<Map<String, String>> data = new ArrayList();
  
  public Integer getCode()
  {
    return this.code;
  }
  
  public void setCode(Integer code)
  {
    this.code = code;
  }
  
  public String getMsg()
  {
    return this.msg;
  }
  
  public void setMsg(String msg)
  {
    this.msg = msg;
  }
  
  public List<Map<String, String>> getData()
  {
    return this.data;
  }
  
  public void setData(Map<String, String> data)
  {
    this.data.add(data);
  }
  
  public String toString()
  {
    return "UploadResult [code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + "]";
  }
}
