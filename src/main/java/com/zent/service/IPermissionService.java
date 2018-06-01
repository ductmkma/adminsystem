package com.zent.service;

import java.util.List;

import com.zent.entities.PermissionBO;
import com.zent.entities.RolePermissionBO;

public interface IPermissionService {
	public List<PermissionBO> getAll();
	public List<PermissionBO> search(PermissionBO permissionBO, Integer page);
	public List<RolePermissionBO> getAllRolePermission();
	public void deleteRolePermission(Long roleId,Long permissionId);
	public void addRolePermission(Long roleId,Long permissionId);
}
