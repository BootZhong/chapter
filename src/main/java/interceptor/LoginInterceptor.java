package interceptor;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import po.User;


/*前台拦截器*/
public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response, Object handler) throws Exception {
       
		
		//获取请求链接
		HttpServletRequest request1=(HttpServletRequest)request;
		String url=request1.getRequestURI();
		//登录注册页面直接放行
		if(url.contains("login")||url.contains("signUp")||url.contains("check")||
				url.contains("getStatus")||url.contains("getProducts")||url.contains("register"))
		{
			return true;
		}
		//已经登录的放行
		if((User)(request1.getSession().getAttribute("userMark"))!=null)
			return true;
		
		//否则转发到登录界面
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//获取一个输出流
		PrintWriter out=response.getWriter();
		//提示登录，并跳转到登录界面
		out.println("<script> alert('请先登录');window.location.href='login.html' </script>");
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
