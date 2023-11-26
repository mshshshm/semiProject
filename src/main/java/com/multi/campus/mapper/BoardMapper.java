package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.BoardVO;
import com.multi.campus.vo.PagingVO;

public interface BoardMapper {
	public int boardInsert(BoardVO vo);
	public List<BoardVO> boardPageList(PagingVO pVO); //page�� �ش��ϴ� ���ڵ� ����
	public int totalRecord(PagingVO pVO);//�ѷ��ڵ� ��
	
	
	public BoardVO boardSelect(int no);
	
	
	public void hitCount(int no);
	
	
	public int boardUpdate(BoardVO vo);
	
	
	public int boardDelete(int no);
}
