package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.TipReplyVO;

public interface TipReplyMapper {
	public int replyInsert(TipReplyVO vo); //���
	public List<TipReplyVO> replySelect(int no); //��۸��
	public int replyUpdate(TipReplyVO vo); //��ۼ���
	public int replyDelete(int replyno); //��ۻ���
}
