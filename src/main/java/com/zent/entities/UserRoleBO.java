package com.zent.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")
public class UserRoleBO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private String id;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserBO user;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleBO role;

	public UserRoleBO() {
	}
	
	public UserRoleBO(UserBO user, RoleBO role) {
		super();
		this.user = user;
		this.role = role;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserBO getUser() {
		return this.user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}

	public RoleBO getRole() {
		return this.role;
	}

	public void setRole(RoleBO role) {
		this.role = role;
	}

}