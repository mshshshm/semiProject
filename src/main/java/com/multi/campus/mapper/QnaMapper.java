package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.PagingVO;
import com.multi.campus.vo.QnaVO;

public interface QnaMapper {
	public int qnaInsert(QnaVO vo);
	public List<QnaVO> qnaPageList(PagingVO pVO); //page�� �ش��ϴ� ���ڵ� ����
	
	//�ش緹�ڵ弱��
	public QnaVO qnaSelect(int no);
	
	//��ȸ������
	public void hitCount(int no);
	
	//�Խ��� ����
	public int qnaUpdate(QnaVO vo);
	
	//�Խ��� ����
	public int qnaDelete(int no);
}
