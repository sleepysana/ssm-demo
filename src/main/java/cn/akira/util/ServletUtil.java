package cn.akira.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtil {
    public static void redirectOutOfIframe(String location, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window.open('" + location + "','_top')");
        out.println("</script>");
        out.println("</html>");
    }
}