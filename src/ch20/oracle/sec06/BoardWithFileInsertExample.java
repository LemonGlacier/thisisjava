package ch20.oracle.sec06;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardWithFileInsertExample {

	public static void main(String[] args) {
		Connection conn=null;
		try {
			//JDBC driver을 메모리로 로딩하고 DriverManager에 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//DB와 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","java","oracle");
			System.out.println("연결 성공");
			
			//DB 작업
			String sql = new StringBuilder()
					.append("update boards set ")
					.append("btitle=?, ")
					.append("bcontent=?, ")
					.append("bfilename=?, ")
					.append("bfiledata=? ")
					.append("where bno=?")
					.toString();
			
			//PreparedStatement 얻기
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "비가 내려요");
			pstmt.setString(2, "겨울비는 추워요");
			pstmt.setString(3, "photo2");
			pstmt.setBlob(4, new FileInputStream("src/ch20/oracle/sec07/photo2.jpg"));
			pstmt.setInt(5, 4);
			
			int rows = pstmt.executeUpdate();
			System.out.println("수정된 행 수: " + rows);
			
			pstmt.close();
						
		} catch (ClassNotFoundException e) {         //BuildPath 확인
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn !=null) {
			//DB 연결 끊기
				try {
					conn.close();
				} catch (SQLException e) {
				}
			System.out.println("연결 끊김");
			}
		}
		

	}

}
