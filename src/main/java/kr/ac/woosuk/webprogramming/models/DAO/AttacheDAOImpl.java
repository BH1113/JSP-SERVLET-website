package kr.ac.woosuk.webprogramming.models.DAO;

import kr.ac.woosuk.webprogramming.models.DTO.AttacheDTO;
import kr.ac.woosuk.webprogramming.models.DTO.ImageDTO;
import kr.ac.woosuk.webprogramming.models.VO.AttacheVO;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttacheDAOImpl implements AttacheDAO {
    private String className;
    private String url;
    private String id;
    private String pass;

    public AttacheDAOImpl() {
        this.className = "oracle.jdbc.driver.OracleDriver";
        this.url = "jdbc:oracle:thin:@192.168.86.146:1521:xe";
        this.id = "c##servlet";
        this.pass = "servlet";
    }

    @Override
    public void add(AttacheDTO attache) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);

            String sql = "INSERT INTO attache_file ";
            sql += "(attacheid, boardid, path, original_file_name, save_file_name, imagefile) ";
            sql += "VALUES ";
            sql += "(attache_seq.NEXTVAL, (SELECT MAX(boardid) FROM BOARD), '" + attache.getPath() + "', '" + attache.getOriginalFileName() + "', '" + attache.getSaveFileName() + "') ";
            int resultCnt = stmt.executeUpdate(sql);
            System.out.println("Attache_file 테이블에 " + resultCnt + "개 튜플이 삽입되었습니다." );
        } catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
            if (conn != null) try {conn.close();} catch (SQLException e) {}
        }
    }

    @Override
    public void update(AttacheVO attache) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "UPDATE attache_file SET ";
            sql += "boardid= '" + attache.getBoardId() + "', ";
            sql += "path= '" + attache.getPath() + "', ";
            sql += "original_file_name= " + attache.getOriginalFileName() + "', ";
            sql += "save_file_name= " + attache.getSaveFileName();
            sql += "WHERE attacheid= " + attache.getId();
            int resultCnt = stmt.executeUpdate(sql);
            System.out.println("Attache_file 테이블에 " + resultCnt + "개 튜플이 수정되었습니다." );
        } catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
            if (conn != null) try {conn.close();} catch (SQLException e) {}
        }
    }

    @Override
    public void delete(int boardId) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "DELETE FROM attache_file ";
                   sql += "WHERE id= " + boardId;
            int resultCnt = stmt.executeUpdate(sql);
            System.out.println("Attache_file 테이블에 " + resultCnt + "개 튜플이 삭제되었습니다." );
        } catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
            if (conn != null) try {conn.close();} catch (SQLException e) {}
        }
    }

    @Override
    public AttacheVO get(int boardId) {
        AttacheVO result = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "SELECT boardid, path, original_file_name as on, save_file_name as sn, imagefile FROM attache_file ";
                   sql += "WHERE boardid=" + boardId;
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("attacheid");
                int boardid = rs.getInt("boardid");
                String path = rs.getString("path");
                String original = rs.getString("on");
                String save = rs.getString("sn");
                byte[] bytes = rs.getBytes("imagefile");
                result = new AttacheVO(id, boardid, path, original, save, bytes);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try {rs.close();} catch (SQLException e) {}
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
            if (conn != null) try {conn.close();} catch (SQLException e) {}
        }
        return result;
    }
}

