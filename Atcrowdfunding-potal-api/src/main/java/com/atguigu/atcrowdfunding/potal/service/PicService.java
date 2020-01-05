package com.atguigu.atcrowdfunding.potal.service;

import java.io.OutputStream;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public abstract interface PicService
{
  public abstract Map uploadPic(MultipartFile paramMultipartFile);
  
  public abstract void downLoad(String paramString, OutputStream paramOutputStream);
}


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\service\PicService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */