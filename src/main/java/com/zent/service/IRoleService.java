package com.zent.service;

import java.util.List;

import com.zent.entities.RoleBO;

public interface IRoleService {
	public List<RoleBO> getAll();
	public List<RoleBO> search(RoleBO roleBO, Integer page);
	public RoleBO getUserById(RoleBO roleBO);
	public void insert(RoleBO roleBO);
	public void delete(RoleBO roleBO);
	public void update(RoleBO roleBO);
}
