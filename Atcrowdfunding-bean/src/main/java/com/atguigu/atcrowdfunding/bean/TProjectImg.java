package com.atguigu.atcrowdfunding.bean;

import java.util.Arrays;

public class TProjectImg {
    private Integer id;

    private Integer proid;

    private String headimg;

    private String detailimg;
    
    private String[] detailImg;

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

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public String getDetailimg() {
        return detailimg;
    }

    public void setDetailimg(String detailimg) {
        this.detailimg = detailimg == null ? null : detailimg.trim();
    }

	public String[] getDetailImg() {
		return detailImg;
	}

	public void setDetailImg(String[] detailImg) {
		this.detailImg = detailImg;
	}

	@Override
	public String toString() {
		return "TProjectImg [id=" + id + ", proid=" + proid + ", headimg=" + headimg + ", detailimg=" + detailimg
				+ ", detailImg=" + Arrays.toString(detailImg) + "]";
	}
    
    
}