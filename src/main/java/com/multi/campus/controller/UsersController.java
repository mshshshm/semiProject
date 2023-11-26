package com.multi.campus.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.multi.campus.service.UsersService;
import com.multi.campus.vo.UsersVO;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/users")
public class UsersController {
	@Autowired
	UsersService service;
	
	@GetMapping("/create_membership")
	public String usersForm() {
		return "users/create_membership";
	}
	
	@GetMapping("/idCheck")
	public ModelAndView idCheck(String userid) {
		
		System.out.println("userid->"+userid);
		
		int result = service.idCheck(userid);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.addObject("userid", userid);
		mav.setViewName("users/idCheck");
		
		return mav;
	}
	

	@RequestMapping(value="/createOk", method=RequestMethod.POST)
	public ModelAndView usersFormOk(UsersVO vo) {
		ModelAndView mav = new ModelAndView();
		System.out.println(vo.toString());
		try {
			int result = service.createMember(vo);
			
			if(result>0){//?šŒ?›ê°??ž…?„±ê³?->ë¡œê·¸?¸ ?¼
				mav.setViewName("redirect:login");			
			}else{//?šŒ?›ê°??ž…?‹¤?Œ¨->?šŒ?›ê°??ž…?¼-> jsp -> history.back()
				mav.setViewName("users/createResult");
			}
		}catch(Exception e) {
			//e.printStackTrace();
			mav.setViewName("users/createResult");
		}		
		return mav;
	}
		
	@RequestMapping("/login")
	public String login() {
		return "users/login";
	}
	
	@PostMapping("/loginOk")
	public ModelAndView loginOk(String userid, String userpwd, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UsersVO logVO = service.loginSelect(userid, userpwd);;
		if(logVO != null) {
			session.setAttribute("loginUser", logVO);
			session.setAttribute("logId", logVO.getUserid());
			session.setAttribute("usernickname", logVO.getUsernickname());
			session.setAttribute("logStatus", "Y");
			mav.setViewName("redirect:/");
		}else {
			mav.setViewName("redirect:login");
		}
		return mav;
	}
	
	@RequestMapping("/findId")
	public String findId() {
		return "users/find_id";
	}
	
	@GetMapping("/findidOk")
	public ModelAndView findidResult(String username, String email) {
		UsersVO vo = service.findid(username, email);
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo", vo);
		
		if(vo != null) {
			mav.setViewName("users/findidOk");
		}else {
			mav.setViewName("redirect:findId");
		}
		return mav;
	}
	
	@RequestMapping("/findPw")
	public String findPw() {
		return "users/find_pw";
	}
	
	@GetMapping("/findpwResult")
	public ModelAndView findpwResult(String username, String userid, String email) {
		UsersVO vo = service.findpwd(username, userid, email);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo", vo);
		if(vo != null) {
			mav.setViewName("users/resetPwd");
		}else {
			mav.setViewName("redirect:findPw");
		}
		return mav;
	}
	
	@RequestMapping("/resetpwd")
	public String resetPwd() {
		return "users/resetPwd";
	}

	@PostMapping("/resetpwdOk")
	public ModelAndView resetPwdOk(UsersVO vo) {
		int result = service.passwordReset(vo);
		System.out.println(vo.toString());
		ModelAndView mav = new ModelAndView();
		if(result > 0) {
			mav.setViewName("users/resetOk");			
		}else {
			mav.setViewName("redirect:resetpwd");
		}
		
		return mav;
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
}
