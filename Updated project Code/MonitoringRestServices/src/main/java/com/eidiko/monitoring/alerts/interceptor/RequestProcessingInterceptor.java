package com.eidiko.monitoring.alerts.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestProcessingInterceptor extends HandlerInterceptorAdapter{
	
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("pre-handle method");
		String userId = request.getHeader("userid");
		System.out.println("userId  "+userId);
		String requestUri = request.getRequestURI();
		if(requestUri.contains("rest/userAuthentication"))
		{
			return true;
		}
		if(userId!=null && !userId.equals(""))
		{
			return true;
		}
		else
		{
			response.setStatus(HttpStatus.FORBIDDEN.value());
			return false;
		}
		
	}

}
