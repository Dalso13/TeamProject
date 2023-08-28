package org.worldfinder.controller;



import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.worldfinder.domain.Criteria;
import org.worldfinder.domain.ItemFilterConVO;
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
	
	
	@GetMapping("/itemList3")
	public String toList3(Model model) {
		log.info("controller itemList3...");
		
		model.addAttribute("itemInfo", null);
		
		return "manager/item/itemList3";
	}
	
	@PostMapping("/itemList3")
	public String toList3set(Model model, ItemFilterConVO icvo) {
		
		log.info("controller itemList3 set ...");
		model.addAttribute("itemInfo", icvo);
		
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println(icvo);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		
		return "manager/item/itemList3";
	}
	
	
	
   	//아이템 리스트를 비동기로 받아오기 (검색기능 + 날짜 검색기능 추가)
   	//@RequestBody : JSON 으로 받아온 데이터를 java 객체로 사용
   	@RequestMapping(value = "/ajaxItemListFilterDate",
   			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
   	@ResponseBody
   	public Map<String, Object> getItemListFilterDate( 
   			@RequestBody ItemFilterVO itemFiltervo) throws Exception {
   		
   		Criteria cri = new Criteria(itemFiltervo.getPage());
   		
   		Map<String, Object> result = new HashMap<String, Object>();
   		itemFiltervo.setCountTotal(service.getCountwithFilterDate(itemFiltervo));
   		PageDTO pdto = new PageDTO(cri, itemFiltervo.getCountTotal());
   		itemFiltervo.setAmount(cri.getAmount());   
   		                                                                     
   		List<ItemVO> list = service.getListwithFilterDate(itemFiltervo);     
   		result.put("list",  list);                                           
   		result.put("page",  itemFiltervo.getPage());                         
   		result.put("startpage",  pdto.getStartPage());                       
   		result.put("endpage",  pdto.getEndPage());                           
   		                                                                     
   		log.info("controller ajaxItemListFilter...");                        
   		
   		return result;
   	}
	
   
   	
   	@PostMapping(value = "/hotelGet")
   	public String hotelGet(ItemFilterConVO icvo, Model model) {
   		
   		model.addAttribute("itemInfo", icvo);
   		return "manager/item/hotelGet";
   	}
   	
   	@RequestMapping(value = "/ajaxHotelGet",
   			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
   	@ResponseBody
   	public ItemVO ajaxHotelGet(@RequestBody ItemFilterConVO icvo) throws ParseException {
   		System.out.println(icvo.getIdx());
   		
   		
   		System.out.println(service.getItemDetail(icvo));
   		
   		log.info("controller ajaxHotelGet...");
   		return service.getItemDetail(icvo);
   	}
   	
   	@PostMapping("/getNoDate")
   	public List<String> getNoDate(@RequestParam("hotelIdx") int hotelIdx) throws ParseException{
   		
   		log.info("controller getNoDate...");
   		System.out.println("controller getNoDate..." + hotelIdx);
   		List<String> result = service.getNoDate(hotelIdx);
   		
   		return result;	
   	}
   	
   	
   	
   	@PostMapping("/spotGet")
   	public String spotGetPage(Model model) {
   		log.info("controller spotGet...");
   		
   		return "manager/item/spotGet";
   	}
	
	
	
	
	
	
}
