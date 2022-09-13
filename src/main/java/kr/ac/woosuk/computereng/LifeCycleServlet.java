package kr.ac.woosuk.computereng;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/LifeCycleServlet")
public class LifeCycleServlet extends HttpServlet {

    public LifeCycleServlet() {
        super();
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy() 메서드 호출");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init() 메서드 호출");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at : ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
