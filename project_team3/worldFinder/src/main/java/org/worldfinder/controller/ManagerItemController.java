package org.worldfinder.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.worldfinder.domain.Criteria;
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
	
//   @RequestMapping("/ajaxItemList")
//   @ResponseBody
//   public Map<String, Object> getBoardReplyList(Model model, 
//		   @RequestParam(value = "page", defaultValue = "1", required = false) int page,
//		   Criteria cri) throws Exception {
//      Map<String, Object> result = new HashMap<String, Object>();
//      int listcount = service.countItemList();
//
//      // 총 페이지수
//      int maxpage = (int) Math.ceil(listcount / (double)cri.getAmount());
//      // 시작 페이지수
//      int startpage = ((page - 1) / cri.getAmount()) * cri.getAmount() + 1;
//
//      // 마지막 페이지수
//      int endpage = startpage + cri.getAmount() - 1;
//
//      if (endpage > maxpage)
//         endpage = maxpage;
//      
//      board_reply.setLimit(limit);
//      board_reply.setPage(page);
//      board_reply.setStartpage(startpage);
//      board_reply.setEndpage(endpage);
//      board_reply.setMaxpage(maxpage);
//      System.out.println(page);
//      System.out.println(startpage);
//      System.out.println(endpage);
//      
//      List<BoardReply> boardList = boardreplyserviceimpl.getBoardReplyList(board_reply);
//      result.put("boardList",  boardList);
//      result.put("page",  page);
//      result.put("startpage",  startpage);
//      result.put("endpage",  endpage);
//      return result;
//   }
	
	
	
	
	
	
	
	
	
	
	
}
