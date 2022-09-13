package kr.ac.woosuk.webprogramming.models.DAO;

import kr.ac.woosuk.webprogramming.models.DTO.UserDTO;
import kr.ac.woosuk.webprogramming.models.VO.UserVO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO{
    private String className;
    private String url;
    private String id;
    private String pass;
    private String today;
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    public UserDAOImpl() {
        this.className = "oracle.jdbc.driver.OracleDriver";
        this.url = "jdbc:oracle:thin:@192.168.86.146:1521:xe";
        this.id = "c##servlet";
        this.pass = "servlet";
        this.today = date.format(new Date());
    }

    @Override
    public void add(UserDTO user) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "INSERT INTO users ";
                   sql += "(id, userid, email, password, job, gender, introduction, create_date) ";
                   sql += "VALUES ";
                   sql += "(User_seq.NEXTVAL, '" + user.getUserid() + "', '" + user.getEmail() + "', '" +user.getPassword() + "', '" + user.getJob() + "', '" + user.getGender() + "', '" + user.getIntroduction() + "', TO_DATE('" + this.today + "', 'yyyy-mm-dd'))";
                   int resultCnt = stmt.executeUpdate(sql);
            System.out.println("user 테이블에 " + resultCnt + "개 튜플이 삽입되었습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {conn.close();} catch (SQLException e) {}
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
        }
    }

    @Override
    public void update(UserVO user) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "UPDATE users SET ";
                   sql += "email='" + user.getEmail() + "', ";
                   sql += "password='" + user.getPassword() + "', ";
                   sql += "job='" + user.getJob() + "', ";
                   sql += "introduction='" + user.getIntroduction() + "' ";
                   sql += "WHERE userid='" + user.getUserid() + "' ";
            int resultCnt = stmt.executeUpdate(sql);
            System.out.println("Users 테이블에 " + resultCnt + "개 튜플이 수정되었습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {conn.close();} catch (SQLException e) {}
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
        }
    }

    @Override
    public void deleteInformation(String user) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "UPDATE users SET ";
                   sql += "email = null, job = null, introduction = null, deleted_date = TO_DATE('" + this.today + "','yyyy-mm-dd') ";
                   sql += "WHERE userid='" + user + "'";
            int resultCnt = stmt.executeUpdate(sql);
            System.out.println("Users 테이블에" + resultCnt +"개의 튜플이 예비삭제 조치에 취했습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {conn.close();} catch (SQLException e) {}
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
        }
    }

    @Override
    public void completeDelete() {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "DELETE FROM users ";
                   sql += "WHERE deleted_date + 30 < TO_DATE('" + this.today + "', 'yyyy-mm-dd') ";
            int resultCnt = stmt.executeUpdate(sql);
            System.out.println("Users 테이블에서 " + resultCnt + "개의 튜플이 완전삭제 되었습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {conn.close();} catch (SQLException e) {}
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
        }
    }

    @Override
    public  UserDTO getUserByUserName(String user) {
        UserDTO result = new UserDTO();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM ";
            sql += "users ";
            sql += "WHERE userid='" + user +"'" ;
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                result.setId(rs.getInt("id"));
                result.setUserid(rs.getString("userid"));
                result.setEmail(rs.getString("email"));
                result.setPassword(rs.getString("password"));
                result.setJob(rs.getInt("job"));
                result.setGender(rs.getString("gender"));
                result.setIntroduction(rs.getString("introduction"));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try {rs.close();} catch (SQLException e) {}
            if (conn != null) try {conn.close();} catch (SQLException e) {}
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
        }
        return result;
    }
    @Override
    public List<UserVO> getList() {
        List<UserVO> results = new ArrayList<UserVO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "SELECT id, userid, job, gender, introduction, create_date FROM ";
                   sql += "users";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String userId = rs.getString("userid");
                int job = Integer.parseInt(rs.getString("job"));
                String gender = rs.getString("gender");
                String introduction = rs.getString("introduction");
                Date createDate = rs.getDate("create_date");
                UserVO userVO = new UserVO(id, userId, null, null, job, gender, introduction, createDate);
                results.add(userVO);
            }
        }catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try {rs.close();} catch (SQLException e) {}
            if (conn != null) try {conn.close();} catch (SQLException e) {}
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
        }
        return results;
    }
}
