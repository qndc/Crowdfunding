package com.atguigu.atcrowdfunding.potal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TMemberProjectFollow;

public interface AtcrowdfundingRecordService {

	/**
	 * 	获取用户关注列表
	 * @param id
	 * @return
	 */
	List<TMemberProjectFollow> selectAllFollows(Integer id);
	
	/**
	 * 	根据用户id查询发起的项目
	 * @param memberid
	 * @return
	 */
	List<Project> selectByMemberId(Integer memberid);

	/**
	 * 	根据项目Id查询项目
	 * @param proId
	 * @return
	 */
	Project getProById(Integer proId);

	/**
	 * 	删除项目
	 * @param proid
	 */
	void delProject(Integer proid);

}
