package com.ujiuye.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ujiuye.bean.DimBean;
import com.ujiuye.bean.PageTool;
import com.ujiuye.bean.Student;
import com.ujiuye.service.StudentService;
import com.ujiuye.utils.MyUtils;

@WebServlet("/studentServlet")
public class StudentServlet extends BaseServlet {
	private StudentService ss = new StudentService();
	
	
	/*@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//����mark
		String mark = req.getParameter("mark");
		if ("insertStudent".equals(mark)) {
			//������ӵķ���
			insertStudent(req,resp);
		}else if("queryAllStudent".equals(mark)) {
			//���ò�ѯ����ѧ���ķ���
			queryAllStudent(req,resp);
		}
	}*/

	/**
	 * ��ѯ����ѧ����Ϣ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void queryAllStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ֱ�ӵ���service��ķ���
		List<Student> lists = ss.queryAllStudent();
		if (lists != null) {
			//��ѧ����Ϣ��ʾ��main.jspҳ����
			//��Я��������jspҳ��ʹ��ת��
			req.setAttribute("lists", lists);
			req.getRequestDispatcher("main.jsp").forward(req, resp);
		}else {
			System.out.println("��ѯʧ��");
		}
		
	}


	/**
	 * ��ӵķ���
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void insertStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//���ձ��Ĳ���
		String sname = req.getParameter("sname");
		String edu = req.getParameter("edu");
		String sclass = req.getParameter("sclass");
		
		//age ��sex ��Ҫת��int
		String age = req.getParameter("age");
		String sex = req.getParameter("sex");
		//string����תstring
		String[] shobby = req.getParameterValues("shobby");
		String hobby = MyUtils.arrayToString(shobby);
		//��װ��ʵ����
		Student s = new Student(sname, Integer.parseInt(age), Integer.parseInt(sex), sclass, hobby, edu);
		//����studentservice���з���
		int i = ss.insertStudent(s);
		if (i > 0) {
			req.setAttribute("msg", "��ӳɹ�");
			//��ѯ�����û���Ϣ
			req.getRequestDispatcher("studentServlet?mark=pagingQueryStudent").forward(req, resp);
		}else {
			
			System.out.println("���ʧ��");
		}
		
	}
	
	/**
	 * ɾ��ѧ����Ϣ
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void deleteStudentBySid(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//����ǰ�˴��ݵĲ���
		String sid = req.getParameter("sid");
		//����service��ķ���
		int i = ss.deleteStudentBySid(sid);
		if (i > 0) {
			//��ѯ����ѧ����Ϣ  ͨ��ת��
			req.getRequestDispatcher("studentServlet?mark=pagingQueryStudent").forward(req, resp);
		}else {
			System.out.println("ɾ��ʧ��");
		}
	}
	/**
	 * �޸ĵĻ���  ͨ��sid��ѯ����ѧ����Ϣ
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryStudentBySid(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//���ղ���sid
		String sid = req.getParameter("sid");
		Student s = ss.queryStudentBySid(Integer.parseInt(sid));
		if (s != null) {
			//��s�ŵ�request��������
			req.setAttribute("student", s);
			//ת����ת���޸�ҳ�� updateStudent.jsp
			req.getRequestDispatcher("updateStudent.jsp").forward(req, resp);
		}
	}
	
	/**
	 * �޸�ѧ����Ϣ
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateStudent(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//����ǰ�˵Ĳ���
		String sname = req.getParameter("sname");
		String edu = req.getParameter("edu");
		String sclass = req.getParameter("sclass");
		String sid = req.getParameter("sid");
		//age ��sex ��Ҫת��int
		String age = req.getParameter("age");
		String sex = req.getParameter("sex");
		//string����תstring
		String[] shobby = req.getParameterValues("shobby");
		String hobby = MyUtils.arrayToString(shobby);
		//��װ��ʵ����
		Student s = new Student(Integer.parseInt(sid),sname, Integer.parseInt(age), Integer.parseInt(sex), sclass, hobby, edu);
		//����service��ķ���
		int i = ss.updateStudent(s);
		if (i > 0) {
			//���³ɹ�
			req.getRequestDispatcher("studentServlet?mark=pagingQueryStudent").forward(req, resp);
		} else {
			System.out.println("����ʧ��");
		}
	}
	
	/**
	 * ģ����ѯ
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void dimQueryStudent(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//����ǰ�˵Ĳ���
		String sname = req.getParameter("sname");
		String startAge = req.getParameter("startAge");
		String endAge = req.getParameter("endAge");
		//���������� ��������ʼ���䡢�������� ��װ��ʵ����
		DimBean db = new DimBean(sname, startAge, endAge);
		
		//���յ�ǰҳ
		String cp = req.getParameter("currentPage");
		int currentPage = ((cp == null || "".equals(cp)) ? 1 : Integer.parseInt(cp));
		//ÿҳ��ʾ������
		int pageSize = 3;
		//������     ģ����ѯ��������
		int totalSize = ss.dimQueryCountStudent(db);
		//��װ��ҳ��ѯ������
		
		PageTool pt = new PageTool(currentPage, pageSize, totalSize);
		//����servcie�㷽��
		List<Student> lists = ss.dimQueryStudent(db,pt);
		System.out.println(lists);
		//��ת��main.jsp�����ݽ�����ʾ����Ҫ��lists���Ϸŵ���������
		req.setAttribute("lists", lists);
		//��Ҫ������������Ϣ�ŵ����������ڴ���main.jsp
		req.setAttribute("db", db);
		req.setAttribute("pt", pt);
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}
	
	/**
	 * ��ҳ��ѯ  ÿҳ��ʾ��������
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void pagingQueryStudent(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//��ǰҳ ��ǰ�������ȡ     ÿҳ��ʾ����  �Զ���  ������ ��ѯ���ݿ�
		String cp = req.getParameter("currentPage");
		
		//��ȡ�Ĳ���ʲôʱ���Ǹ�null��ʲô�Ǹ�""
		//url   ?currentPage=    ����""
		//url     û��currentPage����   ��ȡ�ľ���null
		int currentPage = ((cp == null || "".equals(cp)) ? 1 : Integer.parseInt(cp));
		
		int pageSize = 3;
		int totalSize = ss.queryCountStudent();
		//����pagetool����
		PageTool pt = new PageTool(currentPage, pageSize, totalSize);
		//����service��ķ���
		List<Student> lists = ss.pagingQueryStudent(pt);
		//��lists�ŵ�request��������
		req.setAttribute("lists", lists);
		req.setAttribute("pt", pt);
		//ת����main.jsp
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}
	
	/**
	 * ����ɾ��
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void batchDeleteStudent(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//����sids
		String sids = req.getParameter("sids");
		//����service�㷽��
		int i = ss.batchDeleteStudent(sids);
		if (i > 0) {
			//����ɾ���ɹ�
			req.getRequestDispatcher("studentServlet?mark=pagingQueryStudent").forward(req, resp);
		}
	}
	
	
	
	
}
