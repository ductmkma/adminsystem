package com.zent.service;

import java.util.List;

import com.zent.entities.UserRoleBO;

public interface IUserRoleService {
	public List<UserRoleBO> listUserRoleByUserId(Long userId);
	public void insertUserRole(Long userId,Long roleId);
	public void deleteUserRole(Long userId,Long roleId);
	
}
