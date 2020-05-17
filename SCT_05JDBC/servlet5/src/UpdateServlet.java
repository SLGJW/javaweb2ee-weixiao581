import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String sid = request.getParameter("sid");
        String sname = request.getParameter("sname");
        String sbirthday = request.getParameter("sbirthday");
        String ssex = request.getParameter("ssex");
        System.out.println(sid);
        System.out.println(sname);
        System.out.println(sbirthday);
        System.out.println(ssex);

        DataSource ds = new ComboPooledDataSource();
        QueryRunner qr = new QueryRunner(ds);
        String sql = "update students set sname=?,sbirthday=?,ssex=? where sid=?";
        Object[] obj = {sname,sbirthday,ssex,sid};
        try {
            int result = qr.update(sql, obj);
            request.setAttribute("update", result);
            request.getRequestDispatcher("/CRUD.jsp").forward(request, response);
        } catch (SQLException e) {
            int result = 0;
            request.setAttribute("update", result);
            request.getRequestDispatcher("/CRUD.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
