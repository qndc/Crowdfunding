package com.atguigu.atcrowdfunding.potal.service;

import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.ProjectType;
import com.atguigu.atcrowdfunding.bean.TImgs;
import com.atguigu.atcrowdfunding.bean.TMemberAddress;
import com.atguigu.atcrowdfunding.bean.TMemberInvoice;
import com.atguigu.atcrowdfunding.bean.TMemberProjectFollow;
import com.atguigu.atcrowdfunding.bean.TOrder;
import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TReturn;
import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.util.PageVo;

import java.util.List;

public abstract interface HomePageService
{
  List<Type> typeList();
  
  List<ProjectType> getProsIdByType(Integer paramInteger);
  
  Project getProsById(Integer paramInteger);
  
  List<TImgs> getProImg(Integer paramInteger);
  
  List<TImgs> getProDetailImg(Integer paramInteger);
  
  List<TReturn> getReturnByProId(Integer paramInteger);
  
  TProjectComp getCompByProId(Integer paramInteger);
  
  TReturn getReturnById(Integer paramInteger);
  
  void addAddress(TMemberAddress paramTMemberAddress);
  
  List<TMemberAddress> getAddrByMemberId(Integer paramInteger);
  
  void addInvoice(TMemberInvoice paramTMemberInvoice);
  
  void addOrder(TOrder paramTOrder);
  
  List<TOrder> selectOrder(String paramString);
  
  void updatePro(Project paramProject);
  
  void updateOrder(TOrder paramTOrder);
  
  /**
   * 	根据项目查询订单
   * @param proId
   * @return
   */
  List<TOrder> getOrderByProId(Integer proId);

  /**
   * 	关注
   * @param mpf
   */
  public abstract void addFollower(TMemberProjectFollow mpf);

  /**
   * 	根据项目id、用户id查询是否已关注
   * @param id
   * @param proId
   */
  List<TMemberProjectFollow> getProjectFollowe(Integer id, Integer proId);
  
  /**
   * 	取消关注
   * @param id
   * @param proId
   */
  Boolean cancelFollow(Integer id, Integer proId);

  /**
   * 	查询全部项目总数
   * @return
   */
  List<Project> getPros();

  /**
   * 	查询分类下的所有项目
   * 	mybatis动态拼接sql
   * @param vo
   * @param typeid
   * @return
   */
  PageVo<Project> getProsByPage(PageVo<Project> vo, Integer typeid,Integer status,Integer sort,String keyWords);

  /**
   * 	不根据分页查记录数
   * @param typeid
   * @param status
   * @param sort
   * @param keyWords
   * @return
   */
  Integer getProsNotByPage(Integer typeid, Integer status, String keyWords);

  /**
   * 	根据用户id查询订单
   * @param id
   * @return
   */
  List<TOrder> getOrderByMemberId(Integer id);

  /**
   *	 根据用户id查询发票信息
   * @param id
   * @return
   */
  List<TMemberInvoice> getInvoiceByMemberId(Integer id);

  /**
   * 	根据订单id查询订单
   * @param orderid
   * @return
   */
  TOrder getOrderById(Integer orderid);

  /**
   * 	根据id查询地址
   * @param addressid
   * @return
   */
  TMemberAddress getAddrById(String addressid);

  /**
   * 	根据id查询发票
   * @param invoiceid
   * @return
   */
  TMemberInvoice getInvoiceById(String invoiceid);

  /**
   * 	根据id查询项目
   * @param projectid
   * @return
   */
  Project getProById(Integer projectid);

  /**
   * 	查询热门商品
   * @return
   */
  List<Project> getProsOrderByCompletion();

  /**
   * 	根据订单编号查询订单
   * @param ordernum
   * @return
   */
TOrder getOrderByNum(String ordernum);

 
}
