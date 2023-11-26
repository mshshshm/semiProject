package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.PagingVO;
import com.multi.campus.vo.TipVO;

public interface TipMapper {
	public int tipInsert(TipVO vo);
	public List<TipVO> tipPageList(PagingVO pVO); //page에 해당하는 레코드 선택
	
	//해당레코드선택
	public TipVO tipSelect(int no);
	
	//조회수증가
	public void hitCount(int no);
	
	//게시판 수정
	public int tipUpdate(TipVO vo);
	
	//게시판 삭제
	public int tipDelete(int no);
}
