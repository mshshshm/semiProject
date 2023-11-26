package com.multi.campus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// interceptor클래스는 HandlerInterceptorAdapter를 상속받아야 한다.
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	//컨트롤러가 호출되기 전에 실행하는 메소드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		//로그인 유무확인하여 로그인된 경우 호출된 매핑주소로 이동하면 되고, 로그인이 안된 경우 로그인 폼으로 이동하도록 실행을 변경한다.
		
		HttpSession session = request.getSession();
		
		String logStatus = (String)session.getAttribute("logStatus");
		
		if(logStatus == null || !logStatus.equals("Y")) {//로그인이 안된경우 -> 로그인 페이지로 백시킨다.
			response.sendRedirect(request.getContextPath()+"/users/login");
			return false;
		}
		// 반환형이 true면 원래 매핑으로 지속하고
		// 반환형이 false이면 새로운 주소로 이동한다
		
		return true;
	}
	//컨트롤러를 실행 후 view페이지 이동하기전에 실행되는 메소드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView mav) throws Exception {
		
	}
	// 컨트롤러가 실행 후 호출되는 메소드
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse ewsponse, Object handler,
			@Nullable Exception e) throws Exception {
		
	}
}
