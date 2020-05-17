import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/RetrieveServlet")
public class RetrieveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String sid = request.getParameter("sid");
        String sname = request.getParameter("sname");
        String sbirthday = request.getParameter("sbirthday");
        String ssex = request.getParameter("ssex");

        DataSource ds = new ComboPooledDataSource();
        QueryRunner qr = new QueryRunner(ds);
        List<Object[]> result = null;
        String sql = "select * from students where sid=? or sname=? or sbirthday=? or ssex=?";
        Object[] obj = {sid, sname, sbirthday, ssex};
        try {
            result = qr.query(sql, new ArrayListHandler(), obj);
            request.setAttribute("retrieve", result);
            request.getRequestDispatcher("/retrieveresult.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
