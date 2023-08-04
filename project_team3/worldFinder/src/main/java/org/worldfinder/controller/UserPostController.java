package org.worldfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.worldfinder.domain.UserPostVO;
import org.worldfinder.service.UserPostService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/userPost/*")
public class UserPostController {
	
	@Setter(onMethod_ = @Autowired)
	private UserPostService userService;
	
	@GetMapping("/main")
	public String list(Model model) {
		log.info("userPost controller list..");
		model.addAttribute("list", userService.getList());
		return "userPost/userPostList";
	}
	
	@GetMapping("/write")
	public String register() {
		log.info("userPost controller register(get)..");
		return "userPost/userPostWrite";
	}
	
	@PostMapping("/write")
	public String register(UserPostVO vo, RedirectAttributes rttr) {
		log.info("userPost controller register(post).." + vo);
		userService.postRegister(vo);
		
		rttr.addFlashAttribute("result", "ok");
		
		return "redirect:/userPost/main";
	}
	
	@GetMapping("/view")
	public String view(@RequestParam("up_idx") long up_idx, Model model) {
		log.info("userPost controller view.." + up_idx);
		model.addAttribute("list", userService.postView(up_idx));
		return "userPost/userPostView";
	}
}
