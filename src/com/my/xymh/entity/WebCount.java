package com.my.xymh.entity;

import java.io.Serializable;

/**
 * 站点统计
 * @author 
 *
 */
public class WebCount implements Serializable{
	private Integer id;
	private Integer countAll;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCountAll() {
		return countAll;
	}
	public void setCountAll(Integer countAll) {
		this.countAll = countAll;
	}

}
