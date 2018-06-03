package com.zent.controller;

import java.awt.Menu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zent.dto.MenuDTO;
import com.zent.dto.RoleDTO;
import com.zent.entities.MenuBO;
import com.zent.entities.RoleBO;
import com.zent.json.RoleJson;
import com.zent.service.IMenuService;
import com.zent.util.Constants;
import com.zent.util.DTOUtils;

@Controller
public class MenuController {
	@Autowired
	IMenuService menuService;
	@RequestMapping(value = "/listmenu", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getAllMenus(HttpServletRequest request) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		RoleBO roleBO = new RoleBO();
		roleBO.setId(id);
		// list lấy ra theo id
		List<MenuBO> listMenuRoleBO = new ArrayList<MenuBO>();
		listMenuRoleBO = menuService.getMenusByRoleId(roleBO);
		List<MenuDTO> listMenuRoleDTO = new ArrayList<MenuDTO>();
		// list lấy ra toàn bộ
		List<MenuBO> listMenuBO = new ArrayList<MenuBO>();
		listMenuBO = menuService.getAll();
		List<MenuDTO> listMenuDTO = new ArrayList<MenuDTO>();
		for (int i = 0; i < listMenuBO.size(); i++) {
			for (int j = 0; j < listMenuRoleBO.size(); j++) {
				if (listMenuBO.get(i).getId().equals(listMenuRoleBO.get(j).getId())) {
					listMenuBO.remove(i);
				}
			}
		}
		for (MenuBO menubo : listMenuRoleBO) {
			listMenuDTO.add(menubo.toDTO(true));
		}
		for (MenuBO menubo : listMenuBO) {
			listMenuDTO.add(menubo.toDTO(false));
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(listMenuDTO);
		return json2;
	}
}
