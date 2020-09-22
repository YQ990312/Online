package com.online.operation.common.orm;

import java.io.Serializable;

public class LongModel implements Identifier<Integer>,Serializable{
	private static final long serialVersionUID = 7978917143723588623L;
	
	private Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
}
