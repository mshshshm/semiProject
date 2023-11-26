package com.multi.campus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.multi.campus.mapper.MediaReplyMapper;
import com.multi.campus.vo.MediaReplyVO;

@Service
public class MediaReplyServiceImpl implements MediaReplyService {
	@Inject
	MediaReplyMapper mapper;

	@Override
	public int replyInsert(MediaReplyVO vo) {
		return mapper.replyInsert(vo);
	}

	@Override
	public List<MediaReplyVO> replySelect(int no) {
		return mapper.replySelect(no);
	}

	@Override
	public int replyUpdate(MediaReplyVO vo) {
		return mapper.replyUpdate(vo);
	}

	@Override
	public int replyDelete(int replyno) {
		return mapper.replyDelete(replyno);
	}
}
