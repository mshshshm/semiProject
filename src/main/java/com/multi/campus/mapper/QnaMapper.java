package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.PagingVO;
import com.multi.campus.vo.QnaVO;

public interface QnaMapper {
	public int qnaInsert(QnaVO vo);
	public List<QnaVO> qnaPageList(PagingVO pVO); //page에 해당하는 레코드 선택
	
	//해당레코드선택
	public QnaVO qnaSelect(int no);
	
	//조회수증가
	public void hitCount(int no);
	
	//게시판 수정
	public int qnaUpdate(QnaVO vo);
	
	//게시판 삭제
	public int qnaDelete(int no);
}
