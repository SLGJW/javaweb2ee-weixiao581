package com.service;

import com.dao.DAOFactory;
import com.domain.Student;
import com.util.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updatestudent")
public class UpdateStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String Sid = request.getParameter("sid");
        int sid = MyUtil.toInt(Sid);
        String sname = request.getParameter("sname");
        String password = request.getParameter("password");
        try {
            Student student = new Student(sid,sname,password);
            boolean flag = DAOFactory.getUserDaoInstance().updateStudent(student);
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
