package com.atguigu.atcrowdfunding.potal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.TMemberProjectFollow;

public interface AtcrowdfundingRecordService {

	/**
	 * 	获取用户关注列表
	 * @param id
	 * @return
	 */
	List<TMemberProjectFollow> selectAllFollows(Integer id);

}
