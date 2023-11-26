package com.multi.campus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.campus.service.MediaService;
import com.multi.campus.vo.MediaVO;
import com.multi.campus.vo.PagingVO;

@Controller
public class MediaController {
	@Autowired
	MediaService service;
	//게시판
	@GetMapping("/media_board/list")
	public ModelAndView mediaList(PagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		//DB선택(page, 검색)
		List<MediaVO> list = service.mediaPageList(pVO);
		
		mav.addObject("pVO", pVO);
		mav.addObject("list", list);
		mav.setViewName("media_board/media_community");
		return mav;
	}
	//글쓰기폼
	@GetMapping("/media_board/write")
	public String mediaWrite() {
		return "media_board/boardWrite";
	}
	//글등록(DB)
	@PostMapping("/media_board/writeOk")
	public ModelAndView mediaWriteOk(MediaVO vo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// subject, content -> vo에 request
		// request -> session
		vo.setIp(request.getRemoteAddr());
		vo.setUserid((String)request.getSession().getAttribute("logId"));
		
		int result = service.mediaInsert(vo);
		if (result > 0) { //글등록
			mav.setViewName("redirect:/media_board/list");
		} else { //등록을 못할시
			mav.addObject("msg", "등록");
			mav.setViewName("media_board/boardResult");
		}
		return mav;
		
	}
	//글내용보기
	@GetMapping("/media_board/view")
	public ModelAndView mediaView(int no, PagingVO pVO) {
		
		service.hitCount(no); //조회수증가
		MediaVO vo = service.mediaSelect(no); //레코드선택
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.addObject("pVO", pVO);
		mav.setViewName("media_board/boardView");
		
		return mav;
	}
	//글수정 폼
	@GetMapping("/media_board/edit")
	public ModelAndView mediaEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.mediaSelect(no));
		mav.setViewName("media_board/boardEdit");
		return mav;
	}
	//글수정 (DB)
	@PostMapping("/media_board/editOk")
	public ModelAndView mediaEditOk(MediaVO vo) {
		int result = service.mediaUpdate(vo);
		ModelAndView mav = new ModelAndView();
		if(result > 0) { //수정
			mav.setViewName("redirect:/media_board/view?no=" + vo.getBoardno());
		} else {
			mav.addObject("msg", "수정");
			mav.setViewName("media_board/boardResult");
		}
		return mav;
	}
	//게시글 삭제
	@GetMapping("/media_board/delete")
	public ModelAndView mediaDelete(int no) {
		int result = service.mediaDelete(no);
		ModelAndView mav = new ModelAndView();
		if (result > 0) { //삭제
			mav.setViewName("redirect:/media_board/list"); //글목록으로 매핑
		} else { //삭제실패
			mav.addObject("redirect:/media_board/view?no"+no);
		}

		return mav;
	}
}
