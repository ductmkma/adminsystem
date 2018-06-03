package com.zent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zent.dao.IRoleMenuDAO;
import com.zent.entities.MenuBO;
import com.zent.entities.RoleBO;
import com.zent.entities.RoleMenuBO;

@Service("roleMenuService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RoleMenuService implements IRoleMenuService {
	@Autowired
	IRoleMenuDAO roleMenuDAO;

	@Override
	public void insertRoleMenu(Long roleId, Long menuId) {
		RoleBO roleBO = new RoleBO();
		roleBO.setId(roleId);
		MenuBO menuBO = new MenuBO();
		menuBO.setId(menuId);
		RoleMenuBO roleMenuBO = new RoleMenuBO(menuBO, roleBO);
		roleMenuDAO.insertRoleMenu(roleMenuBO);
		
	}

	@Override
	public void deleteRoleMenu(Long roleId, Long menuId) {
		RoleBO roleBO = new RoleBO();
		roleBO.setId(roleId);
		MenuBO menuBO = new MenuBO();
		menuBO.setId(menuId);
		RoleMenuBO roleMenuBO = new RoleMenuBO(menuBO, roleBO);
		roleMenuDAO.deleteRoleMenu(roleMenuBO);
		
	}

	
	
}
