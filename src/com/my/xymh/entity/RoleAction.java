package com.my.xymh.entity;

import java.io.Serializable;

/**
 * 角色权限
 * @au50thor 
 *
 */
public class RoleAction implements Serializable{
	private Integer id;
	private Integer actionId;//权限id
	private Integer roleId;//角色id
	private Action action;//权限
	private Role role;//角色
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getActionId() {
		return actionId;
	}
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

}
