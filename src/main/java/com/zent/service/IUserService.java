package com.zent.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.zent.entities.UserBO;

public interface IUserService {
	public List<UserBO> getAll();
	public List<UserBO> search(UserBO user, Integer page);
	public UserBO getUserById(UserBO user);
	public UserBO getUserByUserName(UserBO user);
	public void insert(UserBO user);
	public void delete(UserBO user);
	public void update(UserBO user);
	public UserDetails loadUserByUsername(String username);
}
