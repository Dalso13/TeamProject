package org.worldfinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.worldfinder.domain.UserPostVO;
import org.worldfinder.mapper.UserPostMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class UserPostServiceImpl implements UserPostService {
	
	@Autowired
	private UserPostMapper mapper;
	
	@Override
	public List<UserPostVO> getList() {
		log.info("post list..");
		return mapper.getAllList();
	}
	
	@Override
	public void postRegister(UserPostVO vo) {	// 유저 게시글 등록
		log.info("post register.." + vo);
		mapper.postInsert(vo);
	}
	
	@Override
	public UserPostVO postView(long up_idx) {
		log.info("post view.." + up_idx);
		return mapper.postGet(up_idx);
	}
}