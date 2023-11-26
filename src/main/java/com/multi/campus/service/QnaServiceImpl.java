package com.multi.campus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.multi.campus.mapper.QnaMapper;
import com.multi.campus.vo.PagingVO;
import com.multi.campus.vo.QnaVO;

@Service
public class QnaServiceImpl implements QnaService {
    @Inject
    QnaMapper mapper;
    
    @Override
    public int qnaInsert(QnaVO vo) {
        return mapper.qnaInsert(vo);
    }
    
    @Override
    public List<QnaVO> qnaPageList(PagingVO pVO) {
    	return mapper.qnaPageList(pVO);
    }

	@Override
	public QnaVO qnaSelect(int no) {
		return mapper.qnaSelect(no);
	}

	@Override
	public void hitCount(int no) {
		mapper.hitCount(no);		
	}

	@Override
	public int qnaUpdate(QnaVO vo) {
		return mapper.qnaUpdate(vo);
	}

	@Override
	public int qnaDelete(int no) {
		return mapper.qnaDelete(no);
	}

}