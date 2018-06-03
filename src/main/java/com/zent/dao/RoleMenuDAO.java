package com.zent.dao;

import org.springframework.stereotype.Repository;

import com.zent.entities.RoleMenuBO;

@Repository("roleMenuDAO")
public class RoleMenuDAO extends BaseDAO<RoleMenuBO> implements IRoleMenuDAO  {

	@Override
	public void insertRoleMenu(RoleMenuBO roleMenuBO) {
		super.getSession().save(roleMenuBO);
		
	}
	@Override
	public void deleteRoleMenu(RoleMenuBO roleMenuBO) {
		super.getSession().createQuery("DELETE FROM RoleMenuBO r WHERE r.role.id=:roleId AND r.menu.id=:menuId").setParameter("roleId", roleMenuBO.getRole().getId()).setParameter("menuId", roleMenuBO.getMenu().getId()).executeUpdate();
		
	}
	
}
