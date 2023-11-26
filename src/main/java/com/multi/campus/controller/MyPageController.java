package com.multi.campus.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import com.multi.campus.service.MyPageService;
import com.multi.campus.service.RecipeService;
import com.multi.campus.vo.RecipePagingVO;
import com.multi.campus.vo.RecipeVO;
import com.multi.campus.vo.UsersVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	@Autowired
	MyPageService service;
	@Autowired
	RecipeService recipeService;
	
	@GetMapping("/mypageEdit")
	public ModelAndView mypageEdit(
			@SessionAttribute(name = "loginUser", required = false) UsersVO loginUser,
			HttpServletResponse response) throws IOException {

		ModelAndView mav = new ModelAndView();
		if (loginUser == null) {
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			out.println("<script> alert('로그인이 필요헙니다.');");
			out.println("history.go(-1); </script>"); 
			out.close();
			mav.setViewName("redirect:/");
			return mav;
		}
		
		UsersVO vo = service.accountSelect(loginUser.getUserid());
		

		mav.addObject("vo", vo);
		mav.setViewName("mypage/mypageEdit");
		return mav;
	}
	
	@PostMapping("/mypageEditOk")
	public ModelAndView mypageEditOk(UsersVO vo) {
		ModelAndView mav = new ModelAndView();
		System.out.println(vo.toString());
		
		int result = service.mypageUpdate(vo);
		
		if(result>0) {
			mav.setViewName("redirect:/");
		}else {
			mav.setViewName("mypage/mypageEditResult");
		}
		
		return mav;
	}
	
	@GetMapping("/editpwd")
	public String editpwd() {
		return "/mypage/editPwd";
	}
	
	@PostMapping("/editpwdOk")
	public ModelAndView editpwdOk(UsersVO vo) {
		int result = service.editpwdOk(vo);
		System.out.println(vo.toString());
		ModelAndView mav = new ModelAndView();
		if(result > 0) {
			mav.setViewName("mypage/editpwdOk");			
		}else {
			mav.setViewName("redirect:editpwd");
		}
		
		return mav;
	}
	
	@GetMapping("/delete")
	public ModelAndView accountDelete(String userid, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		int result = service.accountDelete(userid);
		if(result > 0) {
			session.invalidate();
			mav.setViewName("redirect:/");
		}else {
			mav.setViewName("mypage/mypageEdit");
		}
		return mav;
	}
	
	
	
	
	
	@GetMapping("/")
	public String myPage(
			@SessionAttribute(name = "loginUser", required = false) UsersVO loginUser,
			HttpServletResponse response, Model model) throws IOException{
		
	
		if (loginUser == null) {
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			out.println("<script> alert('로그인이 필요헙니다.');");
			out.println("history.go(-1); </script>"); 
			out.close();
			return "home";
		}
		
		List<RecipeVO> myRecipeList = service.selectMyRecipe("test");
		List<RecipeVO> myHeartRecipeList = service.selectMyHeartLecipe("test");

        model.addAttribute("loginUser", loginUser);
		model.addAttribute("recipeList", myRecipeList);
		model.addAttribute("heartList", myHeartRecipeList);
		return "/mypage/mypage";
	}
	
	@GetMapping("/recipe")
    public String myRecipe(
    		@SessionAttribute(name = "loginUser", required = false) UsersVO loginUser,
    		RecipePagingVO pVO,
    		HttpServletResponse response, Model model) throws IOException {
		
		if (loginUser == null) {
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			out.println("<script> alert('로그인이 필요헙니다.');");
			out.println("history.go(-1); </script>"); 
			out.close();
			return "home";
		}

        pVO.setTotalRecord(recipeService.recipeCount(loginUser.getUserid()));
        pVO.setUserid(loginUser.getUserid());

        List<RecipeVO> recipeList = recipeService.findAllMyRecipe(pVO);

        model.addAttribute("pVO", pVO);
        model.addAttribute("recipeList", recipeList);
        return "/mypage/myRecipe";
    }
	
	@GetMapping("/heart")
    public String myHeart(
    		@SessionAttribute(name = "loginUser", required = false) UsersVO loginUser,
    		RecipePagingVO pVO,
    		HttpServletResponse response, Model model) throws IOException {
		
		if (loginUser == null) {
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			out.println("<script> alert('로그인이 필요헙니다.');");
			out.println("history.go(-1); </script>"); 
			out.close();
			return "home";
		}
		
        pVO.setTotalRecord(recipeService.heartCount(loginUser.getUserid()));
        pVO.setUserid(loginUser.getUserid());

        List<RecipeVO> recipeList = recipeService.findAllHeartRecipe(pVO);

        model.addAttribute("pVO", pVO);
        model.addAttribute("recipeList", recipeList);
        return "/mypage/heart";
    }
	
	
	
}
