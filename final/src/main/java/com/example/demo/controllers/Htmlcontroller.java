package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bookAndUser.BookOperation;
import bookAndUser.UserOperation;
import operation.EditBook;
import operation.SearchAndBook;

@Controller
public class Htmlcontroller {
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(@RequestParam(required = false) String id, Model model) {
		// SearchAndBook b = new SearchAndBook();
		PassObject p = new PassObject();
		p.id = id;
		model.addAttribute("index", p);
		// p.roomCombination = b.possibleRoomNumber(p.people);
		// model.addAttribute("roomCommbination", p.roomCombination);
		return "index";
	}

	@RequestMapping(value = "/test.html", method = RequestMethod.GET)
	public String test(@RequestParam(required = false) String id, String checkout, String checkin, String city,
			int people, int room, String roomCombin, Integer stars, String sort, Model model) {
		SearchAndBook b = new SearchAndBook();
		PassObject p = new PassObject();
		SearchAndBook s = new SearchAndBook();
		PassObject test2 = new PassObject();
		String[] tmp;
		p.id=id;
		p.people = people;
		p.checkin = checkin;
		p.checkout = checkout;
		p.city = city;
		p.room = room;
		List<Integer[]> e = b.possibleRoomCombination(people, room);
		p.roomcombination = new ArrayList<String>();
		for (int i = 0; i < e.size(); i++) {
			p.roomcombination.add("" + e.get(i)[0] + "," + e.get(i)[1] + "," + e.get(i)[2]);
		}
		if (roomCombin != null) {
			p.roomCombin=roomCombin;
			int[] roomarray = new int[roomCombin.split(",").length];
			for (int j = 0; j < roomCombin.split(",").length; j++) {
				roomarray[j] = Integer.parseInt(roomCombin.split(",")[j]);
			}
			tmp = s.checkVacancy(checkin, checkout, roomarray, city, sort, stars);
		}else {
			p.roomCombin=""+e.get(0)[0]+","+e.get(0)[1]+","+e.get(0)[2];
			tmp = (s.firstSearch(checkin, checkout, city, people, room));
		}
		List<String> l = new ArrayList<String>();
		for (int i = 1; i < tmp.length; i++) {
			l.add(tmp[i]);
		}
		p.title = tmp[0];
		p.re = l;
		model.addAttribute("test", p);
		model.addAttribute("test2", test2);
		return "test";
	}
	@RequestMapping("/tobook")
	public String tobook(@RequestParam(required=true) String checkin,String checkout,String roomCombin,String id, String info, Model model) {
		PassObject p = new PassObject();
		p.checkin=checkin;
		p.checkout=checkout;
		p.roomCombin=roomCombin;
		p.info=info;
		p.id = id;
		p.hotelid=Integer.parseInt(info.split(" ")[3].split("Hotel")[0].strip());
		p.address=info.split(" ")[9]+info.split(" ")[10].split("Total")[0].strip();
		p.price=info.split(" ")[13];
		model.addAttribute("tobook", p);
		return "/tobook";
	}
	@RequestMapping("/account")
	public String account(@RequestParam(required = true) String id, Model model) {
		PassObject p = new PassObject();
		//BookOperation b = new BookOperation();
		p.id=id;
		String[] tmp=BookOperation.bookOfUser(id).split("=================================");
		List<String> l = new ArrayList<String>();
		for (int i = 0; i < tmp.length-1; i++) {
			l.add(tmp[i]);
		}
		p.re=l;
		model.addAttribute("account", p);
		return "/account";
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(required = true) String id, String info,Model model) {
		EditBook b = new EditBook();
		b.deleteBook(info.split(" ")[2].split("Hotel")[0].strip());
		System.out.print(info.split(" ")[2].split("Hotel")[0].strip());
		return "redirect:/account?id="+id;
	}
	@RequestMapping(value = "/commit", method = RequestMethod.GET)
	public String commit(@RequestParam(required = true) String id, String checkin,String checkout,int hotelid,String roomCombin,Model model) {
		SearchAndBook b = new SearchAndBook();
		int[] roomarray = new int[roomCombin.split(",").length];
		for (int j = 0; j < roomCombin.split(",").length; j++) {
			roomarray[j] = Integer.parseInt(roomCombin.split(",")[j]);
		}
		b.commitBook(checkin, checkout, hotelid, id, roomarray);
		return "redirect:/account?id="+id;
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

	@PostMapping("/test2")
	public String posttest(@ModelAttribute PassObject passObject) {
//		if(passObject.id==null) {
//			return"redirect:/login.html";
//		}
		return "redirect:/test.html?checkin=" + passObject.checkin + "&checkout=" + passObject.checkout + "&city="
				+ passObject.city + "&people=" + passObject.people + "&room=" + passObject.room + "&sort="
				+ passObject.sort + "&stars=" + passObject.stars + "&roomCombin=" + passObject.roomCombin;
	}

	@PostMapping("/index")
	public String postindex(@ModelAttribute PassObject passObject) {
		System.out.print(passObject.id.isBlank());
		if(passObject.id.isBlank()) {
			return "redirect:/test.html?checkin=" + passObject.checkin + "&checkout=" + passObject.checkout + "&city="
					+ passObject.city + "&people=" + passObject.people + "&room=" + passObject.room;
		}
		return "redirect:/test.html?checkin=" + passObject.checkin + "&checkout=" + passObject.checkout + "&city="
				+ passObject.city + "&people=" + passObject.people + "&room=" + passObject.room+"&id="+passObject.id;
	}
}
