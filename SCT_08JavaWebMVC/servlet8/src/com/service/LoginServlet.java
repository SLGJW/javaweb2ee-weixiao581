package com.service;

import com.dao.DAOFactory;
import com.domain.Student;
import com.domain.Teacher;
import com.domain.User;
import com.util.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String info = null;     //收集错误信息
        String identity = request.getParameter("identity");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(identity==null || "".equals(identity)){
            info = "请选择您的身份！";
        }else if(username==null || "".equals(username)){
            info = "登录名不能为空！";
        }else if(password==null || "".equals(password)){
            info = "口令不能为空！";
        }
        if(info==null){
            if("student".equals(identity)){
                Student student = new Student();
                student.setSid(MyUtil.toInt(username));
                student.setPassword(password);
                try {
                    Student s = DAOFactory.getStudentDaoInstance().doLogin(student);
                    if (s!=null) {
                        request.setAttribute("info", s.getSid());
                        request.getRequestDispatcher("/student.jsp").forward(request, response);
                    }else {
                        info = "登录名或口令错误！";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("info", info);
            }

            if("teacher".equals(identity)){
                int tid = MyUtil.toInt(username);
                Teacher teacher = new Teacher(tid,password);
                try {
                    Teacher t = DAOFactory.getTeacherDaoInstance().doLogin(teacher);
                    if (t!=null) {
                        request.setAttribute("info", tid);
                        request.getRequestDispatcher("/teacher.jsp").forward(request, response);
                    }else {
                        info = "登录名或口令错误！";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("info", info);
            }
            if("user".equals(identity)){
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                try {
                    User u = DAOFactory.getUserDaoInstance().doLogin(user);
                    if (u!=null) {
                        request.setAttribute("info", u);
                        request.getRequestDispatcher("/user.jsp").forward(request, response);
                    }else {
                        info = "登录名或口令错误！";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("info", info);
            }
        }
        request.setAttribute("info", info);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
