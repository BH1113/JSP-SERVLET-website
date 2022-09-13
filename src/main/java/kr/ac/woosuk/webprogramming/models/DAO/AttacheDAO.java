package kr.ac.woosuk.webprogramming.models.DAO;

import kr.ac.woosuk.webprogramming.models.DTO.ImageDTO;
import kr.ac.woosuk.webprogramming.models.DTO.AttacheDTO;
import kr.ac.woosuk.webprogramming.models.VO.AttacheVO;

public interface AttacheDAO {
    public abstract void add(AttacheDTO attache);
    public abstract void update(AttacheVO attache);
    public abstract void delete(int boardId);
    public abstract AttacheVO get(int boardId);
}
