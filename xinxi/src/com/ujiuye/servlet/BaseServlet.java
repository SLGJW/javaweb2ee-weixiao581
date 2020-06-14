package com.ujiuye.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//localhost:8080/testBaseServlet?mark=query
		//接收mark的值
		String mark = req.getParameter("mark");
		
		/**
		 * 请求testbaseservlet  会执行它的service方法，它有吗，有，从baseservlet继承下来的
		 * this代表当前对象   testbaseserlet
		 */
		Class c = this.getClass();
		
		try {
			//获取成员方法
			//第一个参数代表方法名，第二个参数是参数列表的字节码
			Method m = c.getMethod(mark, HttpServletRequest.class,HttpServletResponse.class);
			//执行方法
			m.invoke(this, req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
