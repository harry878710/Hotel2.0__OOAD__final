package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bookAndUser.UserOperation;
import operation.InputException;
import operation.SearchAndBook;

@Controller
public class Htmlcontroller {
	@RequestMapping("/index.html")
	public String index(Model model) {
		model.addAttribute("index", new PassObject());
		return "index";
	}
	@RequestMapping("/about.html")
	public String about() {
		return "/about";
	}
	@RequestMapping("/blog.html")
	public String blog() {
		return "/blog";
	}
	@RequestMapping("/booking.html")
	public String booking() {
		return "/booking";
	}
	@RequestMapping("/elements.html")
	public String elements() {
		return "/elements";
	}
	@RequestMapping("/contact.html")
	public String contact() {
		return "/contact";
	}
	@RequestMapping("/login.html")
	public String login(Model model) {
		return "/login";
	}
	@RequestMapping("/register.html")
	public String register(Model model) {
		model.addAttribute("register", new PassObject());
		return "/register";
	}
	@PostMapping("/register")
	public String postregister(@ModelAttribute PassObject passObject) {
		//UserOperation.addUser("12345", "123", "123");
		return "popup";
	}
	@PostMapping("/index")
	public String postindex(@ModelAttribute PassObject passObject) throws InputException {
		SearchAndBook s = new SearchAndBook();
		int[] i = {0,1,0};
		passObject.re = "test";
		passObject.re = (s.checkVacancy("2019/12/31", 2,i,"SomeWhere","Sorted by Hotel ID(small to large)"));
		return "test";
	}
}
