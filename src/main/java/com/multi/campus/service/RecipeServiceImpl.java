package com.multi.campus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.multi.campus.mapper.RecipeMapper;
import com.multi.campus.vo.RecipePagingVO;
import com.multi.campus.vo.RecipeVO;
import com.multi.campus.vo.RecipefileVO;

@Service
public class RecipeServiceImpl implements RecipeService {
	@Inject
	RecipeMapper mapper;

	@Override
	public List<RecipeVO> dataList() {
		return mapper.dataList();
	}

	@Override
	public int dataInsert(RecipeVO vo) {
		return mapper.dataInsert(vo);
	}

	@Override
	public int dataFileInsert(List<RecipefileVO> list) {
		return mapper.dataFileInsert(list);
	}

	@Override
	public void dataHitCount(int no) {
		mapper.dataHitCount(no);
	}

	@Override
	public RecipeVO dataSelect(int no) {
		return mapper.dataSelect(no);
	}

	@Override
	public List<RecipefileVO> getDataFile(int no) {
		return mapper.getDataFile(no);
	}

	@Override
	public int dataUpdate(RecipeVO vo) {
		return mapper.dataUpdate(vo);
	}

	@Override
	public int dataDelete(int no) {
		return mapper.dataDelete(no);
	}

	@Override
	public int dataRecordDelete(int no, String userid) {
		return mapper.dataRecordDelete(no, userid);
	}
	
	@Override
	public int recipeCount(String userid) {
		return mapper.recipeCount(userid);
	}
	@Override
	public List<RecipeVO> findAllMyRecipe(RecipePagingVO pVO) {
		return mapper.findAllMyRecipe(pVO);
	}
	
	@Override
	public int heartCount(String userid) {
		return mapper.heartCount(userid);
	}
	
	@Override
	public List<RecipeVO> findAllHeartRecipe(RecipePagingVO pVO) {
		return mapper.findAllHeartRecipe(pVO);
	}

}
