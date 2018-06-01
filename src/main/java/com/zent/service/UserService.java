package com.zent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zent.dao.IUserDAO;
import com.zent.entities.UserBO;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserService implements IUserService, UserDetailsService { //phải có implement UserDetailsServiece
	@Autowired
	IUserDAO userDAO;
	@Override
	public List<UserBO> getAll() {
		List<UserBO> listUser = userDAO.getAll();
		return listUser;
	}
	@Override
	public List<UserBO> search(UserBO user, Integer page) {
		List<UserBO> listUser = userDAO.search(user, page);
		return listUser;
	}
	@Override
	public UserBO getUserById(UserBO userBO) {
		UserBO userReturn = userDAO.getUserById(userBO);
		return userReturn;
	}
	@Override
	public void insert(UserBO user) {
		userDAO.insert(user);
		
	}
	@Override
	public void delete(UserBO user) {
		userDAO.delete(user);
		
	}
	@Override
	public void update(UserBO user) {
		userDAO.update(user);
		
	}

	public UserDetails loadUserByUsername(String username) { //Một hàm cung cấp sẵn từ UserDetailsService
		return userDAO.loadUserByUsername(username);
	}

}
