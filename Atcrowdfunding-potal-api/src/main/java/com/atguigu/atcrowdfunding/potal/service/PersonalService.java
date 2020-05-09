package com.atguigu.atcrowdfunding.potal.service;

import java.util.List;

import com.atguigu.atcrowdfunding.bean.TMemberAddress;

public interface PersonalService {

	List<TMemberAddress> addrList(Integer memberId);

}
