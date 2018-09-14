package com.ways.controller;

import java.util.ArrayList;
import java.util.List;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value="/velocity")
public class VelocityController {

	@RequestMapping(value="/tovelo")
	public String tovelo(Model model){
		List <String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		model.addAttribute("list",list);
		return "view";
	}
}
