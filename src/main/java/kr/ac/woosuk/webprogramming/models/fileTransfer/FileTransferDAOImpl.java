package kr.ac.woosuk.webprogramming.models.fileTransfer;

import java.io.*;
import java.sql.*;

public class FileTransferDAOImpl implements FileTransferDAO {
    private String className;
    private String url;
    private String id;
    private String pass;

    public FileTransferDAOImpl() {
        this.className = "oracle.jdbc.driver.OracleDriver";
        this.url = "jdbc:oracle:thin:@192.168.86.146:1521:xe";
        this.id = "c##servlet";
        this.pass = "servlet";
    }
    @Override
    public void downloadImageFiles(String path) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);
            stmt = conn.createStatement();

            String sql = "SELECT save_file_name, imagefile, original_file_name FROM attache_file ";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                InputStream imageFile = rs.getBinaryStream("imagefile");
                String saveFileName = rs.getString("save_file_name");
                String originalFileName = rs.getString("original_file_name");
                String[] ext = originalFileName.split("\\.");

                FileOutputStream fos = new FileOutputStream(path + "/images" + saveFileName + "." +ext[1]);
                byte[] bytes = new byte[512];
                while(true) {
                    int len = imageFile.read(bytes);
                    if(len<=0) break;
                    fos.write(bytes,0,len);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try {rs.close();} catch (SQLException e) {}
            if (stmt != null) try {stmt.close();} catch (SQLException e) {}
            if (conn != null) try {conn.close();} catch (SQLException e) {}
        }
    }

    @Override
    public void uploadImageFiles(String path, String savedFileName){
        File file = new File(path);
        int fileSize = (int)file.length();
        byte[] bytes = new byte[fileSize];
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            InputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(this.className);
            conn = DriverManager.getConnection(this.url, this.id, this.pass);

            String sql = "UPDATE attache_file SET ";
            sql += "imagefile = ?";
            sql += "WHERE save_file_name= '" + savedFileName + "' ";
            psmt = conn.prepareStatement(sql);
            psmt.setBytes(1, bytes);
            int resultCnt = psmt.executeUpdate();
            System.out.println("Attache_file 테이블에 " + resultCnt + "개 튜플이 삽입되었습니다." );
        } catch (ClassNotFoundException e) {
            System.out.println("오라클 드라이버를 찾을수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psmt != null) try {psmt.close();} catch (SQLException e) {}
            if (conn != null) try {conn.close();} catch (SQLException e) {}
        }
    }
}
