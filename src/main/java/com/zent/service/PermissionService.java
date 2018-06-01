package com.zent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zent.dao.IPermissionDAO;
import com.zent.entities.PermissionBO;
import com.zent.entities.RolePermissionBO;

@Service("permissionService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PermissionService implements IPermissionService {
	@Autowired
	IPermissionDAO permissonDAO;
	@Override
	public List<PermissionBO> getAll() {
		return permissonDAO.getAll();
	}

	@Override
	public List<PermissionBO> search(PermissionBO permissionBO, Integer page) {
		
		return permissonDAO.search(permissionBO, page);
	}

	@Override
	public List<RolePermissionBO> getAllRolePermission() {
		return permissonDAO.getAllRolePermission();
	}

	@Override
	public void deleteRolePermission(Long roleId, Long permissionId) {
		permissonDAO.deleteRolePermission(roleId,permissionId);
		
	}

	@Override
	public void addRolePermission(Long roleId, Long permissionId) {
		permissonDAO.addRolePermission(roleId, permissionId);
		
	}

}
