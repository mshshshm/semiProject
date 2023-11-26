package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.RecipeVO;
import com.multi.campus.vo.UsersVO;

public interface MyPageMapper {
	public UsersVO accountSelect(String userid);
	public int mypageUpdate(UsersVO vo);
	public int editpwdOk(UsersVO vo);
	public int accountDelete(String userid);
	
}
