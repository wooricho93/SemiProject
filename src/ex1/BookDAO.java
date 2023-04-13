package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookDAO {
	// Connection: Java와 DB를 연결해 놓은 인터페이스
	// (Prepared)Statement와 ResultSet을 사용하려면 필수적으로 존재해야 함
	private Connection conn = null;
	// PreparedStatement: Statement에 기능을 더 추가한 것
	private PreparedStatement pstmt = null;
	// ResultSet: select문의 결과를 저장
	private ResultSet rs = null;
	Scanner sc = new Scanner(System.in);

	// Java와 DB를 연결하기 위해 필요한 데이터
	private static BookDAO dbb;
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String user = "book45";
	private final String pwd = "1234";

	// 생성자를 private으로 제한 -> 메서드(newInstance())를 통해서만 접근 가능
	private BookDAO() {
		try {
			// 데이터베이스의 드라이버를 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// SQL과 Java 연결
	// Connection 타입으로 선언: conn에 값을 넘겨주기 위해
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 연결 해제: 사용 후 연결 해제를 해주지 않으면 불필요한 자원이 낭비되며 새로운 Connection, (Prepared)Statement 생성 불가
	// 각 메서드마다 마지막에 반드시 사용
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

	// 객체를 만들기 위해 사용할 메서드: 생성자를 private으로 접근 제한했기 때문에
	public static BookDAO newInstance() {
		if (dbb == null)
			dbb = new BookDAO();
		return dbb;
	}

	// 전체 데이터 삽입
	public void book45Insert(BookVO vo) {
		try {
			// DB에 연결
			conn = getConnection();

			// SQL문 작성
			String sql = "insert into book (num, isbn, category, title, price, summary, author, pub, ratingavg, pictureurl) values (book45_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			// 오라클로 SQL 문장 전송
			pstmt = conn.prepareStatement(sql);

			// ?에 값 저장
			pstmt.setLong(1, vo.getIsbn());
			pstmt.setString(2, vo.getCategory());
			pstmt.setString(3, vo.getTitle());
			pstmt.setInt(4, vo.getPrice());
			pstmt.setString(5, vo.getSummary());
			pstmt.setString(6, vo.getAuthor());
			pstmt.setString(7, vo.getPub());
			pstmt.setString(8, vo.getRatingavg());
			pstmt.setString(9, vo.getPictureUrl());
			

			// 전송된 값 커밋 or 업데이트
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
}
