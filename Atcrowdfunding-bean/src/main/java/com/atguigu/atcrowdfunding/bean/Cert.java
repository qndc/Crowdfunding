package com.atguigu.atcrowdfunding.bean;

import java.util.List;

public class Cert {
    private Integer id;

    private String name;
    
    private List<String> type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Cert [id=" + id + ", name=" + name + ", type=" + type + "]";
	}

	
    
}