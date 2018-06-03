package com.zent.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import com.zent.entities.MenuBO;
import com.zent.entities.RolePermissionBO;
@Repository("rolePermissionDAO")
public class RolePermissionDAO extends BaseDAO<RolePermissionBO> implements IRolePermissionDAO{

	public Boolean checkPermission(List<GrantedAuthority> roleBO, String permission) {
			List<String> roles = new ArrayList<String>();
		for(GrantedAuthority autho:roleBO){
			roles.add(autho.getAuthority().replace("ROLE_", ""));
		}
		// truyá»�n danh sÃ¡ch vÃ o cáº§n cÃ³ ()
		return ((Long)getSession().createQuery("SELECT COUNT(rp.id) FROM RolePermissionBO rp WHERE rp.role.code IN (:roles) AND rp.permission.code = :permission")
				.setParameter("roles", roles).setParameter("permission", permission).uniqueResult()) > 0;
	}
}
