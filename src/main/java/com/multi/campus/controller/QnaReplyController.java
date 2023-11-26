package com.multi.campus.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.campus.service.QnaReplyService;
import com.multi.campus.vo.QnaReplyVO;

@Controller
@RequestMapping("/qna_boardReply")
public class QnaReplyController {
	@Autowired
	QnaReplyService service;
	
	//��۵��
	@PostMapping("/write")
	@ResponseBody
	public String replyWrite(QnaReplyVO vo, HttpSession session) {
		vo.setUserid((String)session.getAttribute("logId"));
		
		int result = service.replyInsert(vo);
		
		return result+"";
	}
	//��۸��
	@GetMapping("/list")
	@ResponseBody
	public List<QnaReplyVO> replyList(int no) {
		List<QnaReplyVO> replyList = service.replySelect(no);
		return replyList;
	}
	//��ۼ���(DB)
	@PostMapping("/editOk")
	@ResponseBody
	public String replyEditOk(QnaReplyVO vo) {
		return service.replyUpdate(vo) + "";
	}
	//��ۻ���
	@GetMapping("/delete")
	@ResponseBody
	public String replyDelete(int replyno) {
		return service.replyDelete(replyno) + "";
	}
}
