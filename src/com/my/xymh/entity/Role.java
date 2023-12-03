package com.my.xymh.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 * @author 
 *
 */
public class Role implements Serializable{
	private Integer id;
	private String name;
	private String jieshi;
	private List<RoleAction> roleActions = new ArrayList<RoleAction>();
	public List<RoleAction> getRoleActions() {
		return roleActions;
	}
	public void setRoleActions(List<RoleAction> roleActions) {
		this.roleActions = roleActions;
	}
	public String getJieshi() {
		return jieshi;
	}
	public void setJieshi(String jieshi) {
		this.jieshi = jieshi;
	}
	private Integer isDelete;
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
		this.name = name;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
