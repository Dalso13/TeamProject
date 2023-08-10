package org.worldfinder.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.worldfinder.domain.UserVO;
import org.worldfinder.service.UserService;

import lombok.extern.log4j.Log4j;

import java.util.List;


@Log4j
@Controller
@RequestMapping(value="/user")
public class UserController {
 
	@Autowired
	private UserService userservice;
	
	@Autowired
	private HttpSession session;
	
	// 회원가입 페이지 이동
	@RequestMapping(value="joinPage", method = RequestMethod.GET)
	public void loginGet() {
		log.info("회원가입 페이지 진입");
	}
	

	// 회원가입
	@PostMapping(value="/join", consumes = "application/json",
				produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public ResponseEntity<String> joinPost(@RequestBody UserVO vo){
		log.info("join 진입"); 
		log.info("uservo...." + vo);
		// 회원가입 실행
		int joinInsert= userservice.userJoin(vo);
		
		log.info("회원가입 성공");
		
		return joinInsert == 1 ?	// 회원등록이 되면
				new ResponseEntity<>("success", HttpStatus.OK) :			// 인서트되면 String으로 전달 
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	// 인서트안되면 에러
	}
	
	// 아이디 중복체크
	@PostMapping(value = "/checkId")
	@ResponseBody
	public String checkId(@RequestBody String u_writer) throws Exception {
			System.out.println("/user/checkId : post");
			System.out.println("param : " + u_writer );
			
			int checkNum = userservice.checkId(u_writer);
			
			if(checkNum == 1) {
				System.out.println("이미 존재하는 아이디입니다.");
				return "duplicated";
			}else {
				System.out.println("사용 가능한 아이디입니다.");
				return "available";
			}
	}
	
	
	// 로그인 페이지 이동
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public void joinGet(String error, String logout, Model model) {

		log.info("error : " + error);
		log.info("logout : " + logout);

		if (error != null) {
			model.addAttribute("error", "Login Error Check your Account");
		}
		if (logout != null) {
			model.addAttribute("logout", "Logout!!");
		}


		log.info("로그인 페이지 진입");
	}

	

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {


		return "/user/loginPage";

	
	}

	// 아이디 찾기 페이지 이동
	@RequestMapping(value = "/idFindPage", method = RequestMethod.GET)
	public void goIdFind() {
		log.info("아이디찾기 페이지 진입");
		}
	
	// 아이디찾기
	@PostMapping(value = "/idFind", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String idFind( UserVO vo) {
		log.info("아이디찾기... : " + vo.toString());

		List<String> user = userservice.findId(vo);
		
		if(user.isEmpty()) {
			return "fail";
		}else {
			return user.toString();
		}
	}
	
	// 아이디찾기 결과

	// 접근 제한 처리
	@GetMapping("/accessError")
	public String accessDenied(Authentication auth, Model model) {
		log.info("access Denied : " + auth );
		model.addAttribute("msg", "Access Denied");
		return "/main/accessError";
	}
	
	
	
	
}
	