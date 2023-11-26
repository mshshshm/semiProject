package com.multi.campus.service;

import java.util.List;

import com.multi.campus.vo.QnaReplyVO;

public interface QnaReplyService {
	public int replyInsert(QnaReplyVO vo);
	public List<QnaReplyVO> replySelect(int no);
	public int replyUpdate(QnaReplyVO vo);
	public int replyDelete(int replyno);
}
