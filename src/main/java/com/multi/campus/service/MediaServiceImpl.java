package com.multi.campus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.multi.campus.mapper.MediaMapper;
import com.multi.campus.vo.MediaVO;
import com.multi.campus.vo.PagingVO;

@Service
public class MediaServiceImpl implements MediaService {
    @Inject
    MediaMapper mapper;
    
    @Override
    public int mediaInsert(MediaVO vo) {
        return mapper.mediaInsert(vo);
    }
    
    @Override
    public List<MediaVO> mediaPageList(PagingVO pVO) {
    	return mapper.mediaPageList(pVO);
    }

	@Override
	public MediaVO mediaSelect(int no) {
		return mapper.mediaSelect(no);
	}

	@Override
	public void hitCount(int no) {
		mapper.hitCount(no);		
	}

	@Override
	public int mediaUpdate(MediaVO vo) {
		return mapper.mediaUpdate(vo);
	}

	@Override
	public int mediaDelete(int no) {
		return mapper.mediaDelete(no);
	}

}