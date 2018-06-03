package com.zent.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.zent.entities.MenuBO;
import com.zent.entities.RoleBO;

public interface IMenuService {
	public List<MenuBO> getAll();
	public List<MenuBO> getMenusByRoleId(RoleBO roleBO);
	public List<MenuBO> getMenus(List<GrantedAuthority> roleBO);
}	
