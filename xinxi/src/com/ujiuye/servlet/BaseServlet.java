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
		//����mark��ֵ
		String mark = req.getParameter("mark");
		
		/**
		 * ����testbaseservlet  ��ִ������service�������������У���baseservlet�̳�������
		 * this����ǰ����   testbaseserlet
		 */
		Class c = this.getClass();
		
		try {
			//��ȡ��Ա����
			//��һ�����������������ڶ��������ǲ����б���ֽ���
			Method m = c.getMethod(mark, HttpServletRequest.class,HttpServletResponse.class);
			//ִ�з���
			m.invoke(this, req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
