package com.service;

import com.dao.DAOFactory;
import com.util.MyUtil;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

@WebServlet("/cencelcourseservlet")
public class CencelCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String courseid = request.getParameter("course");
        String studentid = request.getParameter("student");
        int cid = parseInt(courseid);
        int sid = parseInt(studentid);
        boolean result = false;
        try {
            result = DAOFactory.getStudentDaoInstance().cancelCourse(sid,cid);
            if(result){
                request.setAttribute("result", "已取消该课程！");
                request.getRequestDispatcher("student.jsp").forward(request, response);
            }else{
                request.setAttribute("result", "取消选课失败");
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
