package com.multi.campus.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.multi.campus.mapper.UsersMapper;
import com.multi.campus.vo.UsersVO;

import lombok.extern.slf4j.Slf4j;


@Service
public class UsersServiceImpl implements UsersService {
	@Inject
	UsersMapper mapper;

	@Override
	public int idCheck(String userid) {
		return mapper.idCheck(userid);
	}

	@Override
	public int createMember(UsersVO vo) {
		return mapper.createMember(vo);
	}
	
	@Override
	public UsersVO loginSelect(String userid, String userpwd) {
		return mapper.loginSelect(userid, userpwd);
	}
	
	@Override
	public UsersVO findid(String username, String email) {
		return mapper.findid(username, email);
	}

	@Override
	public UsersVO findpwd(String username, String userid, String email) {
		return mapper.findpwd(username, userid, email);
	}

	@Override
	public int passwordReset(UsersVO vo) {
		return mapper.passwordReset(vo);
	}

}
