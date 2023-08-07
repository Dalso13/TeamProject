package org.worldfinder.service;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.worldfinder.domain.UserVO;
import org.worldfinder.mapper.UserMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper usermapper;
	
	@Override
	public int userJoin(UserVO vo){
		log.info("userJoin...." + vo);
		
		return usermapper.userJoin(vo);
	}
	
	// 아이디 중복체크
	@Override
	public int checkId(String u_writer) throws Exception {
		return usermapper.checkId(u_writer);
	}

	// 로그인 체크
	@Override
	public int loginCheck(UserVO vo) {
		return usermapper.loginCheck(vo);
	}
	
	// 로그인 get
	@Override
	public UserVO getUser(UserVO vo) {
		return usermapper.getUser(vo);
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	// 아이디찾기
	@Override
	public UserVO findId(UserVO vo) {
		return usermapper.findId(vo);
	}
	
	

}
		
	

