package com.zent.dao;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.zent.entities.MenuBO;
import com.zent.entities.RolePermissionBO;

public interface IRolePermissionDAO extends IBaseDAO<RolePermissionBO>{
	Boolean checkPermission(List<GrantedAuthority> roleBO, String permission);
}
