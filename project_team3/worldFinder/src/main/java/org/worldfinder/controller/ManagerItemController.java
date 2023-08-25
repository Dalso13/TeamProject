package org.worldfinder.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.worldfinder.domain.Criteria;
import org.worldfinder.domain.ItemFilterVO;
import org.worldfinder.domain.ItemVO;
import org.worldfinder.domain.PageDTO;
import org.worldfinder.service.ManagerItemService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/manager/item/*")
public class ManagerItemController {

	
	@Setter(onMethod_ = @Autowired)
	private ManagerItemService service;
	
	
	@GetMapping("/itemList")
	public String list(Model model, Criteria cri) {
		log.info("controller itemList...");
		
		model.addAttribute("list", service.getListWithPaging(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.countItemList()));
		return "manager/item/itemList";
	}
	
	
	@GetMapping("/itemList2")
	public String toList2(Model model, Criteria cri) {
		log.info("controller itemList2...");
		
		return "manager/item/itemList2";
	}

	@PostMapping("/itemList3")
	public String toList3(Model model , @RequestParam ("country") String country) {
		log.info("controller itemList3...");
		model.addAttribute("country", service.countryCategory(country));

		return "manager/item/itemList3";
	}
	
	@GetMapping("/itemList3")
	public String toList3(Model model, Criteria cri) {
		log.info("controller itemList3...");


		return "manager/item/itemList3";
	}
	
	
	//아이템 리스트를 비동기로 받아오기
   	@GetMapping(value = "/ajaxItemList",
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
   	@ResponseBody
   	public Map<String, Object> getItemList( 
   			@RequestParam(value = "page", defaultValue = "1", required = false) int page) throws Exception {
		
   		Criteria cri = new Criteria(page);
   		
   		Map<String, Object> result = new HashMap<String, Object>();
  		PageDTO pdto = new PageDTO(cri, service.countItemList());
   		
	
		List<ItemVO> list = service.getListWithPaging(cri);
	    result.put("list",  list);
	    result.put("page",  page);
	    result.put("startpage",  pdto.getStartPage());
	    result.put("endpage",  pdto.getEndPage());


	    log.info("controller AjaxItemList...");
		
		return result;
	}
   	
   	//아이템 리스트를 비동기로 받아오기 (검색기능 추가)
   	//@RequestBody : JSON 으로 받아온 데이터를 java 객체로 사용
   	@RequestMapping(value = "/ajaxItemListFilter",
   			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
   	@ResponseBody
   	public Map<String, Object> getItemListFilter( 
   			@RequestBody ItemFilterVO itemFiltervo) throws Exception {
   		
   		Criteria cri = new Criteria(itemFiltervo.getPage());
   		
   		Map<String, Object> result = new HashMap<String, Object>();
   		PageDTO pdto = new PageDTO(cri, service.countApplyFilter(itemFiltervo));
   		itemFiltervo.setAmount(cri.getAmount());
   		
   		System.out.println("country : " + itemFiltervo.getCountry());
   		System.out.println("item_Category : " + itemFiltervo.getItem_Category());
   		System.out.println("people : " + itemFiltervo.getPeople());
   		System.out.println("startDay : " + itemFiltervo.getStartDay());
   		System.out.println("endDay : " + itemFiltervo.getEndDay());
   		System.out.println("page : " + itemFiltervo.getPage());
   		System.out.println("amount : " + itemFiltervo.getAmount());
   		
   		
   		List<ItemVO> list = service.getListwithFilter(itemFiltervo);
   		result.put("list",  list);
   		result.put("page",  itemFiltervo.getPage());
   		result.put("startpage",  pdto.getStartPage());
   		result.put("endpage",  pdto.getEndPage());
   		
   		log.info("controller ajaxItemListFilter...");
   		
   		return result;
   	}
	
	
	
	
	
	
	
	
	
	
	
}
