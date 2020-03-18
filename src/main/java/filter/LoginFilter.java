package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import po.User;
import po.UserBS;

public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		

		//获取请求链接
		HttpServletRequest request1=(HttpServletRequest)request;
		String url=request1.getRequestURI();
		//登录注册页面和主界面直接放行
		if(url.contains("login")||url.contains("login2")||url.contains("main")||url.contains("signUp"))
		{
			chain.doFilter(request, response);
			//不return还会继续往下执行
			return ;
		}	
		
		//如果访问的是后台主界面且未登录后台账号，跳转到后台的登录界面
		if(url.contains("backStage")) {
			if((UserBS)(request1.getSession().getAttribute("userBSMark"))==null) {
				//获取一个输出流
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter out=response.getWriter();
				out.println("<script> alert('请先登录');window.location.href='login2.html' </script>");
				out.close();
				return;
			}
			else {
				chain.doFilter(request, response);
				return;
			}
		}
		//已经登录的放行
		if((User)(request1.getSession().getAttribute("userMark"))!=null
		  ||(UserBS)(request1.getSession().getAttribute("userBSMark"))!=null)
		{   chain.doFilter(request, response);
			return ;
		}	
		//获取一个输出流
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out=response.getWriter();
		//否则转发到登录界面	
		out.println("<script> alert('请先登录');window.location.href='login.html' </script>");
		out.close();
	}

	
	

}
