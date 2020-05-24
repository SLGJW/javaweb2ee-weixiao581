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

@WebServlet("/deleteteacher")
public class DeleteTeacher extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Tid = request.getParameter("tid");
        int tid = MyUtil.toInt(Tid);
        try {
            Teacher t = MyUtil.getTeacher(tid);
            boolean flag = DAOFactory.getUserDaoInstance().deleteTeacher(t);
            System.out.println(flag);
            if(flag==true){
                request.setAttribute("result", "删除成功！");
                request.getRequestDispatcher("user.jsp").forward(request, response);
            }else {
                request.setAttribute("result", "删除失败！");
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
