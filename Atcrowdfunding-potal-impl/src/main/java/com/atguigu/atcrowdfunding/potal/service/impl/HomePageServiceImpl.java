package com.atguigu.atcrowdfunding.potal.service.impl;

import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.ProjectType;
import com.atguigu.atcrowdfunding.bean.TImgs;
import com.atguigu.atcrowdfunding.bean.TImgsExample;
import com.atguigu.atcrowdfunding.bean.TImgsExample.Criteria;
import com.atguigu.atcrowdfunding.bean.TMemberAddress;
import com.atguigu.atcrowdfunding.bean.TMemberAddressExample;
import com.atguigu.atcrowdfunding.bean.TMemberInvoice;
import com.atguigu.atcrowdfunding.bean.TMemberProjectFollow;
import com.atguigu.atcrowdfunding.bean.TMemberProjectFollowExample;
import com.atguigu.atcrowdfunding.bean.TOrder;
import com.atguigu.atcrowdfunding.bean.TOrderExample;
import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TProjectCompExample;
import com.atguigu.atcrowdfunding.bean.TReturn;
import com.atguigu.atcrowdfunding.bean.TReturnExample;
import com.atguigu.atcrowdfunding.manager.dao.ProjectTypeMapper;
import com.atguigu.atcrowdfunding.manager.dao.TypeMapper;
import com.atguigu.atcrowdfunding.potal.dao.ProjectMapper;
import com.atguigu.atcrowdfunding.potal.dao.TImgsMapper;
import com.atguigu.atcrowdfunding.potal.dao.TMemberAddressMapper;
import com.atguigu.atcrowdfunding.potal.dao.TMemberInvoiceMapper;
import com.atguigu.atcrowdfunding.potal.dao.TMemberProjectFollowMapper;
import com.atguigu.atcrowdfunding.potal.dao.TOrderMapper;
import com.atguigu.atcrowdfunding.potal.dao.TProjectCompMapper;
import com.atguigu.atcrowdfunding.potal.dao.TReturnMapper;
import com.atguigu.atcrowdfunding.potal.service.HomePageService;
import com.atguigu.atcrowdfunding.util.PageVo;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomePageServiceImpl implements HomePageService {
	@Autowired
	private TypeMapper typeMapper;
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private ProjectTypeMapper projectTypeMapper;
	@Autowired
	private TImgsMapper imgMapper;
	@Autowired
	private TReturnMapper returnMapper;
	@Autowired
	private TProjectCompMapper compMapper;
	@Autowired
	private TMemberAddressMapper addressMapper;
	@Autowired
	private TMemberInvoiceMapper invoiceMapper;
	@Autowired
	private TOrderMapper orderMapper;
	@Autowired
	private TMemberProjectFollowMapper mpfMapper;

	public List<com.atguigu.atcrowdfunding.bean.Type> typeList() {
		return this.typeMapper.selectAll();
	}

	public List<ProjectType> getProsIdByType(Integer id) {
		return this.projectTypeMapper.selectProsByType(id);
	}

	public Project getProsById(Integer projectid) {
		return this.projectMapper.selectActrowIng(projectid, "1");
	}

	public List<TImgs> getProImg(Integer id) {
		TImgsExample example = new TImgsExample();
		TImgsExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo("0");
		criteria.andProidEqualTo(id);
		return this.imgMapper.selectByExample(example);
	}

	public List<TImgs> getProDetailImg(Integer proId) {
		TImgsExample example = new TImgsExample();
		TImgsExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo("1");
		criteria.andProidEqualTo(proId);
		return this.imgMapper.selectByExample(example);
	}

	public List<TReturn> getReturnByProId(Integer proId) {
		TReturnExample example = new TReturnExample();
		TReturnExample.Criteria criteria = example.createCriteria();
		criteria.andProjectidEqualTo(proId);
		return this.returnMapper.selectByExample(example);
	}

	public TProjectComp getCompByProId(Integer proId) {
		TProjectCompExample example = new TProjectCompExample();
		TProjectCompExample.Criteria criteria = example.createCriteria();
		criteria.andProidEqualTo(proId);
		return (TProjectComp) this.compMapper.selectByExample(example).get(0);
	}

	public TReturn getReturnById(Integer retId) {
		return this.returnMapper.selectByPrimaryKey(retId);
	}

	public void addAddress(TMemberAddress address) {
		this.addressMapper.insert(address);
	}

	public List<TMemberAddress> getAddrByMemberId(Integer id) {
		TMemberAddressExample example = new TMemberAddressExample();
		TMemberAddressExample.Criteria criteria = example.createCriteria();
		criteria.andMemberidEqualTo(id);
		return this.addressMapper.selectByExample(example);
	}

	public void addInvoice(TMemberInvoice tMemberInvoice) {
		this.invoiceMapper.insert(tMemberInvoice);
	}

	public void addOrder(TOrder order) {
		this.orderMapper.insert(order);
	}

	public List<TOrder> selectOrder(String orderNum) {
		TOrderExample example = new TOrderExample();
		TOrderExample.Criteria criteria = example.createCriteria();
		criteria.andOrdernumEqualTo(orderNum);
		return this.orderMapper.selectByExample(example);
	}

	public void updatePro(Project project) {
		this.projectMapper.updateByPrimaryKey(project);
	}

	public void updateOrder(TOrder tOrder) {
		this.orderMapper.updateByPrimaryKey(tOrder);
	}

	@Override
	public List<TOrder> getOrderByProId(Integer proId) {
		TOrderExample example = new TOrderExample();
		TOrderExample.Criteria criteria = example.createCriteria();
		criteria.andProjectidEqualTo(proId);
		return orderMapper.selectByExample(example);
	}

	@Override
	public void addFollower(TMemberProjectFollow mpf) {
		mpfMapper.insert(mpf);
		
	}

	@Override
	public List<TMemberProjectFollow> getProjectFollowe(Integer id, Integer proId) {
		TMemberProjectFollowExample example = new TMemberProjectFollowExample();
		TMemberProjectFollowExample.Criteria criteria = example.createCriteria();
		criteria.andProjectidEqualTo(proId);
		criteria.andMemberidEqualTo(id);
		List<TMemberProjectFollow> list = mpfMapper.selectByExample(example);
		return list;
	}

	@Override
	public Boolean cancelFollow(Integer id, Integer proId) {
		TMemberProjectFollowExample example = new TMemberProjectFollowExample();
		TMemberProjectFollowExample.Criteria criteria = example.createCriteria();
		criteria.andProjectidEqualTo(proId);
		criteria.andMemberidEqualTo(id);
		int deleteByExample = mpfMapper.deleteByExample(example);
		if (deleteByExample > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Project> getPros() {
		return projectMapper.selectAll();
	}
	
	@Override
	public PageVo<Project> getProsByPage(PageVo<Project> vo,Integer typeid,Integer status) {
		List<Project> pros = projectMapper.selectByPage(typeid,status,vo.getStartIndex(),vo.getPagesize());
		vo.setData(pros);
		return vo;
	}

	
}