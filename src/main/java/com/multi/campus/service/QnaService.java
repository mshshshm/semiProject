package com.multi.campus.service;

import java.util.List;

import com.multi.campus.vo.PagingVO;
import com.multi.campus.vo.QnaVO;

public interface QnaService {
	public int qnaInsert(QnaVO vo);
	public List<QnaVO> qnaPageList(PagingVO pVO);
	public QnaVO qnaSelect(int no);
	public void hitCount(int no);
	public int qnaUpdate(QnaVO vo);
	public int qnaDelete(int no);
}
