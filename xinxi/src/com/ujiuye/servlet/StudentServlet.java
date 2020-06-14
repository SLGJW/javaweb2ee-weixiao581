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
		//接收mark
		String mark = req.getParameter("mark");
		if ("insertStudent".equals(mark)) {
			//调用添加的方法
			insertStudent(req,resp);
		}else if("queryAllStudent".equals(mark)) {
			//调用查询所有学生的方法
			queryAllStudent(req,resp);
		}
	}*/

	/**
	 * 查询所有学生信息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void queryAllStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//直接调用service层的方法
		List<Student> lists = ss.queryAllStudent();
		if (lists != null) {
			//将学生信息显示在main.jsp页面上
			//想携带作用域到jsp页面使用转发
			req.setAttribute("lists", lists);
			req.getRequestDispatcher("main.jsp").forward(req, resp);
		}else {
			System.out.println("查询失败");
		}
		
	}


	/**
	 * 添加的方法
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void insertStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收表单的参数
		String sname = req.getParameter("sname");
		String edu = req.getParameter("edu");
		String sclass = req.getParameter("sclass");
		
		//age 和sex 需要转成int
		String age = req.getParameter("age");
		String sex = req.getParameter("sex");
		//string数组转string
		String[] shobby = req.getParameterValues("shobby");
		String hobby = MyUtils.arrayToString(shobby);
		//封装到实体类
		Student s = new Student(sname, Integer.parseInt(age), Integer.parseInt(sex), sclass, hobby, edu);
		//调用studentservice类中方法
		int i = ss.insertStudent(s);
		if (i > 0) {
			req.setAttribute("msg", "添加成功");
			//查询所有用户信息
			req.getRequestDispatcher("studentServlet?mark=pagingQueryStudent").forward(req, resp);
		}else {
			
			System.out.println("添加失败");
		}
		
	}
	
	/**
	 * 删除学生信息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void deleteStudentBySid(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//接收前端传递的参数
		String sid = req.getParameter("sid");
		//调用service层的方法
		int i = ss.deleteStudentBySid(sid);
		if (i > 0) {
			//查询所有学生信息  通过转发
			req.getRequestDispatcher("studentServlet?mark=pagingQueryStudent").forward(req, resp);
		}else {
			System.out.println("删除失败");
		}
	}
	/**
	 * 修改的回显  通过sid查询该条学生信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryStudentBySid(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//接收参数sid
		String sid = req.getParameter("sid");
		Student s = ss.queryStudentBySid(Integer.parseInt(sid));
		if (s != null) {
			//把s放到request作用域中
			req.setAttribute("student", s);
			//转发跳转到修改页面 updateStudent.jsp
			req.getRequestDispatcher("updateStudent.jsp").forward(req, resp);
		}
	}
	
	/**
	 * 修改学生信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateStudent(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//接收前端的参数
		String sname = req.getParameter("sname");
		String edu = req.getParameter("edu");
		String sclass = req.getParameter("sclass");
		String sid = req.getParameter("sid");
		//age 和sex 需要转成int
		String age = req.getParameter("age");
		String sex = req.getParameter("sex");
		//string数组转string
		String[] shobby = req.getParameterValues("shobby");
		String hobby = MyUtils.arrayToString(shobby);
		//封装到实体类
		Student s = new Student(Integer.parseInt(sid),sname, Integer.parseInt(age), Integer.parseInt(sex), sclass, hobby, edu);
		//调用service层的方法
		int i = ss.updateStudent(s);
		if (i > 0) {
			//更新成功
			req.getRequestDispatcher("studentServlet?mark=pagingQueryStudent").forward(req, resp);
		} else {
			System.out.println("更新失败");
		}
	}
	
	/**
	 * 模糊查询
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void dimQueryStudent(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//接收前端的参数
		String sname = req.getParameter("sname");
		String startAge = req.getParameter("startAge");
		String endAge = req.getParameter("endAge");
		//将三个参数 姓名、起始年龄、结束年龄 封装到实体类
		DimBean db = new DimBean(sname, startAge, endAge);
		
		//接收当前页
		String cp = req.getParameter("currentPage");
		int currentPage = ((cp == null || "".equals(cp)) ? 1 : Integer.parseInt(cp));
		//每页显示的条数
		int pageSize = 3;
		//总条数     模糊查询的总条数
		int totalSize = ss.dimQueryCountStudent(db);
		//封装分页查询工具类
		
		PageTool pt = new PageTool(currentPage, pageSize, totalSize);
		//调用servcie层方法
		List<Student> lists = ss.dimQueryStudent(db,pt);
		System.out.println(lists);
		//跳转到main.jsp将数据进行显示，需要把lists集合放到作用域中
		req.setAttribute("lists", lists);
		//需要将三个参数信息放到作用域中在带到main.jsp
		req.setAttribute("db", db);
		req.setAttribute("pt", pt);
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}
	
	/**
	 * 分页查询  每页显示三条数据
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void pagingQueryStudent(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//当前页 从前端里面获取     每页显示几条  自定义  总条数 查询数据库
		String cp = req.getParameter("currentPage");
		
		//获取的参数什么时候是个null，什么是个""
		//url   ?currentPage=    就是""
		//url     没有currentPage参数   获取的就是null
		int currentPage = ((cp == null || "".equals(cp)) ? 1 : Integer.parseInt(cp));
		
		int pageSize = 3;
		int totalSize = ss.queryCountStudent();
		//创建pagetool对象
		PageTool pt = new PageTool(currentPage, pageSize, totalSize);
		//调用service层的方法
		List<Student> lists = ss.pagingQueryStudent(pt);
		//把lists放到request作用域中
		req.setAttribute("lists", lists);
		req.setAttribute("pt", pt);
		//转发到main.jsp
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}
	
	/**
	 * 批量删除
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void batchDeleteStudent(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//接收sids
		String sids = req.getParameter("sids");
		//调用service层方法
		int i = ss.batchDeleteStudent(sids);
		if (i > 0) {
			//批量删除成功
			req.getRequestDispatcher("studentServlet?mark=pagingQueryStudent").forward(req, resp);
		}
	}
	
	
	
	
}
