package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private static AlbumDAO dao;
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String user = "book45";
	private final String pwd = "1234";
	
	private AlbumDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void disConnection() {
		try {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static AlbumDAO newInstance() {
		if (dao == null) dao = new AlbumDAO();
		return dao;
	}
	
	public void albumInsert(AlbumVO vo) {
		try {
			conn = getConnection();
			String sql = "insert into album (num, productNum, category, albumtitle, albumprice, singer, ent, releasedate, albumpictureurl) values (album_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getProductNum());
			pstmt.setString(2, vo.getCategory());
			pstmt.setString(3, vo.getTitle());
			pstmt.setInt(4, vo.getPrice());
			pstmt.setString(5, vo.getSinger());
			pstmt.setString(6, vo.getEnt());
			pstmt.setString(7, vo.getReleasedate());
			pstmt.setString(8, vo.getPictureurl());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
}
