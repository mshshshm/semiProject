package com.multi.campus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.campus.service.TipService;
import com.multi.campus.vo.PagingVO;
import com.multi.campus.vo.TipVO;

@Controller
public class TipController {
	@Autowired
	TipService service;
	//게시판
	@GetMapping("/tip_board/list")
	public ModelAndView tipList(PagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		//DB선택(page, 검색)
		List<TipVO> list = service.tipPageList(pVO);
		
		mav.addObject("pVO", pVO);
		mav.addObject("list", list);
		mav.setViewName("tip_board/tip_community");
		return mav;
	}
	//글쓰기폼
	@GetMapping("/tip_board/write")
	public String tipWrite() {
		return "tip_board/boardWrite";
	}
	//글등록(DB)
	@PostMapping("/tip_board/writeOk")
	public ModelAndView tipWriteOk(TipVO vo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// subject, content -> vo에 request
		// request -> session
		vo.setIp(request.getRemoteAddr());
		vo.setUserid((String)request.getSession().getAttribute("logId"));
		
		int result = service.tipInsert(vo);
		if (result > 0) { //글등록
			mav.setViewName("redirect:/tip_board/list");
		} else { //등록을 못할시
			mav.addObject("msg", "등록");
			mav.setViewName("tip_board/boardResult");
		}
		return mav;
		
	}
	//글내용보기
	@GetMapping("/tip_board/view")
	public ModelAndView tipView(int no, PagingVO pVO) {
		
		service.hitCount(no); //조회수증가
		TipVO vo = service.tipSelect(no); //레코드선택
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.addObject("pVO", pVO);
		mav.setViewName("tip_board/boardView");
		
		return mav;
	}
	//글수정 폼
	@GetMapping("/tip_board/edit")
	public ModelAndView tipEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.tipSelect(no));
		mav.setViewName("tip_board/boardEdit");
		return mav;
	}
	//글수정 (DB)
	@PostMapping("/tip_board/editOk")
	public ModelAndView tipEditOk(TipVO vo) {
		int result = service.tipUpdate(vo);
		ModelAndView mav = new ModelAndView();
		if(result > 0) { //수정
			mav.setViewName("redirect:/tip_board/view?no=" + vo.getBoardno());
		} else {
			mav.addObject("msg", "수정");
			mav.setViewName("tip_board/boardResult");
		}
		return mav;
	}
	//게시글 삭제
	@GetMapping("/tip_board/delete")
	public ModelAndView tipDelete(int no) {
		int result = service.tipDelete(no);
		ModelAndView mav = new ModelAndView();
		if (result > 0) { //삭제
			mav.setViewName("redirect:/tip_board/list"); //글목록으로 매핑
		} else { //삭제실패
			mav.addObject("redirect:/tip_board/view?no"+no);
		}

		return mav;
	}
}
