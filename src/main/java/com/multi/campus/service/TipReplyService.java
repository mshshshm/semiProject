package com.multi.campus.service;

import java.util.List;

import com.multi.campus.vo.TipReplyVO;

public interface TipReplyService {
	public int replyInsert(TipReplyVO vo);
	public List<TipReplyVO> replySelect(int no);
	public int replyUpdate(TipReplyVO vo);
	public int replyDelete(int replyno);
}
