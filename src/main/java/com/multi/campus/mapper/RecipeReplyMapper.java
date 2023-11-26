package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.RecipeReplyVO;

public interface RecipeReplyMapper {
	public int replyInsert(RecipeReplyVO vo);//댓글
	public List<RecipeReplyVO> replySelect(int no);//댓글등록
	public int replyUpdate(RecipeReplyVO vo);//댓글수정(DB)
	public int replyDelete(int replyno);//댓글삭제
	
}
