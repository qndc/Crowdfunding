package com.atguigu.atcrowdfunding.potal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.TMemberAddress;
import com.atguigu.atcrowdfunding.bean.TMemberAddressExample;
import com.atguigu.atcrowdfunding.bean.TMemberAddressExample.Criteria;
import com.atguigu.atcrowdfunding.potal.dao.TMemberAddressMapper;
import com.atguigu.atcrowdfunding.potal.service.PersonalService;

@Service
public class PersonalServiceImpl implements PersonalService{
	
	@Autowired
	private TMemberAddressMapper memberAddressMapper ;

	@Override
	public List<TMemberAddress> addrList(Integer memberId) {
		TMemberAddressExample example = new TMemberAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andMemberidEqualTo(memberId);
		return memberAddressMapper.selectByExample(example);
	}
	
	

}
