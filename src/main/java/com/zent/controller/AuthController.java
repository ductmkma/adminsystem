package com.zent.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zent.dao.UserDAO;
import com.zent.entities.UserBO;
import com.zent.service.IMenuService;
import com.zent.service.IUserService;

@Controller
public class AuthController {
	@Autowired
	IMenuService menuService;
	@Autowired
	IUserService userSerivice;
	@RequestMapping(value = { "/login"})
	public String login(@RequestParam(value = "message", required = false) final String message, final Model model) {
		if (message != null) {
			model.addAttribute("message", "Tài khoản hoặc mật khẩu sai!");
		}
		return "login";
	}
	@RequestMapping("/logout")
	public String logout(final Model model,HttpSession session) {
		Cookie cookie = new Cookie("remember-me", ""); // bake cookie
		cookie.setMaxAge(-1);
		model.addAttribute("message", "Logged out!");
		return "login";
	}
	
	@RequestMapping(value = { "/index"})
	public String index(Model model,HttpSession session) {
		model.addAttribute("menus", menuService.getMenus((List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities()));
		return "index";
	}
	@RequestMapping("/403")
	public String page403() {
		return "page_403";
	}
}
