package com.atguigu.atcrowdfunding.util;

import java.util.List;

public class PageVo<T> {

	private Integer pageno;		//页号
	private Integer pagesize;	//每页记录数
	private List<T> data;		//每页显示具体数据
	private Integer totalsize;	//总记录数
	private Integer totalno;	//总页数

	public PageVo(Integer pageno, Integer pagesize) {
		if (pageno <= 0) {
			this.pageno = 1;
		} else {
			this.pageno = pageno;
		}
		if (pagesize <= 0) {
			this.pagesize = 12;
		} else {
			this.pagesize = pagesize;
		}
	}

	public Integer getPageno() {
		return pageno;
	}

	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(Integer totalsize) {
		this.totalsize = totalsize;
		this.totalno = (totalsize % pagesize) == 0 ? (totalsize / pagesize) : (totalsize / pagesize + 1);
	}

	public Integer getTotalno() {
		return totalno;
	}

	private void setTotalno(Integer totalno) {
		this.totalno = totalno;
	}

	public Integer getStartIndex() {
		return (this.pageno - 1) * pagesize;
	}

	@Override
	public String toString() {
		return "PageVo [pageno=" + pageno + ", pagesize=" + pagesize + ", data=" + data + ", totalsize=" + totalsize
				+ ", totalno=" + totalno + "]";
	}
	
	
	
}
