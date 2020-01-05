package com.atguigu.atcrowdfunding.potal.service.impl;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.ProjectTag;
import com.atguigu.atcrowdfunding.bean.ProjectType;
import com.atguigu.atcrowdfunding.bean.TImgs;
import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TProjectCompExample;
import com.atguigu.atcrowdfunding.bean.TProjectImg;
import com.atguigu.atcrowdfunding.bean.TProjectTicket;
import com.atguigu.atcrowdfunding.bean.TProjectTicketExample;
import com.atguigu.atcrowdfunding.bean.TReturn;
import com.atguigu.atcrowdfunding.bean.TReturnExample;
import com.atguigu.atcrowdfunding.manager.dao.ProjectTagMapper;
import com.atguigu.atcrowdfunding.manager.dao.ProjectTypeMapper;
import com.atguigu.atcrowdfunding.potal.dao.MemberMapper;
import com.atguigu.atcrowdfunding.potal.dao.ProjectMapper;
import com.atguigu.atcrowdfunding.potal.dao.TImgsMapper;
import com.atguigu.atcrowdfunding.potal.dao.TProjectCompMapper;
import com.atguigu.atcrowdfunding.potal.dao.TProjectTicketMapper;
import com.atguigu.atcrowdfunding.potal.dao.TReturnMapper;
import com.atguigu.atcrowdfunding.potal.service.ProjectTicketService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectTicketServiceImpl implements ProjectTicketService {
	@Autowired
	private TProjectTicketMapper ptMapper;
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private TImgsMapper imgMapper;
	@Autowired
	private ProjectTypeMapper pTypeMapper;
	@Autowired
	private ProjectTagMapper pTagMapper;
	@Autowired
	private TReturnMapper retMapper;
	@Autowired
	private TProjectCompMapper tpcMapper;
	@Autowired
	private MemberMapper memberMapper;

	public TProjectTicket getTicketByMemId(Integer id) {
		TProjectTicketExample example = new TProjectTicketExample();
		TProjectTicketExample.Criteria criteria = example.createCriteria();
		criteria.andMemberidEqualTo(id);
		criteria.andStatusEqualTo("0");
		List<TProjectTicket> list = this.ptMapper.selectByExample(example);
		if (!list.isEmpty()) {
			return (TProjectTicket) list.get(0);
		}
		return null;
	}

	public void createTicket(TProjectTicket projectTicket) {
		this.ptMapper.insert(projectTicket);
	}

	public void updateProjectTicket(TProjectTicket pt) {
		this.ptMapper.updateByPrimaryKey(pt);
	}

	@Transactional(rollbackFor = { Exception.class })
	public void insertProject(Project project) {
		this.projectMapper.insert(project);
		ProjectTag pt = new ProjectTag();
		ProjectType pType = new ProjectType();
		pType.setProjectid(project.getId());
		pType.setTypeid(project.getType());
		this.pTypeMapper.insert(pType);

		Integer[] ids = project.getTagId();
		for (Integer id : ids) {
			pt = new ProjectTag();
			pt.setProjectid(project.getId());
			pt.setTagid(id);
			this.pTagMapper.insert(pt);
		}

		TProjectImg proImg = project.getProjectImg();
		TImgs imgs = new TImgs();
		imgs.setImg(proImg.getHeadimg());
		imgs.setProid(project.getId());
		imgs.setType("0");
		this.imgMapper.insert(imgs);
		String[] detailImg = proImg.getDetailImg();
		for (String img : detailImg) {
			imgs.setImg(img);
			imgs.setType("1");
			imgMapper.insert(imgs);
		}
	}

	@Transactional(rollbackFor = { Exception.class })
	public void insertReturn(TReturn ret) {
		this.retMapper.insert(ret);
	}

	public List<TReturn> getReturns(Integer proid) {
		TReturnExample example = new TReturnExample();
		TReturnExample.Criteria criteria = example.createCriteria();
		criteria.andProjectidEqualTo(proid);
		List<TReturn> list = this.retMapper.selectByExample(example);
		return list;
	}

	public Project getProjectByMemberId(Integer id) {
		return null;
	}

	public TProjectTicket getTicketByPId(String processInstanceId) {
		TProjectTicketExample example = new TProjectTicketExample();
		TProjectTicketExample.Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(processInstanceId);
		List<TProjectTicket> list = this.ptMapper.selectByExample(example);
		return (TProjectTicket) list.get(0);
	}

	public Project getProjectByProId(Integer proId) {
		Project project = this.projectMapper.selectByPrimaryKey(proId);

		TProjectCompExample example = new TProjectCompExample();
		TProjectCompExample.Criteria criteria = example.createCriteria();
		criteria.andProidEqualTo(proId);
		List<TProjectComp> list = this.tpcMapper.selectByExample(example);
		project.setProjectComp((TProjectComp) list.get(0));

		Member member = this.memberMapper.selectByPrimaryKey(project.getMemberid());
		project.setMember(member);
		return project;
	}

	public void updateProject(Project project) {
		this.projectMapper.updateByPrimaryKey(project);
	}
}
