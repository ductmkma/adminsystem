package com.zent.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zent.entities.UserBO;

@Repository("baseDAO")
public abstract class BaseDAO<T> implements IBaseDAO<T>{
	@Autowired
	SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T findById(Class<T> t,Long id) {
		return (T) getSession().get(t, id);
	}

	public void save(T t) {
		 getSession().save(t);
	}

	public void update(T t) {
		getSession().update(t);
	}

	public void deleteById(Long id) {
		getSession().delete(id);
	}

	public List<T> findAll(Class<T> t) {
		return getSession().createQuery("SELECT o FROM "+t.getSimpleName() +" o").list();
	}
}
