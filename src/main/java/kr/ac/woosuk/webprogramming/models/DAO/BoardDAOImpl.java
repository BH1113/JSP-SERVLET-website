package kr.ac.woosuk.webprogramming.models.DAO;

import kr.ac.woosuk.webprogramming.models.DTO.BoardDTO;
import kr.ac.woosuk.webprogramming.models.VO.AttacheVO;
import kr.ac.woosuk.webprogramming.models.VO.BoardVO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BoardDAOImpl implements BoardDAO{
    private String className;
    private String url;
    private String id;
    private String pass;
    private String today;
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    public BoardDAOImpl() {
        this.className = "oracle.jdbc.driver.OracleDriver";
        this.url = "jdbc:oracle:thin:@192.168.86.146:1521:xe";
        this.id = "c##servlet";
        this.pass = "servlet";
        this.today = date.format(new java.util.Date());
    }

    @Override
    public void add(BoardDTO board) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "INSERT INTO board";
                   sql += "(boardid, boardname, title, sub_title, contents, create_date, edit_date) ";
                   sql += "VALUES ";
                   sql += "(Board_seq.NEXTVAL, '" + board.getUsername() + "', '" + board.getTitle() + "', '" + board.getSubTitle() + "', '" + board.getContents() + "', TO_DATE('" + this.today + "', 'yyyy-mm-dd'), NULL)";
                   int resultCnt = stmt.executeUpdate(sql);
                   System.out.println("board 테이블에 " + resultCnt + "개 튜플이 삽입되었습니다.");
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
    public void update(BoardDTO board) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "UPDATE board SET ";
                   sql += "title= '" + board.getTitle() + "', ";
                   sql += "subtitle= '" + board.getSubTitle() + "', ";
                   sql += "contents= '" + board.getContents() + "', ";
                   sql += "edit_date= TO_DATE('" + this.today + "', 'yyyy-mm-dd') ";
                   sql += "WHERE boardid= " + board.getId();
            int resultCnt = stmt.executeUpdate(sql);
            System.out.println("board 테이블에 " + resultCnt + "개 튜플이 수정되었습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try{conn.close();} catch (SQLException e) {}
            if (stmt != null) try{stmt.close();} catch (SQLException e) {}
        }
    }

    @Override
    public void delete(BoardVO board) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "DELETE FROM board";
                   sql += "WHERE boardid= " + board.getId();
            int resultCnt = stmt.executeUpdate(sql);
            System.out.println("board 테이블에 " + resultCnt + "개 튜플이 삭제 되었습니다.");
        }catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try{conn.close();} catch (SQLException e) {}
            if (stmt != null) try{stmt.close();} catch (SQLException e) {}
        }
    }

    @Override
    public BoardVO getByBoardId(int boardId) {
        BoardVO result = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "SELECT * ";
                   sql += "FROM board ";
                   sql += "WHERE boardid = " + boardId;
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("boardid");
                String name = rs.getString("boardname");
                String title = rs.getString("title");
                String subTitle = rs.getString("sub_title");
                String contents = rs.getString("contents");
                Date createDate = rs.getDate("create_date");
                Date editDate = rs.getDate("edit_date");
                result = new BoardVO(id, title, subTitle, contents, name, createDate, editDate, null);
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
    public List<BoardVO> getAllList(){
        List<BoardVO> results = new ArrayList<BoardVO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM ";
                   sql += "board LEFT OUTER JOIN attache_file ";
                   sql += "ON board.boardid = attache_file.boardid ";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("boardid");
                String name = rs.getString("boardname");
                String title = rs.getString("title");
                String subTitle = rs.getString("sub_title");
                String contents = rs.getString("contents");
                Date createDate = rs.getDate("create_date");
                Date editDate = rs.getDate("edit_date");
                String originalFileName = rs.getString("original_file_name");
                AttacheVO attacheVO = new AttacheVO(0, 0, null, null, originalFileName, null);
                BoardVO boardVO = new BoardVO(id, title, subTitle, contents, name, createDate, editDate, attacheVO);
                results.add(boardVO);
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
        return results;

    }

    @Override
    public List<BoardVO> getListByTitle(String searchTitle) {
        List<BoardVO> results = new ArrayList<BoardVO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM ";
                   sql += "board ";
                   sql += "WHERE title like '%"  + searchTitle + "%' ";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("boardid");
                String name = rs.getString("boardname");
                String title = rs.getString("title");
                String subtitle = rs.getString("sub_title");
                String contents = rs.getString("contents");
                Date createDate = rs.getDate("create_date");
                Date editDate = rs.getDate("edit_date");
                BoardVO boardVO = new BoardVO(id, title, subtitle, contents, name, createDate, editDate, null);
                results.add(boardVO);
            }
        }catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try {rs.close();} catch (SQLException e) {}
            if (conn != null) try {conn.close();} catch (SQLException e) {}
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
        }
        return results;
    }

    @Override
    public List<BoardVO> getListByWriter(String writer) {
        List<BoardVO> results = new ArrayList<BoardVO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM ";
            sql += "board ";
            sql += "WHERE boardname= '"  + writer + "' ";
            sql += "ORDER BY create_date DESC, title";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("boardid");
                String name = rs.getString("boardname");
                String title = rs.getString("title");
                String subtitle = rs.getString("sub_title");
                String contents = rs.getString("contents");
                Date createDate = rs.getDate("create_date");
                Date editDate = rs.getDate("edit_date");
                BoardVO boardVO = new BoardVO(id, title, subtitle, contents, name, createDate, editDate, null);
                results.add(boardVO);
            }
        }catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try {rs.close();} catch (SQLException e) {}
            if (conn != null) try {conn.close();} catch (SQLException e) {}
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
        }
        return results;
    }

    @Override
    public List<BoardVO> getListByDate(String from, String to) {
        List<BoardVO> results = new ArrayList<BoardVO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM ";
            sql += "board ";
            sql += "WHERE TO_DATE('" + from +"', 'yyyy-mm-dd') < board.create_date AND board.create_date < TO_DATE('" + to + "', 'yyyy-mm-dd) ";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("boardid");
                String name = rs.getString("boardname");
                String title = rs.getString("title");
                String subtitle = rs.getString("sub_title");
                String contents = rs.getString("contents");
                Date createDate = rs.getDate("create_date");
                Date editDate = rs.getDate("edit_date");
                BoardVO boardVO = new BoardVO(id, title, subtitle, contents, name, createDate, editDate, null);
                results.add(boardVO);
            }
        }catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try {rs.close();} catch (SQLException e) {}
            if (conn != null) try {conn.close();} catch (SQLException e) {}
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
        }
        return results;
    }

    @Override
    public List<BoardVO> getListSortedByDate() {
        List<BoardVO> results = new ArrayList<BoardVO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String[] originalFileName = {"0", "jpg"};

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM ";
            sql += "board LEFT OUTER JOIN attache_file ";
            sql += "ON board.boardid = attache_file.boardid ";
            sql += "ORDER BY create_date DESC, title";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("boardid");
                String name = rs.getString("boardname");
                String title = rs.getString("title");
                String subtitle = rs.getString("sub_title");
                String contents = rs.getString("contents");
                Date createDate = rs.getDate("create_date");
                Date editDate = rs.getDate("edit_date");
                if(rs.getString("original_file_name")!=null) {
                    originalFileName = rs.getString("original_file_name").split("\\.");
                }
                String save_file_name = "images"+ rs.getString("save_file_name");
                AttacheVO attacheVO = new AttacheVO(0, 0, null, originalFileName[1], save_file_name, null);
                BoardVO boardVO = new BoardVO(id, title, subtitle, contents, name, createDate, editDate, attacheVO);
                results.add(boardVO);
            }
        }catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try {rs.close();} catch (SQLException e) {}
            if (conn != null) try {conn.close();} catch (SQLException e) {}
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
        }
        return results;
    }
}
