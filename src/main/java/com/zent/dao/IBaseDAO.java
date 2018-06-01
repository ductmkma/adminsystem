package com.zent.dao;

import java.util.List;

public interface IBaseDAO<T> {
	public T findById(Class<T> t,Long id);

	public void save(T t);

	public void update(T t);
	
	public void deleteById(Long id);
	
	public List findAll(Class<T> t);
}
