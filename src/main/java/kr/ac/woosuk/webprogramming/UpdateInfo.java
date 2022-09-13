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

@WebServlet(urlPatterns = "/UserInfo")
public class UpdateInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateInfo() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("userid");

        UserDAO userDAO = new UserDAOImpl();
        UserDTO userDTO = userDAO.getUserByUserName(username);

        req.setAttribute("userInfo", userDTO);

        RequestDispatcher view = req.getRequestDispatcher("./userInformation.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("userid");

        UserDAO userDAO = new UserDAOImpl();

        String email = req.getParameter("email");
        String password = req.getParameter("pass1");
        int job = Integer.parseInt(req.getParameter("job"));
        String intro = req.getParameter("message");

        String deleteCheck = req.getParameter("delete");
        if(deleteCheck.equals("1")) {
            userDAO.deleteInformation(username);
            session.invalidate();
        } else {
            UserVO userVO = new UserVO(0, username, email, password, job, null, intro, null);
            userDAO.update(userVO);
        }
        resp.sendRedirect("./Index");
    }
}
