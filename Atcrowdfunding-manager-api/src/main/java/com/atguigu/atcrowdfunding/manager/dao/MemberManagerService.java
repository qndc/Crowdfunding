package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.util.Page;

public interface MemberManagerService {

	/**
	 * 分页查询
	 * @param pageTotal
	 * @param pageSeize
	 * @param queryText
	 * @return
	 */
	Page queryPage(Integer pageTotal, Integer pageSeize, String queryText);


}
