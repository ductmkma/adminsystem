package com.zent.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.zent.entities.MenuBO;

public interface IRolePermissionService {
	Boolean checkPermission(List<GrantedAuthority> roleBO, String permission);
}
