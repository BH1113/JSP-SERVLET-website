package kr.ac.woosuk.webprogramming;

import kr.ac.woosuk.webprogramming.models.DAO.AttacheDAO;
import kr.ac.woosuk.webprogramming.models.DAO.AttacheDAOImpl;
import kr.ac.woosuk.webprogramming.models.DAO.BoardDAO;
import kr.ac.woosuk.webprogramming.models.DAO.BoardDAOImpl;
import kr.ac.woosuk.webprogramming.models.VO.AttacheVO;
import kr.ac.woosuk.webprogramming.models.VO.BoardVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/BoardUpdate")
public class BoardUpdate extends HttpServlet {
    protected static final long serialVersionUID = 1L;

    public BoardUpdate() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");

        try {
            String boardId = req.getParameter("title");
            BoardDAO boardDAO = new BoardDAOImpl();
            BoardVO boardVO = boardDAO.getByBoardId(Integer.parseInt(boardId));

            req.setAttribute("selectedBoard", boardVO);

            AttacheDAO attacheDAO = new AttacheDAOImpl();
            AttacheVO attacheVO = attacheDAO.get(Integer.parseInt(boardId));

            if(attacheVO != null) {
                req.setAttribute("selectedFile", attacheVO);
            }

            RequestDispatcher view = req.getRequestDispatcher("./boardWrite.jsp");
            view.forward(req, resp);
        } catch (NullPointerException e) {
            resp.sendRedirect("./Board?error=수정할 게시판을 선택하세요.");
        }

    }
}
