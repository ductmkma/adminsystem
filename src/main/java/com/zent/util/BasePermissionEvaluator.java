package com.zent.util;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.zent.service.IRolePermissionService;

public class BasePermissionEvaluator implements PermissionEvaluator {
	public static final String PROJECT_PERMISSION_TYPE = "Project";
	@Autowired
	IRolePermissionService rolePermissionService;
	@SuppressWarnings("unchecked")
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permissionText) {
		return rolePermissionService.checkPermission((List<GrantedAuthority>)authentication.getAuthorities(),(String) permissionText);
	}

	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permissionText) {
		return true;
	}
}
