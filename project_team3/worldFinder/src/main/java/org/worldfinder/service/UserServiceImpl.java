package org.worldfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.worldfinder.domain.UserVO;
import org.worldfinder.mapper.UserMapper;

import lombok.extern.java.Log;
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
	public String loginCheck(UserVO vo) throws Exception {
		return usermapper.loginCheck(vo);
	}
	
	
}
