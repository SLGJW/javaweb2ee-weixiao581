package com.service;

import com.dao.DAOFactory;
import com.util.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addcourseservlet")
public class AddCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String course = request.getParameter("course");
        String student = request.getParameter("student");
        int cid = MyUtil.toInt(course);
        int sid = MyUtil.toInt(student);
        try {
            boolean result = DAOFactory.getStudentDaoInstance().chooseCourse(sid, cid);
            if(result){
                request.setAttribute("result", "添加成功！");
                request.getRequestDispatcher("student.jsp").forward(request, response);
            }else{
                request.setAttribute("result", "添加失败！");
                request.getRequestDispatcher("student.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
