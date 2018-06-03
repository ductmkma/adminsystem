package com.zent.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.zent.entities.RoleBO;
import com.zent.entities.UserBO;
import com.zent.entities.UserRoleBO;

@Repository("userRoleDAO")
public class UserRoleDAO extends BaseDAO<UserRoleBO> implements IUserRoleDAO {

	@Override
	public List<UserRoleBO> listUserRoleByUserId(Long userId) {
		return super.getSession().createQuery("SELECT ur FROM UserRoleBO ur where ur.user.id=:userId").setParameter("userId", userId).list();
	}

	@Override
	public void insertUserRole(Long userId, Long roleId) {
		SQLQuery insertQuery = getSession().createSQLQuery("INSERT INTO user_role(user_id,role_id) VALUES(?,?)");
		insertQuery.setParameter(0, userId);
		insertQuery.setParameter(1, roleId);
		insertQuery.executeUpdate();
		
		
	}

	@Override
	public void deleteUserRole(UserRoleBO userRoleBO) {
		String hql = "DELETE FROM UserRoleBO ur WHERE ur.role.id=:roleId AND ur.user.id=:userId";
		Query query = super.getSession().createQuery(hql).setParameter("roleId", userRoleBO.getRole().getId()).setParameter("userId",userRoleBO.getUser().getId());
		query.executeUpdate();
	}
	

}
