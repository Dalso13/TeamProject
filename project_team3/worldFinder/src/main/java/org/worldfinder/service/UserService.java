package org.worldfinder.service;

import org.worldfinder.domain.UserVO;

public interface UserService {
	
	// 회원가입
	public int userJoin(UserVO vo);


	// 아이디중복확인
	public int checkId(String u_writer) throws Exception;	
	
	// 로그인 체크
	public String loginCheck(UserVO vo) throws Exception;

	
}
