package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.TProjectMember;
import com.atguigu.atcrowdfunding.bean.TProjectMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TProjectMemberMapper {
    int countByExample(TProjectMemberExample example);

    int deleteByExample(TProjectMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TProjectMember record);

    int insertSelective(TProjectMember record);

    List<TProjectMember> selectByExample(TProjectMemberExample example);

    TProjectMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TProjectMember record, @Param("example") TProjectMemberExample example);

    int updateByExample(@Param("record") TProjectMember record, @Param("example") TProjectMemberExample example);

    int updateByPrimaryKeySelective(TProjectMember record);

    int updateByPrimaryKey(TProjectMember record);
}