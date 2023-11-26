package com.multi.campus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.multi.campus.mapper.QnaReplyMapper;
import com.multi.campus.vo.QnaReplyVO;

@Service
public class QnaReplyServiceImpl implements QnaReplyService {
	@Inject
	QnaReplyMapper mapper;

	@Override
	public int replyInsert(QnaReplyVO vo) {
		return mapper.replyInsert(vo);
	}

	@Override
	public List<QnaReplyVO> replySelect(int no) {
		return mapper.replySelect(no);
	}

	@Override
	public int replyUpdate(QnaReplyVO vo) {
		return mapper.replyUpdate(vo);
	}

	@Override
	public int replyDelete(int replyno) {
		return mapper.replyDelete(replyno);
	}
}
