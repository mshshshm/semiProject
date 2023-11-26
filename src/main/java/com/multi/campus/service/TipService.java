package com.multi.campus.service;

import java.util.List;

import com.multi.campus.vo.PagingVO;
import com.multi.campus.vo.TipVO;

public interface TipService {
	public int tipInsert(TipVO vo);
	public List<TipVO> tipPageList(PagingVO pVO);
	public TipVO tipSelect(int no);
	public void hitCount(int no);
	public int tipUpdate(TipVO vo);
	public int tipDelete(int no);
}
