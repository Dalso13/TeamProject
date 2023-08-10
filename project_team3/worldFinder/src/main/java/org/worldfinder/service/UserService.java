package org.worldfinder.service;


import javax.servlet.http.HttpSession;

import org.worldfinder.domain.UserVO;

import java.util.List;

public interface UserService {
	
	// 회원가입
	public int userJoin(UserVO vo);

	// 아이디중복확인
	public int checkId(String u_writer) throws Exception;	

	// 로그인체크
	public int loginCheck(UserVO vo);

	
	// 로그아웃
	public void logout(HttpSession session);

	// 아이디찾기
	public List<String> findId(UserVO vo);
	
	
}
