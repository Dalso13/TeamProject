package org.worldfinder.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
				new ResponseEntity<>("success", HttpStatus.OK) :// 인서트되면 String으로 전달 
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	// 인서트안되면 에러
		

	}
	
	// 로그인 페이지 이동
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public void joinGet() {
		log.info("로그인 페이지 진입");
	}
	// 로그인 체크
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("u_writer") String u_writer
						,@RequestParam("u_pw") String u_pw) throws Exception {
		
		
		
	} 
	*/
	
	// 아이디 중복체크
	@PostMapping(value= "/checkId")
	@ResponseBody
	public String checkId(@RequestBody String u_writer) throws Exception {
			System.out.println("/user/checkId : post");
			System.out.println("param : " + u_writer );
			
			int checkNum = userservice.checkId(u_writer);
			
			if(checkNum == 1) {
				System.out.println("아이디가 중복되었다.");
				return "duplicated";
			}else {
				System.out.println("아이디 사용 가능");
				return "available";
			}
	}
	
	/*
	@RequestMapping(value= "/checkId", method = RequestMethod.POST)
	@ResponseBody
	public String userIdCheckPOST(String u_writer) throws Exception{
		log.info("userIdCheck() 진입");
		
		int result = userservice.checkId(u_writer);
		
		log.info("결과 값 =" + result);
		
		if(result != 0) {
			System.out.println(u_writer);
			return "fail"; 
			
		}else {
			return "success";
		}
		
	}
	*/
	
	
	
	
}
	