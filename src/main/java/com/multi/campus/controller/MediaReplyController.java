package com.multi.campus.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.campus.service.MediaReplyService;
import com.multi.campus.vo.MediaReplyVO;

@Controller
@RequestMapping("/media_boardReply")
public class MediaReplyController {
	@Autowired
	MediaReplyService service;
	
	//��۵��
	@PostMapping("/write")
	@ResponseBody
	public String replyWrite(MediaReplyVO vo, HttpSession session) {
		vo.setUserid((String)session.getAttribute("logId"));
		
		int result = service.replyInsert(vo);
		
		return result+"";
	}
	//��۸��
	@GetMapping("/list")
	@ResponseBody
	public List<MediaReplyVO> replyList(int no) {
		List<MediaReplyVO> replyList = service.replySelect(no);
		return replyList;
	}
	//��ۼ���(DB)
	@PostMapping("/editOk")
	@ResponseBody
	public String replyEditOk(MediaReplyVO vo) {
		return service.replyUpdate(vo) + "";
	}
	//��ۻ���
	@GetMapping("/delete")
	@ResponseBody
	public String replyDelete(int replyno) {
		return service.replyDelete(replyno) + "";
	}
}
