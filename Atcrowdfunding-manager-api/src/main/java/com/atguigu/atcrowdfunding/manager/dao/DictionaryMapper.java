package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Dictionary;
import java.util.List;

public interface DictionaryMapper {
	public abstract int deleteByPrimaryKey(Integer paramInteger);

	public abstract int insert(Dictionary paramDictionary);

	public abstract Dictionary selectByPrimaryKey(Integer paramInteger);

	public abstract List<Dictionary> selectAll();

	public abstract int updateByPrimaryKey(Dictionary paramDictionary);
}