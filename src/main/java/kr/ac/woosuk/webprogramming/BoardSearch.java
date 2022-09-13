package kr.ac.woosuk.webprogramming;

import kr.ac.woosuk.webprogramming.models.DAO.BoardDAO;
import kr.ac.woosuk.webprogramming.models.DAO.BoardDAOImpl;
import kr.ac.woosuk.webprogramming.models.DAO.UserDAO;
import kr.ac.woosuk.webprogramming.models.DAO.UserDAOImpl;
import kr.ac.woosuk.webprogramming.models.VO.BoardVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/BoardSearch")
public class BoardSearch extends HttpServlet {
    protected static final long serialVersionUID = 1L;

    public BoardSearch() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NullPointerException {
        req.setCharacterEncoding("UTF-8");

        BoardDAO boardDAO = new BoardDAOImpl();

        try {
            String searchType = req.getParameter("boardSearchType");
            String searchWith = req.getParameter("boardSearch");
            List<BoardVO> boardVOList = new ArrayList<>();

            if (searchType.equals("1")) {
                boardVOList = boardDAO.getListByTitle(searchWith);
            } else if (searchType.equals("2")) {
                boardVOList = boardDAO.getListByWriter(searchWith);
            }

            req.setAttribute("boards", boardVOList);

            RequestDispatcher view = req.getRequestDispatcher("./board.jsp");
            view.forward(req, resp);

        }catch (NullPointerException e) {
            resp.sendRedirect("./Board?입력이 올바르지 않습니다. 제목과 검색항목을 확인하세요.");
        }
    }
}
