package com.atguigu.atcrowdfunding.bean;

public class TProjectComp {
    private Integer id;

    private String compna;

    private String compno;

    private String compcd;

    private String compacct;

    private String servicetel;

    private String servicetime;
    
    private String start_time;
    private String end_time;

    private String userexist;

    private String template;

    private Integer proid;
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompna() {
        return compna;
    }

    public void setCompna(String compna) {
        this.compna = compna == null ? null : compna.trim();
    }

    public String getCompno() {
        return compno;
    }

    public void setCompno(String compno) {
        this.compno = compno == null ? null : compno.trim();
    }

    public String getCompcd() {
        return compcd;
    }

    public void setCompcd(String compcd) {
        this.compcd = compcd == null ? null : compcd.trim();
    }

    public String getCompacct() {
        return compacct;
    }

    public void setCompacct(String compacct) {
        this.compacct = compacct == null ? null : compacct.trim();
    }

    public String getServicetel() {
        return servicetel;
    }

    public void setServicetel(String servicetel) {
        this.servicetel = servicetel == null ? null : servicetel.trim();
    }

    public String getServicetime() {
        return servicetime;
    }

    public void setServicetime(String servicetime) {
        this.servicetime = servicetime == null ? null : servicetime.trim();
    }

    public String getUserexist() {
        return userexist;
    }

    public void setUserexist(String userexist) {
        this.userexist = userexist == null ? null : userexist.trim();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	@Override
	public String toString() {
		return "TProjectComp [id=" + id + ", compna=" + compna + ", compno=" + compno + ", compcd=" + compcd
				+ ", compacct=" + compacct + ", servicetel=" + servicetel + ", servicetime=" + servicetime
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", userexist=" + userexist + ", template="
				+ template + ", proid=" + proid + "]";
	}
    
}