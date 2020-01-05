package com.atguigu.atcrowdfunding.bean;

import java.util.List;

public class Data {
	
	private List<MemberCert> list;

	public List<MemberCert> getList() {
		return list;
	}

	public void setList(List<MemberCert> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Data [list=" + list + "]";
	}

	
}
