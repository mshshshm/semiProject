package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.MediaVO;
import com.multi.campus.vo.PagingVO;

public interface MediaMapper {
	public int mediaInsert(MediaVO vo);
	public List<MediaVO> mediaPageList(PagingVO pVO); //page에 해당하는 레코드 선택
	
	//해당레코드선택
	public MediaVO mediaSelect(int no);
	
	//조회수증가
	public void hitCount(int no);
	
	//게시판 수정
	public int mediaUpdate(MediaVO vo);
	
	//게시판 삭제
	public int mediaDelete(int no);
}
