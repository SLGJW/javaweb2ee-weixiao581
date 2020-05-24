package com.service;

import com.dao.DAOFactory;
import com.domain.Course;
import com.domain.Student;
import com.util.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addcourse")
public class AddCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int cid = MyUtil.toInt(request.getParameter("cid"));
        String cname = request.getParameter("cname");
        int course_teacher = MyUtil.toInt(request.getParameter("course_teacher"));
        Course c = new Course(cid,cname,course_teacher);
        try {
            boolean flag = DAOFactory.getUserDaoInstance().insertCourse(c);
            if(flag){
                request.setAttribute("result", "添加成功！");
                request.getRequestDispatcher("user.jsp").forward(request, response);
            }else {
                request.setAttribute("result", "添加失败！");
                request.getRequestDispatcher("user.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
