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
		

		//��ȡ��������
		HttpServletRequest request1=(HttpServletRequest)request;
		String url=request1.getRequestURI();
		//��¼ע��ҳ���������ֱ�ӷ���
		if(url.contains("login")||url.contains("login2")||url.contains("main")||url.contains("signUp"))
		{
			chain.doFilter(request, response);
			//��return�����������ִ��
			return ;
		}	
		
		//������ʵ��Ǻ�̨��������δ��¼��̨�˺ţ���ת����̨�ĵ�¼����
		if(url.contains("backStage")) {
			if((UserBS)(request1.getSession().getAttribute("userBSMark"))==null) {
				//��ȡһ�������
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter out=response.getWriter();
				out.println("<script> alert('���ȵ�¼');window.location.href='login2.html' </script>");
				out.close();
				return;
			}
			else {
				chain.doFilter(request, response);
				return;
			}
		}
		//�Ѿ���¼�ķ���
		if((User)(request1.getSession().getAttribute("userMark"))!=null
		  ||(UserBS)(request1.getSession().getAttribute("userBSMark"))!=null)
		{   chain.doFilter(request, response);
			return ;
		}	
		//��ȡһ�������
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out=response.getWriter();
		//����ת������¼����	
		out.println("<script> alert('���ȵ�¼');window.location.href='login.html' </script>");
		out.close();
	}

	
	

}
