package kr.ac.woosuk.webprogramming;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import kr.ac.woosuk.webprogramming.scheduler.schedulerForDelete;

@WebServlet(urlPatterns = "/Index")
public class Index extends HttpServlet {
    protected static final long serialVersionUID = 1L;

    public Index() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        schedulerForDelete scheduler = new schedulerForDelete();
        HttpSession session = req.getSession();
        if (req.getParameter("notConfirm") != null){
            session.invalidate();
        }
        RequestDispatcher view = req.getRequestDispatcher("./index.jsp");
        view.forward(req, resp);
    }
}
