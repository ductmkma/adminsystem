package com.zent.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zent.entities.RoleBO;
import com.zent.entities.UserBO;
import com.zent.service.IRoleMenuService;
import com.zent.util.JsonResponse;

@Controller
public class RoleMenuController {
	@Autowired
	IRoleMenuService roleMenuService;
	@PreAuthorize("hasPermission('', 'ADD_ROLE_MENU') or hasPermission('', 'DELETE_ROLE_MENU') ")
	@RequestMapping(value = "/insertrolemenu", method = RequestMethod.POST)
	public @ResponseBody JsonResponse add(HttpServletRequest request, HttpServletResponse response, Model model) {
		String status = request.getParameter("status");
		Long roleId = Long.parseLong(request.getParameter("roleId"));
		Long menuId = Long.parseLong(request.getParameter("menuId"));
		JsonResponse res = new JsonResponse();
		if (status.equals("select_node")) {
			try {
				roleMenuService.insertRoleMenu(roleId, menuId);
				res.setStatus("ADDED");
				res.setResult(new Boolean(true));
			} catch (Exception e1) { // TODO Auto-generated catch block
				res.setStatus("FAIL");
			}
		}else if(status.equals("deselect_node")) {
			try {
				roleMenuService.deleteRoleMenu(roleId, menuId);
				res.setStatus("DELETED");
				res.setResult(new Boolean(true));
			} catch (Exception e1) { // TODO Auto-generated catch block
				res.setStatus("FAIL");
			}
		}
		return res;
	}
}
