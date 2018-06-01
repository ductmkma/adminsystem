package com.zent.dto;

import java.io.Serializable;

public class RolePermissionDTO implements Serializable{
	private Long id;
	private PermissionDTO permissionDTO;
	public RolePermissionDTO() {
		
	}
	public RolePermissionDTO(Long id, PermissionDTO permissionDTO) {
		super();
		this.id = id;
		this.permissionDTO = permissionDTO;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public PermissionDTO getPermissionDTO() {
		return permissionDTO;
	}
	public void setPermissionDTO(PermissionDTO permissionDTO) {
		this.permissionDTO = permissionDTO;
	}
	
}
