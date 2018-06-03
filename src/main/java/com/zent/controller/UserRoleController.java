package com.zent.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zent.entities.RoleBO;
import com.zent.entities.UserBO;
import com.zent.service.IRoleService;
import com.zent.service.IUserRoleService;
import com.zent.service.IUserService;
import com.zent.util.JsonResponse;

@Controller
public class UserRoleController {
	@Autowired
	IRoleService roleService;
	@Autowired
	IUserRoleService userRoleService;
	@Autowired
	IUserService userService;
	@RequestMapping(value = "/users/{id}/roles", method = RequestMethod.GET)
	public String add(@PathVariable("id") Long userId,Model model) {
		List<RoleBO> listRoleBO = roleService.getAll();
		model.addAttribute("userId", userId);
		model.addAttribute("user",userService.getUserById(new UserBO(userId)));
		model.addAttribute("listRole", listRoleBO);
		model.addAttribute("listUserRole", userRoleService.listUserRoleByUserId(userId));
		return "userrole";
	}
	// add role_permission
		@RequestMapping(value = "/users/{id}/roles", method = RequestMethod.POST)
		public @ResponseBody JsonResponse add(HttpServletRequest request, HttpServletResponse response, Model model) {
			Long roleId = Long.parseLong(request.getParameter("role_id"));
			Long userId = Long.parseLong(request.getParameter("user_id"));
			Long checked = Long.parseLong(request.getParameter("checked"));
			JsonResponse res = new JsonResponse();
			if (checked==1) {
				try {
					userRoleService.deleteUserRole(userId, roleId);
					res.setStatus("DELETED");
					res.setResult(new Boolean(true));
				} catch (Exception e1) { // TODO Auto-generated catch block
					res.setStatus("FAIL");
				}
			}else if(checked==0) {
				try {
					userRoleService.insertUserRole(userId, roleId);
					res.setStatus("ADDED");
					res.setResult(new Boolean(true));
				} catch (Exception e1) { // TODO Auto-generated catch block
					res.setStatus("FAIL");
				}
			}
			return res;
		}
}
