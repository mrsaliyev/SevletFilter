package kz.iitu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    System.out.println("JSESSIONID = " + cookie.getValue());
                    break;
                }
            }
        }

        HttpSession session = request.getSession(false);
        System.out.println("User = " + session.getAttribute("user"));
        if(session != null){
            session.invalidate();
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
        PrintWriter out= response.getWriter();
        out.println("<font color=green>You are lod out.</font>");
        rd.include(request, response);
    }

}
