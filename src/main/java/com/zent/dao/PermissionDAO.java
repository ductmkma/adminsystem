package com.zent.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import com.zent.entities.MenuBO;
import com.zent.entities.PermissionBO;
import com.zent.entities.RoleBO;
import com.zent.entities.RolePermissionBO;
import com.zent.entities.UserBO;

@Repository("permissionDAO")
public class PermissionDAO extends BaseDAO<PermissionBO> implements IPermissionDAO {

	public List<PermissionBO> getPermissions(List<GrantedAuthority> roleBO) {
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority autho : roleBO) {
			roles.add(autho.getAuthority().replace("ROLE_", ""));
		}
		// truyền danh sách vào cần có ( )
		return getSession().createQuery("SELECT p FROM PermissionBO p JOIN p.rolePermissionBOs pr WHERE pr.role.code IN (:roles) ").setParameter("roles", roles).list();
	}

	@Override
	public List<PermissionBO> getAll() {
		String hql = "SELECT p FROM PermissionBO p";
		return super.getSession().createQuery(hql).list();
	}

	@Override
	public List<PermissionBO> search(PermissionBO permissionBO, Integer page) {
		String hql = "SELECT p FROM PermissionBO p";
		if (permissionBO.getName() != null || permissionBO.getName().equals("")) {
			hql += " WHERE p.name LIKE :name";
		}
		Query query = super.getSession().createQuery(hql);
		if (permissionBO.getName() != null || permissionBO.getName().equals("")) {
			query.setParameter("name", "%" + permissionBO.getName() + "%");
		}
		return query.getResultList();
	}

	@Override
	public List<RolePermissionBO> getAllRolePermission() {
		String hql = "SELECT rp FROM RolePermissionBO rp";
		return super.getSession().createQuery(hql).list();
	}

	@Override
	public void deleteRolePermission(Long roleId, Long permissionId) {
		String hql = "DELETE FROM RolePermissionBO rp WHERE rp.role.id=:roleId AND rp.permission.id=:permissionId";
		Query query = super.getSession().createQuery(hql).setParameter("roleId", roleId).setParameter("permissionId", permissionId);
		query.executeUpdate();
		
	}

	@Override
	public void addRolePermission(Long roleId, Long permissionId) {
		RoleBO roleBO = new RoleBO();
		roleBO.setId(roleId);
		PermissionBO permissionBO = new PermissionBO();
		permissionBO.setId(permissionId);
		RolePermissionBO rolePermissionBO = new RolePermissionBO(permissionBO, roleBO);
		super.getSession().save(rolePermissionBO);
		
		
	}

}
