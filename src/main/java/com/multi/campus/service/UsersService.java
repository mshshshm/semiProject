package com.multi.campus.service;

import com.multi.campus.vo.UsersVO;

public interface UsersService {
	public int idCheck(String userid);
	public int createMember(UsersVO vo);
	public UsersVO loginSelect(String userid, String userpwd);
	public UsersVO findid(String username, String email);
	public UsersVO findpwd(String username, String userid, String email);
	public int passwordReset(UsersVO vo);
}
