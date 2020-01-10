package com.atguigu.atcrowdfunding.util;

import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

public class Page {
	
	private Integer pageTotal;	//当前页
	private Integer pageSize;	//每页显示记录数
	private Integer pageCounts;	//总页数
	private Integer counts;		//总记录数
	private List data;	//每页显示具体数据
	public Integer getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCounts() {
		return pageCounts;
	}
	private void setPageCounts(Integer pageCounts) {
		this.pageCounts = pageCounts;
	}
	public Integer getCounts() {
		return counts;
	}
	public void setCounts(Integer counts) {
		this.counts = counts;
		this.pageCounts = counts%pageSize == 0?counts/pageSize:counts/pageSize+1;
		System.err.println("总页数："+this.pageCounts);
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public Integer startIndex() {
		return (this.pageTotal-1)*pageSize;
	}
	public Page(Integer pageTotal, Integer pageSize) {
		this.pageTotal = pageTotal;
		this.pageSize = pageSize;
	}
	public Page() {
		super();
		
	}
	@Override
	public String toString() {
		return "Page [pageTotal=" + pageTotal + ", pageSize=" + pageSize + ", pageCounts=" + pageCounts + ", counts="
				+ counts + ", data=" + data + "]";
	}
	
}
