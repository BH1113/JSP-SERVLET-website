package kr.ac.woosuk.webprogramming.models.DAO;

import kr.ac.woosuk.webprogramming.models.DTO.UserDTO;
import kr.ac.woosuk.webprogramming.models.VO.UserVO;

import java.util.List;

public interface UserDAO {
    public abstract void add(UserDTO user);
    public abstract void update(UserVO user);
    public abstract void deleteInformation(String user);
    public abstract void completeDelete();
    public abstract UserDTO getUserByUserName(String user);
    public abstract List<UserVO> getList();
}
