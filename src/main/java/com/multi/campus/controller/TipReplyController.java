package com.multi.campus.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.campus.service.TipReplyService;
import com.multi.campus.vo.TipReplyVO;

@Controller
@RequestMapping("/tip_boardReply")
public class TipReplyController {
	@Autowired
	TipReplyService service;
	
	//´ñ±Ûµî·Ï
	@PostMapping("/write")
	@ResponseBody
	public String replyWrite(TipReplyVO vo, HttpSession session) {
		vo.setUserid((String)session.getAttribute("logId"));
		
		int result = service.replyInsert(vo);
		
		return result+"";
	}
	//´ñ±Û¸ñ·Ï
	@GetMapping("/list")
	@ResponseBody
	public List<TipReplyVO> replyList(int no) {
		List<TipReplyVO> replyList = service.replySelect(no);
		return replyList;
	}
	//´ñ±Û¼öÁ¤(DB)
	@PostMapping("/editOk")
	@ResponseBody
	public String replyEditOk(TipReplyVO vo) {
		return service.replyUpdate(vo) + "";
	}
	//´ñ±Û»èÁ¦
	@GetMapping("/delete")
	@ResponseBody
	public String replyDelete(int replyno) {
		return service.replyDelete(replyno) + "";
	}
}
