package com.multi.campus.service;

import java.util.List;

import com.multi.campus.vo.BoardVO;
import com.multi.campus.vo.MediaVO;
import com.multi.campus.vo.PagingVO;

public interface MediaService {
	public int mediaInsert(MediaVO vo);
	public List<MediaVO> mediaPageList(PagingVO pVO);
	public MediaVO mediaSelect(int no);
	public void hitCount(int no);
	public int mediaUpdate(MediaVO vo);
	public int mediaDelete(int no);
}
