package com.atguigu.atcrowdfunding.potal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.TMemberProjectFollow;
import com.atguigu.atcrowdfunding.bean.TMemberProjectFollowExample;
import com.atguigu.atcrowdfunding.potal.dao.TMemberProjectFollowMapper;
import com.atguigu.atcrowdfunding.potal.service.AtcrowdfundingRecordService;
@Service
public class AtcrowdfundingRecordServiceImpl implements AtcrowdfundingRecordService {
	
	@Autowired
	private TMemberProjectFollowMapper followMapper;

	@Override
	public List<TMemberProjectFollow> selectAllFollows(Integer id) {
		TMemberProjectFollowExample example = new TMemberProjectFollowExample();
		TMemberProjectFollowExample.Criteria criteria = example.createCriteria();
		criteria.andMemberidEqualTo(id);
		return followMapper.selectByExample(example);
	}

}
