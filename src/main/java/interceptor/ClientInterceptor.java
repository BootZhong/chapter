package interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import po.User;
import po.UserBS;

/*后台请求方法拦截器*/
public class ClientInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response, Object handler) throws Exception {
       
		
		//获取请求链接
		HttpServletRequest request1=(HttpServletRequest)request;
		String url=request1.getRequestURI();
		//登录页面直接放行
		if(url.contains("login")||url.contains("check"))
		{
			return true;
		}
		//已经登录的放行
		if((UserBS)(request1.getSession().getAttribute("userBSMark"))!=null)
			return true;
		
		//否则转发到登录界面
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//获取一个输出流
		PrintWriter out=response.getWriter();
		//提示登录，并跳转到登录界面
		out.println("<script> alert('请先登录');window.location.href='login2.html' </script>");
		
		return false;
	}
	
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
}