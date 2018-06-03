package com.zent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zent.dao.IRolePermissionDAO;

@Service("rolePermissionService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RolePermissionService implements IRolePermissionService {
	@Autowired
	IRolePermissionDAO rolePermissionDAO;

	public Boolean checkPermission(List<GrantedAuthority> roleBO, String permission) {
		return rolePermissionDAO.checkPermission(roleBO, permission);
	}
	

}
