package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.TProjectMember;
import com.atguigu.atcrowdfunding.bean.TProjectMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface TProjectMemberMapper
{
  public abstract int countByExample(TProjectMemberExample paramTProjectMemberExample);
  
  public abstract int deleteByExample(TProjectMemberExample paramTProjectMemberExample);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(TProjectMember paramTProjectMember);
  
  public abstract int insertSelective(TProjectMember paramTProjectMember);
  
  public abstract List<TProjectMember> selectByExample(TProjectMemberExample paramTProjectMemberExample);
  
  public abstract TProjectMember selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(@Param("record") TProjectMember paramTProjectMember, @Param("example") TProjectMemberExample paramTProjectMemberExample);
  
  public abstract int updateByExample(@Param("record") TProjectMember paramTProjectMember, @Param("example") TProjectMemberExample paramTProjectMemberExample);
  
  public abstract int updateByPrimaryKeySelective(TProjectMember paramTProjectMember);
  
  public abstract int updateByPrimaryKey(TProjectMember paramTProjectMember);
}


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\dao\TProjectMemberMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */