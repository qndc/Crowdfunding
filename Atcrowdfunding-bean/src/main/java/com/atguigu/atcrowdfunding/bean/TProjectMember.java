package com.atguigu.atcrowdfunding.bean;

public class TProjectMember {
    private Integer id;

    private Integer proid;

    private String basicdesc;

    private String detaildesc;

    private String tel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public String getBasicdesc() {
        return basicdesc;
    }

    public void setBasicdesc(String basicdesc) {
        this.basicdesc = basicdesc == null ? null : basicdesc.trim();
    }

    public String getDetaildesc() {
        return detaildesc;
    }

    public void setDetaildesc(String detaildesc) {
        this.detaildesc = detaildesc == null ? null : detaildesc.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }
}