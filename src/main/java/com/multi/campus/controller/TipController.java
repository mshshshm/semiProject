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
	//�Խ���
	@GetMapping("/tip_board/list")
	public ModelAndView tipList(PagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		//DB����(page, �˻�)
		List<TipVO> list = service.tipPageList(pVO);
		
		mav.addObject("pVO", pVO);
		mav.addObject("list", list);
		mav.setViewName("tip_board/tip_community");
		return mav;
	}
	//�۾�����
	@GetMapping("/tip_board/write")
	public String tipWrite() {
		return "tip_board/boardWrite";
	}
	//�۵��(DB)
	@PostMapping("/tip_board/writeOk")
	public ModelAndView tipWriteOk(TipVO vo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// subject, content -> vo�� request
		// request -> session
		vo.setIp(request.getRemoteAddr());
		vo.setUserid((String)request.getSession().getAttribute("logId"));
		
		int result = service.tipInsert(vo);
		if (result > 0) { //�۵��
			mav.setViewName("redirect:/tip_board/list");
		} else { //����� ���ҽ�
			mav.addObject("msg", "���");
			mav.setViewName("tip_board/boardResult");
		}
		return mav;
		
	}
	//�۳��뺸��
	@GetMapping("/tip_board/view")
	public ModelAndView tipView(int no, PagingVO pVO) {
		
		service.hitCount(no); //��ȸ������
		TipVO vo = service.tipSelect(no); //���ڵ弱��
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.addObject("pVO", pVO);
		mav.setViewName("tip_board/boardView");
		
		return mav;
	}
	//�ۼ��� ��
	@GetMapping("/tip_board/edit")
	public ModelAndView tipEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.tipSelect(no));
		mav.setViewName("tip_board/boardEdit");
		return mav;
	}
	//�ۼ��� (DB)
	@PostMapping("/tip_board/editOk")
	public ModelAndView tipEditOk(TipVO vo) {
		int result = service.tipUpdate(vo);
		ModelAndView mav = new ModelAndView();
		if(result > 0) { //����
			mav.setViewName("redirect:/tip_board/view?no=" + vo.getBoardno());
		} else {
			mav.addObject("msg", "����");
			mav.setViewName("tip_board/boardResult");
		}
		return mav;
	}
	//�Խñ� ����
	@GetMapping("/tip_board/delete")
	public ModelAndView tipDelete(int no) {
		int result = service.tipDelete(no);
		ModelAndView mav = new ModelAndView();
		if (result > 0) { //����
			mav.setViewName("redirect:/tip_board/list"); //�۸������ ����
		} else { //��������
			mav.addObject("redirect:/tip_board/view?no"+no);
		}

		return mav;
	}
}
