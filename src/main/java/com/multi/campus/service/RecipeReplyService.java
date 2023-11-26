package com.multi.campus.service;

import java.util.List;

import com.multi.campus.vo.RecipeReplyVO;

public interface RecipeReplyService {
	public int replyInsert(RecipeReplyVO vo);
	public List<RecipeReplyVO> replySelect(int no);
	public int replyUpdate(RecipeReplyVO vo);
	public int replyDelete(int replyno);
}
