package com.atguigu.atcrowdfunding.bean;

public class TProjectTicket {
    private Integer id;

    private Integer memberid;

    private String pid;

    private String status;

    private String pstep;

    private Integer proid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPstep() {
        return pstep;
    }

    public void setPstep(String pstep) {
        this.pstep = pstep == null ? null : pstep.trim();
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }
}