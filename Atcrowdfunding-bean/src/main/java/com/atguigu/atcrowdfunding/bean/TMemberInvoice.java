package com.atguigu.atcrowdfunding.bean;

public class TMemberInvoice {
    private Integer id;

    private Integer memberid;

    private String invoice;

    private String tax;

    private String addrtel;

    private String banknum;

    private String type;

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

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice == null ? null : invoice.trim();
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax == null ? null : tax.trim();
    }

    public String getAddrtel() {
        return addrtel;
    }

    public void setAddrtel(String addrtel) {
        this.addrtel = addrtel == null ? null : addrtel.trim();
    }

    public String getBanknum() {
        return banknum;
    }

    public void setBanknum(String banknum) {
        this.banknum = banknum == null ? null : banknum.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

	@Override
	public String toString() {
		return "TMemberInvoice [id=" + id + ", memberid=" + memberid + ", invoice=" + invoice + ", tax=" + tax
				+ ", addrtel=" + addrtel + ", banknum=" + banknum + ", type=" + type + "]";
	}
    
    
}