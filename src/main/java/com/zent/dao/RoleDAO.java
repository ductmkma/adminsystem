package com.zent.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.zent.entities.RoleBO;
import com.zent.entities.UserBO;
import com.zent.util.DTOUtils;

@Repository("roleDAO")
public class RoleDAO extends BaseDAO<RoleBO> implements IRoleDAO {

	@Override
	public List<RoleBO> getAll() {
		String hql = "FROM RoleBO r where r.deletedAt is null";
		return super.getSession().createQuery(hql).list();
	}

	@Override
	public List<RoleBO> search(RoleBO roleBO, Integer page) {
		String hql = "SELECT r FROM RoleBO r where r.deletedAt is null";
		if (roleBO.getName() != null || roleBO.getName().equals("")) {
			hql += " AND r.name LIKE :name";
		}
		Query query = super.getSession().createQuery(hql);
		if (roleBO.getName() != null || roleBO.getName().equals("")) {
			query.setParameter("name", "%" + roleBO.getName() + "%");
		}
		return query.getResultList();
	}

	@Override
	public RoleBO getUserById(RoleBO roleBO) {
		return (RoleBO) getSession().createQuery("SELECT r FROM RoleBO r WHERE r.id=:id")
				.setParameter("id", roleBO.getId()).uniqueResult();
	}

	@Override
	public void insert(RoleBO roleBO) {
		Date date = new Date();
		roleBO.setCreatedAt(date);
		getSession().save(roleBO);
	}

	@Override
	public void delete(RoleBO roleBO) {
		Date date = new Date();
		roleBO.setDeletedAt(date);
		getSession().update(roleBO);

	}

	@Override
	public void update(RoleBO roleBO) {
		Date date = new Date();
		roleBO.setUpdatedAt(date);
		getSession().update(roleBO);

	}

}
