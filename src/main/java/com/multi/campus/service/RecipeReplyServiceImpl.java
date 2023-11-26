package com.multi.campus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.multi.campus.mapper.RecipeReplyMapper;
import com.multi.campus.vo.RecipeReplyVO;

@Service
public class RecipeReplyServiceImpl implements RecipeReplyService {
	@Inject
	RecipeReplyMapper mapper;

	@Override
	public int replyInsert(RecipeReplyVO vo) {
		return mapper.replyInsert(vo);
	}

	@Override
	public List<RecipeReplyVO> replySelect(int no) {
		return mapper.replySelect(no);
	}

	@Override
	public int replyUpdate(RecipeReplyVO vo) {
		return mapper.replyUpdate(vo);
	}

	@Override
	public int replyDelete(int replyno) {
		return mapper.replyDelete(replyno);
	}
}
