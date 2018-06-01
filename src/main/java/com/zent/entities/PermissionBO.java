package com.zent.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * The persistent class for the permission database table.
 * 
 */
@Entity
@Table(name = "permission")
public class PermissionBO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String name;
	private List<RolePermissionBO> rolePermissionBOs;

	public PermissionBO() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "permission")
	public List<RolePermissionBO> getRolePermissionBOs() {
		return rolePermissionBOs;
	}

	public void setRolePermissionBOs(List<RolePermissionBO> rolePermissionBOs) {
		this.rolePermissionBOs = rolePermissionBOs;
	}

}