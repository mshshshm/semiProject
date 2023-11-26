package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.MediaVO;
import com.multi.campus.vo.PagingVO;

public interface MediaMapper {
	public int mediaInsert(MediaVO vo);
	public List<MediaVO> mediaPageList(PagingVO pVO); //page�� �ش��ϴ� ���ڵ� ����
	
	//�ش緹�ڵ弱��
	public MediaVO mediaSelect(int no);
	
	//��ȸ������
	public void hitCount(int no);
	
	//�Խ��� ����
	public int mediaUpdate(MediaVO vo);
	
	//�Խ��� ����
	public int mediaDelete(int no);
}
