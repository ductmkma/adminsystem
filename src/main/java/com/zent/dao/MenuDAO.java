package com.zent.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import com.zent.entities.MenuBO;
import com.zent.entities.RoleBO;
@Repository("menuDAO")
public class MenuDAO extends BaseDAO<MenuBO> implements IMenuDAO {

	@Override
	public List<MenuBO> getAll() {
		return super.getSession().createQuery("SELECT m FROM MenuBO m").list();
	}

	@Override
	public List<MenuBO> getMenusByRoleId(RoleBO roleBO) {
		return super.getSession().createQuery("SELECT m FROM MenuBO m JOIN m.roleMenus rm WHERE rm.role.id=:id").setParameter("id", roleBO.getId()).list();
	}

	public List<MenuBO> getMenus(List<GrantedAuthority> roleBO) {
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority autho:roleBO){
			roles.add(autho.getAuthority().replace("ROLE_", ""));
		}
		return getSession().createQuery("SELECT m FROM MenuBO m JOIN m.roleMenus rm WHERE rm.role.code IN (:roles)")
				.setParameter("roles", roles).list();
	}

}
