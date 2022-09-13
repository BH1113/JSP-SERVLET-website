package kr.ac.woosuk.webprogramming.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        HttpSession session = httpServletRequest.getSession();

        boolean login = false;
        if(session != null) {
            if (session.getAttribute("userid") != null){
                login = true;
            }
        }

        if (login) {
            filterChain.doFilter(req, resp);
        } else {
            httpServletResponse.sendRedirect("/webtest/SignIn");
        }
    }
}
