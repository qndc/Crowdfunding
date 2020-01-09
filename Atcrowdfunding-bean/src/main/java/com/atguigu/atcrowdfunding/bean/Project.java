package com.atguigu.atcrowdfunding.bean;

import java.util.Arrays;

public class Project {
	private Integer id;
	private String name;
	private String remark;
	private Long money;
	private Integer day;
	private String status;
	private String deploydate;
	private Long supportmoney;
	private Integer supporter;
	private Integer completion;
	private Integer memberid;
	private String createdate;
	private Integer follower;
	private Integer[] tagId;
	private Integer type;
	private TImgs imgs;
	private TProjectImg projectImg;
	private TProjectComp projectComp;
	private String enddate;
	private Member member;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = (name == null ? null : name.trim());
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = (remark == null ? null : remark.trim());
	}

	public Long getMoney() {
		return this.money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public Integer getDay() {
		return this.day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = (status == null ? null : status.trim());
	}

	public String getDeploydate() {
		return this.deploydate;
	}

	public void setDeploydate(String deploydate) {
		this.deploydate = (deploydate == null ? null : deploydate.trim());
	}

	public Long getSupportmoney() {
		return this.supportmoney;
	}

	public void setSupportmoney(Long supportmoney) {
		this.supportmoney = supportmoney;
	}

	public Integer getSupporter() {
		return this.supporter;
	}

	public void setSupporter(Integer supporter) {
		this.supporter = supporter;
	}

	public Integer getCompletion() {
		return this.completion;
	}

	public void setCompletion(Integer completion) {
		this.completion = completion;
	}

	public Integer getMemberid() {
		return this.memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	public String getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = (createdate == null ? null : createdate.trim());
	}

	public Integer getFollower() {
		return this.follower;
	}

	public void setFollower(Integer follower) {
		this.follower = follower;
	}

	public Integer[] getTagId() {
		return this.tagId;
	}

	public void setTagId(Integer[] tagId) {
		this.tagId = tagId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public TProjectComp getProjectComp() {
		return this.projectComp;
	}

	public void setProjectComp(TProjectComp projectComp) {
		this.projectComp = projectComp;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public TImgs getImgs() {
		return this.imgs;
	}

	public void setImgs(TImgs imgs) {
		this.imgs = imgs;
	}

	public TProjectImg getProjectImg() {
		return this.projectImg;
	}

	public void setProjectImg(TProjectImg projectImg) {
		this.projectImg = projectImg;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", remark=" + remark + ", money=" + money + ", day=" + day
				+ ", status=" + status + ", deploydate=" + deploydate + ", supportmoney=" + supportmoney
				+ ", supporter=" + supporter + ", completion=" + completion + ", memberid=" + memberid + ", createdate="
				+ createdate + ", follower=" + follower + ", tagId=" + Arrays.toString(tagId) + ", type=" + type
				+ ", imgs=" + imgs + ", projectImg=" + projectImg + ", projectComp=" + projectComp + ", enddate="
				+ enddate + ", member=" + member + "]";
	}

}
