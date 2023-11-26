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
	//�Խ���
	@GetMapping("/media_board/list")
	public ModelAndView mediaList(PagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		//DB����(page, �˻�)
		List<MediaVO> list = service.mediaPageList(pVO);
		
		mav.addObject("pVO", pVO);
		mav.addObject("list", list);
		mav.setViewName("media_board/media_community");
		return mav;
	}
	//�۾�����
	@GetMapping("/media_board/write")
	public String mediaWrite() {
		return "media_board/boardWrite";
	}
	//�۵��(DB)
	@PostMapping("/media_board/writeOk")
	public ModelAndView mediaWriteOk(MediaVO vo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// subject, content -> vo�� request
		// request -> session
		vo.setIp(request.getRemoteAddr());
		vo.setUserid((String)request.getSession().getAttribute("logId"));
		
		int result = service.mediaInsert(vo);
		if (result > 0) { //�۵��
			mav.setViewName("redirect:/media_board/list");
		} else { //����� ���ҽ�
			mav.addObject("msg", "���");
			mav.setViewName("media_board/boardResult");
		}
		return mav;
		
	}
	//�۳��뺸��
	@GetMapping("/media_board/view")
	public ModelAndView mediaView(int no, PagingVO pVO) {
		
		service.hitCount(no); //��ȸ������
		MediaVO vo = service.mediaSelect(no); //���ڵ弱��
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.addObject("pVO", pVO);
		mav.setViewName("media_board/boardView");
		
		return mav;
	}
	//�ۼ��� ��
	@GetMapping("/media_board/edit")
	public ModelAndView mediaEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.mediaSelect(no));
		mav.setViewName("media_board/boardEdit");
		return mav;
	}
	//�ۼ��� (DB)
	@PostMapping("/media_board/editOk")
	public ModelAndView mediaEditOk(MediaVO vo) {
		int result = service.mediaUpdate(vo);
		ModelAndView mav = new ModelAndView();
		if(result > 0) { //����
			mav.setViewName("redirect:/media_board/view?no=" + vo.getBoardno());
		} else {
			mav.addObject("msg", "����");
			mav.setViewName("media_board/boardResult");
		}
		return mav;
	}
	//�Խñ� ����
	@GetMapping("/media_board/delete")
	public ModelAndView mediaDelete(int no) {
		int result = service.mediaDelete(no);
		ModelAndView mav = new ModelAndView();
		if (result > 0) { //����
			mav.setViewName("redirect:/media_board/list"); //�۸������ ����
		} else { //��������
			mav.addObject("redirect:/media_board/view?no"+no);
		}

		return mav;
	}
}
