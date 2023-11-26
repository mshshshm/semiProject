package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.TipReplyVO;

public interface TipReplyMapper {
	public int replyInsert(TipReplyVO vo); //´ñ±Û
	public List<TipReplyVO> replySelect(int no); //´ñ±Û¸ñ·Ï
	public int replyUpdate(TipReplyVO vo); //´ñ±Û¼öÁ¤
	public int replyDelete(int replyno); //´ñ±Û»èÁ¦
}
