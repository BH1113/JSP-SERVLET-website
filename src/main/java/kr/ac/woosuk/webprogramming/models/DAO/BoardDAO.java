package kr.ac.woosuk.webprogramming.models.DAO;

import kr.ac.woosuk.webprogramming.models.DTO.BoardDTO;
import kr.ac.woosuk.webprogramming.models.VO.BoardVO;

import java.util.List;

public interface BoardDAO {
    public abstract void add(BoardDTO board);
    public abstract void update(BoardDTO board);
    public abstract void delete(BoardVO board);
    public abstract BoardVO getByBoardId(int boardId);
    public abstract List<BoardVO> getAllList();
    public abstract List<BoardVO> getListByTitle(String searchTitle);
    public abstract List<BoardVO> getListByWriter(String writer);
    public abstract List<BoardVO> getListByDate(String from, String to);
    public abstract List<BoardVO> getListSortedByDate();
}
