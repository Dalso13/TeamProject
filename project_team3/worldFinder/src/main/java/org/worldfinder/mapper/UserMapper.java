package org.worldfinder.mapper;


import org.springframework.stereotype.Repository;
import org.worldfinder.domain.UserVO;

@Repository
public interface UserMapper {
	
	// 회원가입
	public int userJoin(UserVO uservo);
	
	// 로그인
	public String loginCheck(UserVO vo) throws Exception;

	// 아이디중복체크
	public int checkId(String u_writer);
	
	
	
	
	
}
