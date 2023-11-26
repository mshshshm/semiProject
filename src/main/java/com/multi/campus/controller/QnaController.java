package com.multi.campus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.campus.service.QnaService;
import com.multi.campus.vo.PagingVO;
import com.multi.campus.vo.QnaVO;

@Controller
public class QnaController {
	@Autowired
	QnaService service;
	
	@GetMapping("/qna_board/list")
	public ModelAndView qnaList(PagingVO pVO) {
		ModelAndView mav = new ModelAndView();
		//DB����(page, �˻�)
		List<QnaVO> list = service.qnaPageList(pVO);
		
		mav.addObject("pVO", pVO);
		mav.addObject("list", list);
		mav.setViewName("qna_board/qna_community");
		return mav;
	}
	//�۾�����
	@GetMapping("/qna_board/write")
	public String qnaWrite() {
		return "qna_board/boardWrite";
	}
	//�۵��(DB)
	@PostMapping("/qna_board/writeOk")
	public ModelAndView qnaWriteOk(QnaVO vo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// subject, content -> vo�� request
		// request -> session
		vo.setIp(request.getRemoteAddr());
		vo.setUserid((String)request.getSession().getAttribute("logId"));
		
		int result = service.qnaInsert(vo);
		if (result > 0) { //�۵��
			mav.setViewName("redirect:/qna_board/list");
		} else { //����� ���ҽ�
			mav.addObject("msg", "���");
			mav.setViewName("qna_board/boardResult");
		}
		return mav;
		
	}
	//�۳��뺸��
	@GetMapping("/qna_board/view")
	public ModelAndView qnaView(int no, PagingVO pVO) {
		
		service.hitCount(no); //��ȸ������
		QnaVO vo = service.qnaSelect(no); //���ڵ弱��
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.addObject("pVO", pVO);
		mav.setViewName("qna_board/boardView");
		
		return mav;
	}
	//�ۼ��� ��
	@GetMapping("/qna_board/edit")
	public ModelAndView qnaEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.qnaSelect(no));
		mav.setViewName("qna_board/boardEdit");
		return mav;
	}
	//�ۼ��� (DB)
	@PostMapping("/qna_board/editOk")
	public ModelAndView qnaEditOk(QnaVO vo) {
		int result = service.qnaUpdate(vo);
		ModelAndView mav = new ModelAndView();
		if(result > 0) { //����
			mav.setViewName("redirect:/qna_board/view?no=" + vo.getBoardno());
		} else {
			mav.addObject("msg", "����");
			mav.setViewName("qna_board/boardResult");
		}
		return mav;
	}
	//�Խñ� ����
	@GetMapping("/qna_board/delete")
	public ModelAndView qnaDelete(int no) {
		int result = service.qnaDelete(no);
		ModelAndView mav = new ModelAndView();
		if (result > 0) { //����
			mav.setViewName("redirect:/qna_board/list"); //�۸������ ����
		} else { //��������
			mav.addObject("redirect:/qna_board/view?no"+no);
		}

		return mav;
	}
}
