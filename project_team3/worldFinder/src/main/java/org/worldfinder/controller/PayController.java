package org.worldfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/pay")
public class PayController {
	
	@GetMapping
	public String pay() {
		return "userPost/test";
	}
}
