package com.zent.service;

import java.util.List;

import com.zent.entities.MenuBO;
import com.zent.entities.RoleBO;

public interface IMenuService {
	public List<MenuBO> getAll();
	public List<MenuBO> getMenusByRoleId(RoleBO roleBO);
}	
