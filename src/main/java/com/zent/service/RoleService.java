package com.zent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zent.dao.IRoleDAO;
import com.zent.dao.IUserDAO;
import com.zent.entities.RoleBO;
@Service("roleService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RoleService implements IRoleService {
	@Autowired
	IRoleDAO roleDAO;
	@Override
	public List<RoleBO> getAll() {
		return roleDAO.getAll();
	}

	@Override
	public List<RoleBO> search(RoleBO roleBO, Integer page) {
		return roleDAO.search(roleBO, page);
	}

	@Override
	public RoleBO getUserById(RoleBO roleBO) {
		return roleDAO.getUserById(roleBO);
	}

	@Override
	public void insert(RoleBO roleBO) {
		roleDAO.insert(roleBO);
		
	}

	@Override
	public void delete(RoleBO roleBO) {
		roleDAO.delete(roleBO);
		
	}

	@Override
	public void update(RoleBO roleBO) {
		roleDAO.update(roleBO);
		
	}

}
