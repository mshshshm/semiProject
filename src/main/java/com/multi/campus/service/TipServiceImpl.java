package com.multi.campus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.multi.campus.mapper.TipMapper;
import com.multi.campus.vo.PagingVO;
import com.multi.campus.vo.TipVO;

@Service
public class TipServiceImpl implements TipService {
    @Inject
    TipMapper mapper;
    
    @Override
    public int tipInsert(TipVO vo) {
        return mapper.tipInsert(vo);
    }
    
    @Override
    public List<TipVO> tipPageList(PagingVO pVO) {
    	return mapper.tipPageList(pVO);
    }

	@Override
	public TipVO tipSelect(int no) {
		return mapper.tipSelect(no);
	}

	@Override
	public void hitCount(int no) {
		mapper.hitCount(no);		
	}

	@Override
	public int tipUpdate(TipVO vo) {
		return mapper.tipUpdate(vo);
	}

	@Override
	public int tipDelete(int no) {
		return mapper.tipDelete(no);
	}

}