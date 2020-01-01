package com.example.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bookAndUser.UserOperation;
import operation.SearchAndBook;


@Controller
public class Htmlcontroller {
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(@RequestParam(required = false) String id, Model model) {
		SearchAndBook b = new SearchAndBook();
		PassObject p = new PassObject();
		p.id = id;
		model.addAttribute("index", p);
		p.roomCombination = b.possibleRoomNumber(p.people);
		model.addAttribute("roomCommbination", p.roomCombination);
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
		model.addAttribute("login", new PassObject());
		return "/login";
	}

	@PostMapping("/login")
	public String postlogin(@ModelAttribute PassObject passObject) {
		passObject.n = UserOperation.userLogin(passObject.id, passObject.password);
		return "loginpop";
	}

	@RequestMapping("/register.html")
	public String register(Model model) {
		model.addAttribute("register", new PassObject());
		return "/register";
	}

	@PostMapping("/loginpop")
	public String postloginpop(@ModelAttribute PassObject passObject) {
		return "redirect:/index.html?id=" + passObject.id;
	}

	@PostMapping("/register")
	public String postregister(@ModelAttribute PassObject passObject) {
		passObject.n = UserOperation.addUser(passObject.id, passObject.password, passObject.confirm);
		return "popup";
	}

	@PostMapping("/popup")
	public String postpopup(@ModelAttribute PassObject passObject) {
		return "redirect:/index.html?id=" + passObject.id;
	}

	@PostMapping("/index")
	public String postindex(@ModelAttribute PassObject passObject) {
		SearchAndBook s = new SearchAndBook();
		//int[] i = { 0, 1, 0 };
		// passObject.re = "test";
		passObject.re = (s.firstSearch(passObject.checkin, passObject.checkout, passObject.city, passObject.people, passObject.room));
		return "test";
	}
}
