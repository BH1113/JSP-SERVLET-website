package kr.ac.woosuk.webprogramming;

import kr.ac.woosuk.webprogramming.models.DAO.*;
import kr.ac.woosuk.webprogramming.models.DTO.AttacheDTO;
import kr.ac.woosuk.webprogramming.models.DTO.BoardDTO;
import kr.ac.woosuk.webprogramming.models.VO.BoardVO;
import kr.ac.woosuk.webprogramming.models.fileTransfer.FileTransferDAO;
import kr.ac.woosuk.webprogramming.models.fileTransfer.FileTransferDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = {"/Board", "/Board/write"})
@MultipartConfig
public class Board extends HttpServlet {
    protected static final long serialVersionUID = 1L;

    public Board() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = getServletContext().getRealPath("/images");

        String redirect = req.getParameter("write");
        if(redirect != null){
            resp.sendRedirect("/webtest/boardWrite.jsp");
        }else{
            BoardDAO boardDAO = new BoardDAOImpl();
            List<BoardVO> boardVOList = boardDAO.getListSortedByDate();

            FileTransferDAO fileTransferDAO = new FileTransferDAOImpl();
            fileTransferDAO.downloadImageFiles(path);

            req.setAttribute("boards", boardVOList);
            req.setAttribute("path", path + "/images");

            RequestDispatcher view = req.getRequestDispatcher("./board.jsp");
            view.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String boardId = "";
        String title = req.getParameter("title");
        String subtitle = req.getParameter("subtitle");
        String contents = req.getParameter("contents");
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("userid");

        Part part = req.getPart("file");
        String originalFileName = part.getSubmittedFileName();
        System.out.println(originalFileName);

        String[] ext = originalFileName.split("\\.");
        String path = getServletContext().getRealPath("/images");

        String saveFileName = UUID.randomUUID().toString().replace("-", "");
        String pathSetting = path + "/images" + saveFileName + "." + ext[1];

        part.write(pathSetting);

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(title);
        boardDTO.setSubTitle(subtitle);
        boardDTO.setContents(contents);
        boardDTO.setUsername(username);

        BoardDAO boardDAO = new BoardDAOImpl();
        AttacheDAO attacheDAO = new AttacheDAOImpl();

        if (req.getParameter("boardId") != null) {
            boardId = req.getParameter("boardId");
            boardDTO.setId(Integer.parseInt(boardId));
            boardDAO.update(boardDTO);
            attacheDAO.delete(Integer.parseInt(boardId));
        }else {
            boardDAO.add(boardDTO);
        }

        if (req.getPart("file") == null) {
            resp.sendRedirect("./Board");
        } else  {
            AttacheDTO attacheDTO = new AttacheDTO();
            attacheDTO.setPath(path + "/images");
            attacheDTO.setOriginalFileName(originalFileName);
            attacheDTO.setSaveFileName(saveFileName);

            attacheDAO.add(attacheDTO);

            FileTransferDAO fileTransferDAO = new FileTransferDAOImpl();
            fileTransferDAO.uploadImageFiles(pathSetting, saveFileName);

            resp.sendRedirect("./Board");
        }
    }
}
