package com.atguigu.atcrowdfunding.potal.service.impl;

import com.atguigu.atcrowdfunding.bean.TProjectComp;
import com.atguigu.atcrowdfunding.bean.TReturn;
import com.atguigu.atcrowdfunding.bean.TTypeTag;
import com.atguigu.atcrowdfunding.bean.TTypeTagExample;
import com.atguigu.atcrowdfunding.bean.TTypeTagExample.Criteria;
import com.atguigu.atcrowdfunding.bean.Tag;
import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.manager.dao.TTypeTagMapper;
import com.atguigu.atcrowdfunding.manager.dao.TagMapper;
import com.atguigu.atcrowdfunding.manager.dao.TypeMapper;
import com.atguigu.atcrowdfunding.potal.dao.TProjectCompMapper;
import com.atguigu.atcrowdfunding.potal.dao.TReturnMapper;
import com.atguigu.atcrowdfunding.potal.service.AtcrowdfundingService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtcrowdfundingServiceImpl implements AtcrowdfundingService {
	@Autowired
	private TypeMapper typeMapper;
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private TTypeTagMapper tTypeTagMapper;
	@Autowired
	private TReturnMapper retMapper;
	@Autowired
	private TProjectCompMapper proCompMapper;

	public List<Type> typeList() {
		return this.typeMapper.selectAll();
	}

	public List<Tag> getTagById(Integer typeid) {
		TTypeTagExample example = new TTypeTagExample();
		TTypeTagExample.Criteria criteria = example.createCriteria();
		criteria.andTypeidEqualTo(typeid);
		List<TTypeTag> list = this.tTypeTagMapper.selectByExample(example);
		List<Tag> tags = new ArrayList();
		for (TTypeTag tTypeTag : list) {
			Tag tag = this.tagMapper.selectByPrimaryKey(tTypeTag.getTagid());
			tags.add(tag);
		}
		return tags;
	}

	public TReturn getRetById(Integer returnId) {
		return this.retMapper.selectByPrimaryKey(returnId);
	}

	public void updateReturn(TReturn ret) {
		this.retMapper.updateByPrimaryKey(ret);
	}

	public void delReturn(Integer returnId) {
		this.retMapper.deleteByPrimaryKey(returnId);
	}

	public void addComp(TProjectComp comp) {
		this.proCompMapper.insert(comp);
	}
}
