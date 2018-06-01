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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zent.dto.UserDTO;
import com.zent.entities.PermissionBO;
import com.zent.entities.UserBO;
import com.zent.json.PermissionJson;
import com.zent.json.UserJson;
import com.zent.service.IPermissionService;
import com.zent.util.Constants;
import com.zent.util.JsonResponse;

@Controller
public class PermissionController {
	@Autowired
	IPermissionService permissionService;

	@RequestMapping(value = "/permission", method = RequestMethod.GET)
	public String getAll(Model model, HttpSession session) {
		return "permission";
	}

	@RequestMapping(value = "/role/{id}/permission", method = RequestMethod.GET)
	public String permission(@PathVariable("id") Long id, Model model, HttpSession session) {
		model.addAttribute("roleId", id);
		model.addAttribute("listPermission", permissionService.getAll());
		model.addAttribute("listRolePermission", permissionService.getAllRolePermission());
		return "permission";
	}
	// add role_permission
	@RequestMapping(value = "/role/{id}/permission", method = RequestMethod.POST)
	public @ResponseBody JsonResponse add(HttpServletRequest request, HttpServletResponse response, Model model) {
		Long roleId = Long.parseLong(request.getParameter("role_id"));
		Long permissionId = Long.parseLong(request.getParameter("permission_id"));
		Long checked = Long.parseLong(request.getParameter("checked"));
		JsonResponse res = new JsonResponse();
		if (checked==1) {
			try {
				permissionService.deleteRolePermission(roleId, permissionId);
				res.setStatus("DELETED");
				res.setResult(new Boolean(true));
			} catch (Exception e1) { // TODO Auto-generated catch block
				res.setStatus("FAIL");
			}
		}else if(checked==0) {
			try {
				permissionService.addRolePermission(roleId, permissionId);
				res.setStatus("ADDED");
				res.setResult(new Boolean(true));
			} catch (Exception e1) { // TODO Auto-generated catch block
				res.setStatus("FAIL");
			}
		}
		return res;
	}
	
	@RequestMapping(value = "/listpermission", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
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
		List<PermissionBO> listPermissionBO = new ArrayList<PermissionBO>();
		PermissionBO permissionBO = new PermissionBO();
		permissionBO.setName(searchParameter);
		listPermissionBO = permissionService.search(permissionBO, page);

		PermissionJson permissionBOJson = new PermissionJson();
		// Set Total display record
		if (searchParameter.equals("")) {
			permissionBOJson.setiTotalDisplayRecords(permissionService.getAll().size());
			// Set Total record
			permissionBOJson.setiTotalRecords(permissionService.getAll().size());
		} else {
			permissionBOJson.setiTotalDisplayRecords(listPermissionBO.size());
			// Set Total record
			permissionBOJson.setiTotalRecords(listPermissionBO.size());
		}
		permissionBOJson.setAaData(listPermissionBO);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(permissionBOJson);
		return json2;
	}
}
