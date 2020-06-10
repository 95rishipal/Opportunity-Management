package com.opportunitymanagement.accolite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.opportunitymanagement.accolite.dao.UserDaoImp;
import com.opportunitymanagment.accolite.models.User;

@Component
public class MyInterceptor implements HandlerInterceptor {
	private int count=0;
	@Autowired
	UserDaoImp userDaoImp;
	
   @Override
   public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	   System.out.println(request.getRequestURI());
	   String email = request.getHeader("Email");
	   String token = request.getHeader("Token");
	   String gid = request.getHeader("Gid");
	   User user = userDaoImp.findByEmail(email);
	   if(user != null) {
		   System.out.println("Email:"+email+" Gid: "+gid);
		   if(email.equals(user.getEmail())&&gid.equals(user.getGid())) return true;
		   else response.setStatus(401);
	   }
	   return true;
	   
   }
   
   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	   System.out.println("PostHandle Interceptor");	
   }
   
   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
	   System.out.println("After Completion");
   }
}

