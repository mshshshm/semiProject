package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.QnaReplyVO;
import com.multi.campus.vo.QnaVO;

public interface QnaReplyMapper {
	public int replyInsert(QnaReplyVO vo); //´ñ±Û
	public List<QnaReplyVO> replySelect(int no); //´ñ±Û¸ñ·Ï
	public int replyUpdate(QnaReplyVO vo); //´ñ±Û¼öÁ¤
	public int replyDelete(int replyno); //´ñ±Û»èÁ¦
}
