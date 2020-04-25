import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String sid = request.getParameter("sid");
        String sname = request.getParameter("sname");
        String birthday = request.getParameter("birthday");
        String sex = request.getParameter("sex");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write("<h3>" +
                "学号：" +sid+"<br>"+
                "姓名："+sname+"<br>"+
                "出生："+birthday+"<br>"+
                "性别："+sex+
                "</h3>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
