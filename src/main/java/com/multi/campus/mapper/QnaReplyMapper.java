package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.QnaReplyVO;
import com.multi.campus.vo.QnaVO;

public interface QnaReplyMapper {
	public int replyInsert(QnaReplyVO vo); //���
	public List<QnaReplyVO> replySelect(int no); //��۸��
	public int replyUpdate(QnaReplyVO vo); //��ۼ���
	public int replyDelete(int replyno); //��ۻ���
}
