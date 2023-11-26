package com.multi.campus.service;

import java.util.List;

import com.multi.campus.vo.RecipeVO;
import com.multi.campus.vo.UsersVO;

public interface MyPageService {
	public UsersVO accountSelect(String userid);
	public int mypageUpdate(UsersVO vo);
	public int editpwdOk(UsersVO vo);
	public int accountDelete(String userid);
	
	public List<RecipeVO> selectMyRecipe(String userid);
	public List<RecipeVO> selectMyHeartLecipe(String userid);
}
