package com.zent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zent.dao.IMenuDAO;
import com.zent.entities.MenuBO;
import com.zent.entities.RoleBO;
@Service("menuService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MenuService implements IMenuService {
	@Autowired
	IMenuDAO menuDAO;
	
	public IMenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(IMenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	@Override
	public List<MenuBO> getAll() {
		return menuDAO.getAll();
	}

	@Override
	public List<MenuBO> getMenusByRoleId(RoleBO roleBO) {
		return menuDAO.getMenusByRoleId(roleBO);
	}

	public List<MenuBO> getMenus(List<GrantedAuthority> roleBO) {
		return menuDAO.getMenus(roleBO);
	}

}
