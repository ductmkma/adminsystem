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
 * The persistent class for the role_menu database table.
 * 
 */
@Entity
@Table(name = "role_menu")
public class RoleMenuBO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private MenuBO menu;
	private RoleBO role;
	public RoleMenuBO() {
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
	@ManyToOne
	@JoinColumn(name="menu_id")
	public MenuBO getMenu() {
		return menu;
	}

	public void setMenu(MenuBO menu) {
		this.menu = menu;
	}
	@ManyToOne
	@JoinColumn(name="role_id")
	public RoleBO getRole() {
		return role;
	}

	public void setRole(RoleBO role) {
		this.role = role;
	}

}