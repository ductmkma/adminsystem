package com.zent.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RoleDTO implements Serializable{
	private Long id;
	private String name;
	private String code;
	private Date createdAt;
	private Date deletedAt;
	private Date updatedAt;
	private List<UserRoleDTO> userRoles;
	private List<RoleMenuDTO> roleMenus;
	private List<RolePermissionDTO> rolePermissionDTO;
	
	public RoleDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<UserRoleDTO> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRoleDTO> userRoles) {
		this.userRoles = userRoles;
	}
	public List<RoleMenuDTO> getRoleMenus() {
		return roleMenus;
	}
	public void setRoleMenus(List<RoleMenuDTO> roleMenus) {
		this.roleMenus = roleMenus;
	}
	public List<RolePermissionDTO> rolePermissionDTO() {
		return rolePermissionDTO;
	}
	public void setRolePermissionBOs(List<RolePermissionDTO> rolePermissionDTO) {
		this.rolePermissionDTO = rolePermissionDTO;
	}
	public RoleDTO(Long id, String name, String code, Date createdAt, Date deletedAt, Date updatedAt,
			List<UserRoleDTO> userRoles, List<RoleMenuDTO> roleMenus, List<RolePermissionDTO> rolePermissionDTO) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
		this.updatedAt = updatedAt;
		this.userRoles = userRoles;
		this.roleMenus = roleMenus;
		this.rolePermissionDTO = rolePermissionDTO;
	}
	
	
	
}
