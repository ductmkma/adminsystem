package com.zent.dao;

import java.util.List;

import com.zent.entities.MenuBO;
import com.zent.entities.RoleBO;

public interface IMenuDAO {
	public List<MenuBO> getAll();
	public List<MenuBO> getMenusByRoleId(RoleBO roleBO);
}
