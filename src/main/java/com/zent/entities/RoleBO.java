package com.zent.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name = "role")
public class RoleBO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@Column(name = "deleted_at")
	private Date deletedAt;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "role")
	private List<UserRoleBO> userRoles;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany( mappedBy = "role")
	private List<RoleMenuBO> roleMenus;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany( mappedBy = "role")
	private List<RolePermissionBO> rolePermissionBOs;

	public RoleBO() {
	}

	public RoleBO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<UserRoleBO> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRoleBO> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRoleBO addUserRole(UserRoleBO userRole) {
		getUserRoles().add(userRole);
		userRole.setRole(this);

		return userRole;
	}

	public UserRoleBO removeUserRole(UserRoleBO userRole) {
		getUserRoles().remove(userRole);
		userRole.setRole(null);

		return userRole;
	}

	public List<RoleMenuBO> getRoleMenus() {
		return roleMenus;
	}

	public void setRoleMenus(List<RoleMenuBO> roleMenus) {
		this.roleMenus = roleMenus;
	}

	public List<RolePermissionBO> getRolePermissionBOs() {
		return rolePermissionBOs;
	}

	public void setRolePermissionBOs(List<RolePermissionBO> rolePermissionBOs) {
		this.rolePermissionBOs = rolePermissionBOs;
	}

	public RoleBO(Long id, String code, String name, Date createdAt, Date updatedAt, Date deletedAt,
			List<UserRoleBO> userRoles, List<RoleMenuBO> roleMenus, List<RolePermissionBO> rolePermissionBOs) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.userRoles = userRoles;
		this.roleMenus = roleMenus;
		this.rolePermissionBOs = rolePermissionBOs;
	}
	
}