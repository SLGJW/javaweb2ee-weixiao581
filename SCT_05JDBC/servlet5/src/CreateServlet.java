import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.lang.model.element.VariableElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String sid = request.getParameter("sid");
        String sname = request.getParameter("sname");
        String sbirthday = request.getParameter("sbirthday");
        String ssex = request.getParameter("ssex");

        DataSource ds = new ComboPooledDataSource();
        QueryRunner qr = new QueryRunner(ds);
        String sql = "insert into students values(?,?,?,?)";
        Object[] obj = {sid,sname,sbirthday,ssex};
        try {
            int result = qr.update(sql, obj);
            request.setAttribute("create", result);
            request.getRequestDispatcher("/CRUD.jsp").forward(request, response);
        } catch (SQLException e) {
            int result = 0;
            request.setAttribute("create", result);
            request.getRequestDispatcher("/CRUD.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
