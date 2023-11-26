package com.multi.campus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.campus.mapper.MyPageMapper;
import com.multi.campus.mapper.RecipeMapper;
import com.multi.campus.vo.RecipeVO;
import com.multi.campus.vo.UsersVO;

@Service
public class MyPageServiceImpl implements MyPageService {
	@Autowired MyPageMapper mapper;
	@Autowired RecipeMapper recipeMapper;
	
	
	@Override
	public UsersVO accountSelect(String userid) {
		return mapper.accountSelect(userid);
	}
	
	@Override
	public int mypageUpdate(UsersVO vo) {
		return mapper.mypageUpdate(vo);
	}

	@Override
	public int editpwdOk(UsersVO vo) {
		return mapper.editpwdOk(vo);
	}

	@Override
	public int accountDelete(String userid) {
		return mapper.accountDelete(userid);
	}
	
	@Override
	public List<RecipeVO> selectMyRecipe(String userid) {
		return recipeMapper.selectMyRecipe(userid);
	}
	
	@Override
	public List<RecipeVO> selectMyHeartLecipe(String userid) {
		return recipeMapper.selectMyHeartLecipe(userid);
	}
	
}
