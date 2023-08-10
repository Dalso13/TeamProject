package org.worldfinder.mapper;


import org.springframework.stereotype.Repository;
import org.worldfinder.domain.UserVO;

import java.util.List;

@Repository
public interface UserMapper {
	
	// 회원가입
	public int userJoin(UserVO uservo);

	// 아이디중복체크
	public int checkId(String u_writer);

	
	// 로그인체크
	public int loginCheck(UserVO vo);
	
	// 로그인 get
	public UserVO getUser(String u_writer);
	
	// 아이디찾기
	public List<String> findId(UserVO vo1);



	
	
	
	
	
}
