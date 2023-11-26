package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.PagingVO;
import com.multi.campus.vo.TipVO;

public interface TipMapper {
	public int tipInsert(TipVO vo);
	public List<TipVO> tipPageList(PagingVO pVO); //page�� �ش��ϴ� ���ڵ� ����
	
	//�ش緹�ڵ弱��
	public TipVO tipSelect(int no);
	
	//��ȸ������
	public void hitCount(int no);
	
	//�Խ��� ����
	public int tipUpdate(TipVO vo);
	
	//�Խ��� ����
	public int tipDelete(int no);
}
