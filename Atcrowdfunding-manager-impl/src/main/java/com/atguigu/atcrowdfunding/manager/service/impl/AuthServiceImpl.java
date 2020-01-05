package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.ProjectTag;
import com.atguigu.atcrowdfunding.bean.ProjectType;
import com.atguigu.atcrowdfunding.bean.TImgs;
import com.atguigu.atcrowdfunding.bean.TImgsExample;
import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TProjectCompExample;
import com.atguigu.atcrowdfunding.bean.TProjectTicket;
import com.atguigu.atcrowdfunding.bean.TProjectTicketExample;
import com.atguigu.atcrowdfunding.bean.TReturn;
import com.atguigu.atcrowdfunding.bean.TReturnExample;
import com.atguigu.atcrowdfunding.bean.TTicket;
import com.atguigu.atcrowdfunding.bean.TTicketExample;
import com.atguigu.atcrowdfunding.bean.TTicketExample.Criteria;
import com.atguigu.atcrowdfunding.bean.Tag;
import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.manager.dao.ProjectTagMapper;
import com.atguigu.atcrowdfunding.manager.dao.ProjectTypeMapper;
import com.atguigu.atcrowdfunding.manager.dao.TCertMapper;
import com.atguigu.atcrowdfunding.manager.dao.TagMapper;
import com.atguigu.atcrowdfunding.manager.dao.TypeMapper;
import com.atguigu.atcrowdfunding.manager.service.AuthService;
import com.atguigu.atcrowdfunding.potal.dao.ProjectMapper;
import com.atguigu.atcrowdfunding.potal.dao.TImgsMapper;
import com.atguigu.atcrowdfunding.potal.dao.TProjectCompMapper;
import com.atguigu.atcrowdfunding.potal.dao.TProjectTicketMapper;
import com.atguigu.atcrowdfunding.potal.dao.TReturnMapper;
import com.atguigu.atcrowdfunding.potal.dao.TTicketMapper;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private TTicketMapper ticketMapper;
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private TImgsMapper imgsMapper;
	@Autowired
	private TReturnMapper returnMapper;
	@Autowired
	private TProjectCompMapper compMapper;
	@Autowired
	private TCertMapper tCertMapper;
	@Autowired
	private ProjectTypeMapper projectTypeMapper;
	@Autowired
	private TypeMapper typeMapper;
	@Autowired
	private ProjectTagMapper projectTagMapper;
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private TProjectTicketMapper ttMapper;

	public TTicket selectTicketByPiid(String piid) {
		TTicketExample example = new TTicketExample();
		TTicketExample.Criteria criteria = example.createCriteria();
		criteria.andPiidEqualTo(piid);
		List<TTicket> list = this.ticketMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return (TTicket) list.get(0);
	}

	public Project getProjectById(Integer proId) {
		return this.projectMapper.selectByPrimaryKey(proId);
	}

	public List<TImgs> getImgsByProId(Integer id) {
		TImgsExample example = new TImgsExample();
		TImgsExample.Criteria criteria = example.createCriteria();
		criteria.andProidEqualTo(id);
		return this.imgsMapper.selectByExample(example);
	}

	public List<TReturn> getRetByProId(Integer id) {
		TReturnExample example = new TReturnExample();
		TReturnExample.Criteria criteria = example.createCriteria();
		criteria.andProjectidEqualTo(id);
		return this.returnMapper.selectByExample(example);
	}

	public List<TProjectComp> getCompByProId(Integer id) {
		TProjectCompExample example = new TProjectCompExample();
		TProjectCompExample.Criteria criteria = example.createCriteria();
		criteria.andProidEqualTo(id);
		return this.compMapper.selectByExample(example);
	}

	public List<MemberCert> getMemberCert(Integer memberid) {
		return this.tCertMapper.selectMemberCert(memberid);
	}

	public Cert getCertById(Integer certid) {
		return this.tCertMapper.selectByPrimaryKey(certid);
	}

	public ProjectType getProType(Integer id) {
		return this.projectTypeMapper.selectByProId(id);
	}

	public Type getType(Integer typeid) {
		return this.typeMapper.selectByPrimaryKey(typeid);
	}

	public List<ProjectTag> getTagByProId(Integer id) {
		return this.projectTagMapper.selectByProId(id);
	}

	public Tag getTagById(Integer tagid) {
		return this.tagMapper.selectByPrimaryKey(tagid);
	}

	public TProjectTicket getTicketByProId(Integer proId) {
		TProjectTicketExample example = new TProjectTicketExample();
		TProjectTicketExample.Criteria criteria = example.createCriteria();
		criteria.andProidEqualTo(proId);
		return (TProjectTicket) this.ttMapper.selectByExample(example).get(0);
	}

}
