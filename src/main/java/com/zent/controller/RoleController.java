package com.zent.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zent.dto.RoleDTO;
import com.zent.dto.UserDTO;
import com.zent.entities.RoleBO;
import com.zent.entities.UserBO;
import com.zent.json.RoleJson;
import com.zent.json.UserJson;
import com.zent.service.IRoleService;
import com.zent.util.Constants;
import com.zent.util.DTOUtils;
import com.zent.util.JsonResponse;

@Controller
public class RoleController {
	@Autowired
	IRoleService roleService;

	// show
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public String getAll(Model model, HttpSession session) {
		// model.addAttribute("userDTO", new UserDTO());
		return "role";
	}

	// add role
	@RequestMapping(value = "/role/add", method = RequestMethod.POST)
	public @ResponseBody JsonResponse add(HttpServletRequest request, HttpServletResponse response, Model model) {
		String action = request.getParameter("action");
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		JsonResponse res = new JsonResponse();
		if (action.equals("add")) {
			try {
				RoleBO roleBO = new RoleBO();
				roleBO.setCode(code);
				roleBO.setName(name);
				roleService.insert(roleBO);
				res.setStatus("SUCCESS");
				res.setResult(new Boolean(true));
			} catch (Exception e1) { // TODO Auto-generated catch block
				res.setStatus("FAIL");
			}
		}
		return res;
	}

	// edit
	@RequestMapping(value = "/role/edit", method = RequestMethod.POST)
	public @ResponseBody JsonResponse edit(HttpServletRequest request, HttpServletResponse response, Model model) {
		String action = request.getParameter("action");
		Long id = Long.parseLong(request.getParameter("id"));
		String code_edit = request.getParameter("code_edit");
		String name_edit = request.getParameter("name_edit");
		JsonResponse res = new JsonResponse();
		if (action.equals("edit")) {
			try {
				RoleBO roleBO = new RoleBO();
				roleBO.setId(id);
				RoleBO roleBO2 = roleService.getUserById(roleBO);
				roleBO2.setCode(code_edit);
				roleBO2.setName(name_edit);
				roleService.update(roleBO2);
				res.setStatus("SUCCESS");
				res.setResult(new Boolean(true));
			} catch (Exception e1) { // TODO Auto-generated catch block
				res.setStatus("FAIL");
			}
		}
		return res;
	}

	// delete
	@RequestMapping(value = "/role/delete", method = RequestMethod.POST)
	public @ResponseBody JsonResponse delete(HttpServletRequest request, HttpServletResponse response, Model model) {
		String action = request.getParameter("action");
		Long id = Long.parseLong(request.getParameter("id"));
		JsonResponse res = new JsonResponse();
		if (action.equals("delete")) {
			try {
				RoleBO roleBO = new RoleBO();
				roleBO.setId(id);
				RoleBO roleBO2 = roleService.getUserById(roleBO);
				roleService.delete(roleBO2);
				res.setStatus("SUCCESS");
				res.setResult(new Boolean(true));
			} catch (Exception e1) { // TODO Auto-generated catch block
				res.setStatus("FAIL");
			}
		}
		return res;
	}

	// get list user
	@RequestMapping(value = "/listrole", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String springPaginationDataTables(HttpServletRequest request) throws IOException {

		// Fetch the page number from client
		Integer pageNumber = 0;
		if (null != request.getParameter("iDisplayStart"))
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
		// Fetch search parameter
		String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		Constants.pageSizeDataTable = pageDisplayLength;
		Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
		Integer page = (iDisplayStart / pageDisplayLength) + 1;
		// Create page list data
		List<RoleBO> listRoleBO = new ArrayList<RoleBO>();
		RoleBO roleBO = new RoleBO();
		roleBO.setName(searchParameter);
		listRoleBO = roleService.search(roleBO, page);
		List<RoleDTO> listRoleDTO = new ArrayList<RoleDTO>();
		listRoleDTO = DTOUtils.mapList(listRoleBO, RoleDTO.class);
		RoleJson roleJson = new RoleJson();
		// Set Total display record
		if (searchParameter.equals("")) {
			roleJson.setiTotalDisplayRecords(roleService.getAll().size());
			// Set Total record
			roleJson.setiTotalRecords(roleService.getAll().size());
		} else {
			roleJson.setiTotalDisplayRecords(listRoleDTO.size());
			// Set Total record
			roleJson.setiTotalRecords(listRoleDTO.size());
		}
		roleJson.setAaData(listRoleDTO);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(roleJson);
		return json2;
	}
}
