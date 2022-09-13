package kr.ac.woosuk.webprogramming;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/Logout")
public class Logout extends HttpServlet {
    protected static final long serialVersionUID = 1L;

    public Logout() {
        super ();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        if (session != null) {
            session.invalidate();
        }
        resp.sendRedirect("./Index");
    }
}
