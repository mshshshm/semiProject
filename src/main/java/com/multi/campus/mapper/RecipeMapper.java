package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.RecipePagingVO;
import com.multi.campus.vo.RecipeVO;
import com.multi.campus.vo.RecipefileVO;

public interface RecipeMapper {
	public List<RecipeVO> dataList();
	public int dataInsert(RecipeVO vo);
	public int dataFileInsert(List<RecipefileVO> list);//�뙆�씪紐� insert
	public void dataHitCount(int no);//議고쉶�닔 利앷�
	public RecipeVO dataSelect(int no);//湲��궡�슜蹂닿린 湲��꽑�깮
	public List<RecipefileVO> getDataFile(int no);//泥⑤��뙆�씪�꽑�깮
	public int dataUpdate(RecipeVO vo);//湲��닔�젙
	public int dataDelete(int no);//泥⑤��뙆�씪�궘�젣
	public int dataRecordDelete(int no, String userid);//�썝湲��궘�젣
	
	
	public List<RecipeVO> selectMyRecipe(String userid);
	public List<RecipeVO> selectMyHeartLecipe(String userid);
	
	public int recipeCount(String userid);
	public List<RecipeVO> findAllMyRecipe(RecipePagingVO pVO);
	
	public int heartCount(String userid);
	public List<RecipeVO> findAllHeartRecipe(RecipePagingVO pVO);
}
