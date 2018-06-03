package com.zent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zent.dao.IUserRoleDAO;
import com.zent.entities.RoleBO;
import com.zent.entities.UserBO;
import com.zent.entities.UserRoleBO;

@Service("userRoleService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserRoleService implements IUserRoleService {
	@Autowired
	IUserRoleDAO userRoleDAO;

	@Override
	public List<UserRoleBO> listUserRoleByUserId(Long userId) {
		return userRoleDAO.listUserRoleByUserId(userId);
	}

	@Override
	public void insertUserRole(Long userId, Long roleId) {
		userRoleDAO.insertUserRole(userId, roleId);
	}

	@Override
	public void deleteUserRole(Long userId, Long roleId) {
		UserBO userBO = new UserBO();
		userBO.setId(userId);
		RoleBO roleBO = new RoleBO();
		roleBO.setId(roleId);
		UserRoleBO userRoleBO = new UserRoleBO(userBO, roleBO);
		userRoleDAO.deleteUserRole(userRoleBO);
		
	}

}
