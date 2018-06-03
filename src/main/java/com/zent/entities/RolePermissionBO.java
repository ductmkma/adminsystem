package com.zent.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the role_permission database table.
 * 
 */
@Entity
@Table(name="role_permission")
public class RolePermissionBO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private PermissionBO permission;
	private RoleBO role;

	public RolePermissionBO() {
	}
	
	public RolePermissionBO(PermissionBO permission, RoleBO role) {
		super();
		this.permission = permission;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="permission_id")
	public PermissionBO getPermission() {
		return permission;
	}

	public void setPermission(PermissionBO permission) {
		this.permission = permission;
	}
	@ManyToOne
	@JoinColumn(name="role_id")
	public RoleBO getRole() {
		return role;
	}

	public void setRole(RoleBO role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return permission.getName();
	}

}