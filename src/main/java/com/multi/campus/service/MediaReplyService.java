package com.multi.campus.service;

import java.util.List;

import com.multi.campus.vo.MediaReplyVO;

public interface MediaReplyService {
	public int replyInsert(MediaReplyVO vo);
	public List<MediaReplyVO> replySelect(int no);
	public int replyUpdate(MediaReplyVO vo);
	public int replyDelete(int replyno);
}
