import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/protect")
public class Servlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        Cookie[] cs = request.getCookies();
        if(cs!=null&&cs.length>0){
            for (Cookie c : cs) {
                String name = c.getName();
                String value = c.getValue();
                if(name.equals("wupeng")&&value.equals("123456")){
                    pw.write("<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <title>受保护页面</title>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "    <h1>登录受保护页面</h1>\n" +
                            "    <h3>欢迎wupeng访问本系统<a href=\"logoutservlet\">【注销】</a></h3>\n" +
                            "</body>\n" +
                            "</html>");
                    return;
                }
            }
        }
        pw.write("<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>未登录</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>您还未登录，无法访问登录受保护页面</h1>\n" +
                "    <h3>请先<a href=\"/stu38/login.html\">登录</a></h3>\n" +
                "</body>\n" +
                "</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
