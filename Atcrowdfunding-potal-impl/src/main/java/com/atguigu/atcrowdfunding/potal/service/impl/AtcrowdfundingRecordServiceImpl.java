package com.atguigu.atcrowdfunding.potal.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TMemberProjectFollow;
import com.atguigu.atcrowdfunding.bean.TMemberProjectFollowExample;
import com.atguigu.atcrowdfunding.bean.TProjectDestroy;
import com.atguigu.atcrowdfunding.potal.dao.ProjectMapper;
import com.atguigu.atcrowdfunding.potal.dao.TMemberProjectFollowMapper;
import com.atguigu.atcrowdfunding.potal.service.AtcrowdfundingRecordService;
@Service
public class AtcrowdfundingRecordServiceImpl implements AtcrowdfundingRecordService {
	
	@Autowired
	private TMemberProjectFollowMapper followMapper;
	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public List<TMemberProjectFollow> selectAllFollows(Integer id) {
		TMemberProjectFollowExample example = new TMemberProjectFollowExample();
		TMemberProjectFollowExample.Criteria criteria = example.createCriteria();
		criteria.andMemberidEqualTo(id);
		return followMapper.selectByExample(example);
	}

	@Override
	public List<Project> selectByMemberId(Integer memberid) {
		return projectMapper.selectByMemberId(memberid);
	}

	@Override
	public Project getProById(Integer proId) {
		return projectMapper.selectByPrimaryKey(proId);
	}

	@Override
	public void delProject(Integer proid) {
		Project project = projectMapper.selectByPrimaryKey(proid);
		LocalDateTime now = LocalDateTime.now();
		String deletetdate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		TProjectDestroy destroy = 
				new TProjectDestroy(project.getId(), project.getName(), project.getRemark(), project.getMoney(), 
						project.getDay(), project.getStatus(), project.getDeploydate(), project.getSupportmoney(),
						project.getSupporter(), project.getCompletion(), project.getMemberid(), project.getCreatedate(),
						project.getFollower(), deletetdate);
		projectMapper.insertDesInfo(destroy);
//		projectMapper.deleteByPrimaryKey(proid);
		
	}

}
