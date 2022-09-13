package kr.ac.woosuk.webprogramming;

import kr.ac.woosuk.webprogramming.models.DAO.UserDAO;
import kr.ac.woosuk.webprogramming.models.DAO.UserDAOImpl;
import kr.ac.woosuk.webprogramming.models.DTO.UserDTO;
import kr.ac.woosuk.webprogramming.models.VO.UserVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/SignUp")
public class SignUp extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SignUp() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("./join.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String userName = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("pass1");
        int job = Integer.parseInt(req.getParameter("job"));
        String gender = req.getParameter("priority");
        String message = req.getParameter("message");

        UserDTO user = new UserDTO();
        user.setUserid(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setJob(job);
        user.setGender(gender);
        user.setIntroduction(message);

        UserDAO userDAO = new UserDAOImpl();
        UserDTO userDTO = userDAO.getUserByUserName(userName);
        if(userDTO.getUserid() != null) {
            resp.sendRedirect("./SignUp?error=아이디중복");
        }else {
            userDAO.add(user);
            session.setAttribute("userid", userName);

            resp.sendRedirect("./Index");
        }
    }
}
