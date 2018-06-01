package com.zent.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zent.dto.UserDTO;
import com.zent.entities.UserBO;
import com.zent.json.UserJson;
import com.zent.service.IUserService;
import com.zent.util.Constants;
import com.zent.util.DTOUtils;
import com.zent.util.JsonResponse;
import com.zent.util.UploadFile;
import com.zent.validator.UserDTOValidator;

@Controller
public class UserController {
	@Autowired
	IUserService userService;
	@Autowired
	UserDTOValidator userDTOValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userDTOValidator);
	}

	public UserDTOValidator getUserDTOValidator() {
		return userDTOValidator;
	}

	public void setUserDTOValidator(UserDTOValidator userDTOValidator) {
		this.userDTOValidator = userDTOValidator;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	// show
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAll(Model model, HttpSession session) {
		model.addAttribute("userDTO", new UserDTO());
		return "usermanager";
	}

	// add
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "add";
	}

	// add post
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(ModelMap modelMap, @ModelAttribute @Validated UserDTO userDTO, BindingResult result,
			RedirectAttributes redirectAttribute) {
		if (result.hasErrors()) {
			return "add";
		} else {
			UserBO userBO = DTOUtils.map(userDTO, UserBO.class);
			String fileName = UploadFile.upload(userDTO.getImage());
			if (userDTO.getImage().getSize() > 0) {
				userBO.setImage(fileName);
			} else {
				userBO.setImage("avata.jpg");
			}
			userService.insert(userBO);
			redirectAttribute.addFlashAttribute("messeage", "Cập nhật thành công !");
			return "redirect:/";
		}

	}

	// edit
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable("id") Long id, Model model) {
		UserBO userBO = new UserBO();
		userBO.setId(id);
		UserBO userBOReturn = userService.getUserById(userBO);
		UserDTO userDTO = DTOUtils.map(userBOReturn, UserDTO.class);
		model.addAttribute("fileNameOld",userBOReturn.getImage());
		model.addAttribute("userDTO", userDTO);
		return "edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") Integer id, ModelMap modelMap, @ModelAttribute @Validated UserDTO userDTO,BindingResult result,RedirectAttributes redirectAttribute) {
		if (result.hasErrors()) {
			return "edit";
		} else {
			UserBO userBO = DTOUtils.map(userDTO, UserBO.class);
			if (userDTO.getImage().getSize() > 0) {
				String fileName = UploadFile.upload(userDTO.getImage());
				userBO.setImage(fileName);
			} else {
				userBO.setImage(userDTO.getFileNameOld());
			}
			userService.update(userBO);
			redirectAttribute.addFlashAttribute("messeage", "Cập nhật thành công !");
			return "redirect:/";
		}
	}

	// delete
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody JsonResponse add(HttpServletRequest request, HttpServletResponse response, Model model) {
		String action = request.getParameter("action");
		Long id = Long.parseLong(request.getParameter("id"));
		JsonResponse res = new JsonResponse();
		if (action.equals("delete")) {
			try {
				UserBO userBO = new UserBO();
				userBO.setId(id);
				UserBO userBO2 = userService.getUserById(userBO);
				userService.delete(userBO2);
				res.setStatus("SUCCESS");
				res.setResult(new Boolean(true));
			} catch (Exception e1) { // TODO Auto-generated catch block
				res.setStatus("FAIL");
			}
		}
		return res;
	}

	// get list user
	@RequestMapping(value = "/listuser", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
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
		List<UserBO> listUserBO = new ArrayList<UserBO>();
		UserBO userBO = new UserBO();
		userBO.setName(searchParameter);
		listUserBO = userService.search(userBO, page);
		List<UserDTO> listUSerDTO = new ArrayList<UserDTO>();
		listUSerDTO = DTOUtils.mapList(listUserBO, UserDTO.class);
		UserJson userJson = new UserJson();
		// Set Total display record
		if (searchParameter.equals("")) {
			userJson.setiTotalDisplayRecords(userService.getAll().size());
			// Set Total record
			userJson.setiTotalRecords(userService.getAll().size());
		} else {
			userJson.setiTotalDisplayRecords(listUSerDTO.size());
			// Set Total record
			userJson.setiTotalRecords(listUSerDTO.size());
		}
		userJson.setAaData(listUSerDTO);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(userJson);
		return json2;
	}

	// read Images
	@RequestMapping(value = "/avata/{id}", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, @PathVariable("id") Long id) throws IOException {
		UserBO userBO = new UserBO();
		userBO.setId(id);
		String avata = userService.getUserById(userBO).getImage();
		File file = new File(Constants.PATH + avata);
		response.setContentType("image/*");
		response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
		byte[] buffer = new byte[1024];
		int bytesRead = 0;
		while ((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		outStream.flush();
		inStream.close();
	}
	
	
	

}
