package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.MediaReplyVO;

public interface MediaReplyMapper {
	public int replyInsert(MediaReplyVO vo); //���
	public List<MediaReplyVO> replySelect(int no); //��۸��
	public int replyUpdate(MediaReplyVO vo); //��ۼ���
	public int replyDelete(int replyno); //��ۻ���
}
