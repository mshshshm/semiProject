package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.MediaReplyVO;

public interface MediaReplyMapper {
	public int replyInsert(MediaReplyVO vo); //´ñ±Û
	public List<MediaReplyVO> replySelect(int no); //´ñ±Û¸ñ·Ï
	public int replyUpdate(MediaReplyVO vo); //´ñ±Û¼öÁ¤
	public int replyDelete(int replyno); //´ñ±Û»èÁ¦
}
