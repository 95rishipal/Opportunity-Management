package com.oppo.accolite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.oppo.accolite.dao.UserDaoImp;
import com.oppo.accolite.models.User;

@Component
public class MyInterceptor implements HandlerInterceptor {
	@Autowired
	UserDaoImp userDaoImp;
	
   @Override
   public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	   String email = request.getHeader("Email");
	   String token = request.getHeader("Token");
	   String gid = request.getHeader("Gid");
	   User user = userDaoImp.findByEmail(email);
	   if(user != null) {
		   if(email.equals(user.getEmail())&&gid.equals(user.getGid())) return true;
		   else response.setStatus(401);
	   }
	   return true;
   }
   
}

