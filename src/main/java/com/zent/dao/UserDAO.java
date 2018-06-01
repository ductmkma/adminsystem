package com.zent.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.zent.entities.UserBO;
import com.zent.util.SecurityUtil;

@Repository("userDAO")
public class UserDAO extends BaseDAO<UserBO> implements IUserDAO {

	@Override
	public List<UserBO> getAll() {
		String hql = "FROM UserBO u where u.deletedAt is null";
		return getSession().createQuery(hql).list();
	}

	@Override
	public List<UserBO> search(UserBO userBO, Integer page) {
		String hql = "SELECT u FROM UserBO u where u.deletedAt is null";
		if (userBO.getName() != null || userBO.getName().equals("")) {
			hql += " and u.name LIKE :name";
		}
		Query query = super.getSession().createQuery(hql);
		if (userBO.getName() != null || userBO.getName().equals("")) {
			query.setParameter("name", "%" + userBO.getName() + "%");
		}
		return query.getResultList();
	}

	@Override
	public UserBO getUserById(UserBO userBO) {
		return (UserBO) getSession().createQuery("SELECT u FROM UserBO u WHERE u.id=:id")
				.setParameter("id", userBO.getId()).uniqueResult();
	}

	@Override
	public void insert(UserBO userBO) {
		Date date = new Date();
		userBO.setCreatedAt(date);
		String password = SecurityUtil.encrypt("123456");
		userBO.setPassword(password);
		getSession().save(userBO);
	}

	@Override
	public void update(UserBO userBO) {
		Date date = new Date();
		userBO.setUpdatedAt(date);
		getSession().update(userBO);

	}

	@Override
	public void delete(UserBO userBO) {
		Date date = new Date();
		userBO.setDeletedAt(date);
		getSession().update(userBO);
	}

	public UserBO loadUserByUsername(String username) {
		return (UserBO) getSession().createQuery("SELECT o FROM UserBO o WHERE o.username=:username")
				.setParameter("username", username).uniqueResult();
	}
}
