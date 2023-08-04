package org.worldfinder.mapper;

import java.util.List;

import org.worldfinder.domain.UserPostVO;

public interface UserPostMapper {
	public List<UserPostVO> getAllList();
	public void postInsert(UserPostVO vo);
	public UserPostVO postGet(long up_idx);
}
