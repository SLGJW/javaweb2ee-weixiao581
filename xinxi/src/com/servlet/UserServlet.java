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
 * 一张表对应着一个servlet
 * @author Administrator
 *
 */
@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {
	private UserService userService = new UserService();

	public void loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取用户名和密码
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		int i = userService.loginUser(username,password);
		
		if (i > 0) {
			//查询的到 登录成功
			//登录成功之后，将用户信息放到session作用域中
			req.getSession().setAttribute("user", new User(username,password));
			
			//添加cookie 实现账号和密码的填充
			//创建cookie
			
			Cookie c1 = new Cookie("username", username);
			Cookie c2 = new Cookie("password", password);
			//设置cookie的存活时间  单位是秒
			c1.setMaxAge(60 * 60);
			c2.setMaxAge(60 * 60);
			//将cookie带到客户端
			resp.addCookie(c1);
			resp.addCookie(c2);
			
			//跳转到学生信息页面
			//req.getRequestDispatcher("studentServlet?mark=queryAllStudent").forward(req, resp);
			//登录成功走分页查询，默认显示第一页
			req.getRequestDispatcher("studentServlet?mark=pagingQueryStudent").forward(req, resp);
		}else {
			//查询不到 登录失败
			//跳转到登录页面 给予提示信息
			req.setAttribute("msg", "用户名或者密码不正确");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
	
	public void quitUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//移出session作用域的用户
		req.getSession().removeAttribute("user");
		//跳转到登录页面
		resp.sendRedirect("login.jsp");
	}
}
