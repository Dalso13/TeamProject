package org.worldfinder.service;

import java.util.List;

import org.worldfinder.domain.UserPostVO;

public interface UserPostService {
	public List<UserPostVO> getList();
	public void postRegister(UserPostVO vo);
	public UserPostVO postView(long up_idx);
}
