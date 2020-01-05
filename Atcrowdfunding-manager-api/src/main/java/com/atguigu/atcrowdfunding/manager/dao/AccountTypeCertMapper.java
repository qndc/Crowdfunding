package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.AccountTypeCert;
import java.util.List;

public interface AccountTypeCertMapper {
		public abstract int deleteByPrimaryKey(Integer paramInteger);
	  
	  public abstract int insert(AccountTypeCert paramAccountTypeCert);
	  
	  public abstract AccountTypeCert selectByPrimaryKey(Integer paramInteger);
	  
	  public abstract List<AccountTypeCert> selectAll();
	  
	  public abstract int updateByPrimaryKey(AccountTypeCert paramAccountTypeCert);
}