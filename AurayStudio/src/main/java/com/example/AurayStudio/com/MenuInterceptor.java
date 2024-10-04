package com.example.AurayStudio.com;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.AurayStudio.dto.BoardDto;
import com.example.AurayStudio.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MenuInterceptor implements HandlerInterceptor {
	@Autowired
	private BoardService service;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		// 컨트롤로 메서드 호출 직전 실행
		System.out.println("call preHandle()");
		return true;
	}
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception{
		List<BoardDto> menu = service.getBoardMenu();
		mv.addObject("menu", menu);
	}
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception{
		// 뷰 런더링 후 실행
		System.out.println("call afterCompletion()");
	}
}
