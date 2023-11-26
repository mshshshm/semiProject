package com.multi.campus.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.campus.service.RecipeReplyService;
import com.multi.campus.vo.RecipeReplyVO;

@Controller
@RequestMapping("/recipeReply")
public class RecipeReplyController {
	@Autowired
	RecipeReplyService service;
	
	//댓글등록
	@PostMapping("/write")
	@ResponseBody
	public String replyWrite(RecipeReplyVO vo, HttpSession session) {
		vo.setUserid((String)session.getAttribute("logId"));
		
		int result = service.replyInsert(vo);
		
		return result+""; // int형을 문자열로 바꾸기 위해 +""
	}
	//댓글 목록
	@GetMapping("/list")
	@ResponseBody
	public  List<RecipeReplyVO> replyList(int no) {
		List<RecipeReplyVO> replyList = service.replySelect(no);
		return replyList;
	}
	@PostMapping("/editOk")
	@ResponseBody
	//댓글수정(DB)
	public String replyEditOk(RecipeReplyVO vo) {
		return service.replyUpdate(vo)+"";
	}
	//댓글삭제
	@GetMapping("/delete")
	@ResponseBody
	public String replyDelete(int replyno) {
		return service.replyDelete(replyno)+"";
	}
}
