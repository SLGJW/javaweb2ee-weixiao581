package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.service.UserService;

/**
 * һ�ű��Ӧ��һ��servlet
 * @author Administrator
 *
 */
@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {
	private UserService userService = new UserService();

	public void loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ�û���������
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		int i = userService.loginUser(username,password);
		
		if (i > 0) {
			//��ѯ�ĵ� ��¼�ɹ�
			//��¼�ɹ�֮�󣬽��û���Ϣ�ŵ�session��������
			req.getSession().setAttribute("user", new User(username,password));
			
			//���cookie ʵ���˺ź���������
			//����cookie
			
			Cookie c1 = new Cookie("username", username);
			Cookie c2 = new Cookie("password", password);
			//����cookie�Ĵ��ʱ��  ��λ����
			c1.setMaxAge(60 * 60);
			c2.setMaxAge(60 * 60);
			//��cookie�����ͻ���
			resp.addCookie(c1);
			resp.addCookie(c2);
			
			//��ת��ѧ����Ϣҳ��
			//req.getRequestDispatcher("studentServlet?mark=queryAllStudent").forward(req, resp);
			//��¼�ɹ��߷�ҳ��ѯ��Ĭ����ʾ��һҳ
			req.getRequestDispatcher("studentServlet?mark=pagingQueryStudent").forward(req, resp);
		}else {
			//��ѯ���� ��¼ʧ��
			//��ת����¼ҳ�� ������ʾ��Ϣ
			req.setAttribute("msg", "�û����������벻��ȷ");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
	
	public void quitUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//�Ƴ�session��������û�
		req.getSession().removeAttribute("user");
		//��ת����¼ҳ��
		resp.sendRedirect("login.jsp");
	}
}
