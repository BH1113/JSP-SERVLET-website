package kr.ac.woosuk.webprogramming;

import kr.ac.woosuk.webprogramming.models.DAO.UserDAO;
import kr.ac.woosuk.webprogramming.models.DAO.UserDAOImpl;
import kr.ac.woosuk.webprogramming.models.DTO.UserDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/SignIn")
public class SignIn extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SignIn() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("./signIn.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String userName = req.getParameter("name");
        String password = req.getParameter("pass");

        UserDAO userDAO = new UserDAOImpl();
        UserDTO userDTO = userDAO.getUserByUserName(userName);

        if (userName.equals(userDTO.getUserid()) && password.equals(userDTO.getPassword())){
            session.setAttribute("userid", userName);
            if (userDTO.getEmail() == null){
                resp.sendRedirect("./UserInfo?error=resign");
            }else {
                resp.sendRedirect("./Index");
            }
        } else {
            resp.sendRedirect("/webtest/signIn.jsp?error=비밀번호 혹은 아이디 확인");
        }
    }
}
