package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.manager.dao.MemberManagerService;
import com.atguigu.atcrowdfunding.potal.dao.MemberMapper;
import com.atguigu.atcrowdfunding.util.Page;

@Service
public class MemberManagerServiceImpl implements MemberManagerService {
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public Page queryPage(Integer pageTotal, Integer pageSize, String queryText) {
		Page page = new Page(pageTotal, pageSize);
		//获取分页查询的记录数
		List<Member> data = (List<Member>) memberMapper.queryList(page.startIndex(), pageSize,queryText);
		page.setData(data);
		//查询总记录数
		Integer counts = memberMapper.queryCounts(queryText);
		page.setCounts(counts);
		return page;
	}
}
