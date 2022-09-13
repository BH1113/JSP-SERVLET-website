package kr.ac.woosuk.computereng;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OutputStream os = response.getOutputStream();
        PrintStream out = new PrintStream(os, true);
        out.println("This is get");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OutputStream os = response.getOutputStream();
        PrintStream out = new PrintStream(os, true);
        out.println("This is post");
    }
}
