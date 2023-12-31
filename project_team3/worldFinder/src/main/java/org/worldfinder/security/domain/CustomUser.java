package org.worldfinder.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import org.worldfinder.domain.UserVO;

@Getter
public class CustomUser extends User{
	private static final long serialVersionUID = 1L;
	
	private UserVO member;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	
	public CustomUser(UserVO vo) {
		super(vo.getU_writer(), vo.getU_pw() , vo.getAuthList().stream().map(auth ->
			new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		
		this.member = vo;
				
	}
}
