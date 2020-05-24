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

@WebServlet("/updatecourse")
public class UpdateCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int oldcid = MyUtil.toInt(request.getParameter("oldcid"));
        String Cid = request.getParameter("cid");
        int cid = MyUtil.toInt(Cid);
        String cname = request.getParameter("cname");
        int course_teacher = MyUtil.toInt(request.getParameter("course_teacher"));
        try {
            Course course = new Course(cid,cname,course_teacher);
            boolean flag = DAOFactory.getUserDaoInstance().updateCourse(course,oldcid);
            if(flag){
                request.setAttribute("result", "修改成功！");
                request.getRequestDispatcher("user.jsp").forward(request, response);
            }else {
                request.setAttribute("result", "修改失败！");
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
