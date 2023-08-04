package org.worldfinder.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.worldfinder.domain.Criteria;
import org.worldfinder.domain.PageDTO;
import org.worldfinder.service.ManagerFoodService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/manager/food/*")
public class ManagerFoodController {

	
	@Setter(onMethod_ = @Autowired)
	private ManagerFoodService service;
	
	@GetMapping("/foodList")
	public String list(Model model, Criteria cri) {
		log.info("controller itemList...");
		
		model.addAttribute("list", service.getListWithPaging(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.countFoodList()));
		return "manager/food/foodList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}