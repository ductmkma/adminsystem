package com.zent.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.zent.entities.UserBO;

public interface IUserDAO extends IBaseDAO<UserBO>{
	public UserBO loadUserByUsername(String username);
	public List<UserBO> getAll();
	public List<UserBO> search(UserBO userBO,Integer page);
	public UserBO getUserById(UserBO userBO);
	public void insert(UserBO userBO);
	public void update(UserBO userBO);
	public void delete(UserBO userBO);
}
