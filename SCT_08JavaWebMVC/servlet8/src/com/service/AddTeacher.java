package com.service;

import com.dao.DAOFactory;
import com.domain.Teacher;
import com.util.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addteacher")
public class AddTeacher extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int tid = MyUtil.toInt(request.getParameter("tid"));
        String tname = request.getParameter("tname");
        String password = request.getParameter("password");
        Teacher t = new Teacher(tid,tname,password);
        try {
            boolean flag = DAOFactory.getUserDaoInstance().insertTeacher(t);
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
