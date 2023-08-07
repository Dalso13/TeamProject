package org.worldfinder.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.worldfinder.domain.UserVO;
import org.worldfinder.service.UserService;

import lombok.extern.log4j.Log4j;


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
	public void joinGet() {
		log.info("로그인 페이지 진입");
		}
		
	// 로그인 
	@PostMapping(value="/login")
	public String login(UserVO vo, HttpServletRequest request) {
		log.info("로그인..." + vo);
		session = request.getSession();	// 세션 생성
		int login = userservice.loginCheck(vo);	// loginCheck : u_writer count
		request.setAttribute("login", login);
		
		if(login > 0) {	// count 했을때, 있으면 
			session.setAttribute("user", userservice.getUser(vo));	// getUser : vo 상세정보
		}
		return "user/login";
	}
	
	/*
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request) {
		
		log.info("로그아웃 진입....");
		
		session = request.getSession();
		
		session.invalidate();
	
		return "main/index";
	
	}
	*/
	// 아이디 찾기 페이지 이동
	@RequestMapping(value = "/idFindPage", method = RequestMethod.GET)
	public void goIdFind() {
		log.info("아이디찾기 페이지 진입");
		}
	
	// 아이디찾기
	@PostMapping("/idFind")
	public String idFind(HttpServletRequest request, Model model, UserVO vo) {
		log.info("아이디찾기...");
		
		UserVO user = userservice.findId(vo);
		
		if(user == null) {
			model.addAttribute("check", 1);
		}else {
			model.addAttribute("check", 0);
			model.addAttribute("name", user.getU_name());
		
		}
		
		return "/user/idFind";
		
	}
	
	// 아이디찾기 결과
	


	
	
	
	
}
	