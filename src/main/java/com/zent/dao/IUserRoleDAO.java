package com.zent.dao;

import java.util.List;

import com.zent.entities.UserRoleBO;

public interface IUserRoleDAO {
	public List<UserRoleBO> listUserRoleByUserId(Long userId);
	public void insertUserRole(Long userId, Long roleId);
	public void deleteUserRole(UserRoleBO userRoleBO);
}
