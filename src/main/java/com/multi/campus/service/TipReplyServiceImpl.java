package com.multi.campus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.multi.campus.mapper.TipReplyMapper;
import com.multi.campus.vo.TipReplyVO;

@Service
public class TipReplyServiceImpl implements TipReplyService {
	@Inject
	TipReplyMapper mapper;

	@Override
	public int replyInsert(TipReplyVO vo) {
		return mapper.replyInsert(vo);
	}

	@Override
	public List<TipReplyVO> replySelect(int no) {
		return mapper.replySelect(no);
	}

	@Override
	public int replyUpdate(TipReplyVO vo) {
		return mapper.replyUpdate(vo);
	}

	@Override
	public int replyDelete(int replyno) {
		return mapper.replyDelete(replyno);
	}
}
