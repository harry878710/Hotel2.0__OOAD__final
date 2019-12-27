package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Htmlcontroller {
	@RequestMapping("/index.html")
	public String index(Model model) {
		model.addAttribute("index", new Index());
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
	@PostMapping("/index")
	public String postindex(Index index) {	
		return "blog";
	}
}
