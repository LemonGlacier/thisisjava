package ch20.oracle.sec08;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDeleteExample {

	public static void main(String[] args) {
		Connection conn=null;
		try {
			//JDBC driver을 메모리로 로딩하고 DriverManager에 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//DB와 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","java","oracle");
			System.out.println("연결 성공");
			
			//DB 작업
			String sql = "delete from boards where bwriter=?";
			
			//PreparedStatement 얻기
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno", "btitle"});
			
			pstmt.setString(1, "winter");
			
			int rows = pstmt.executeUpdate();
			System.out.println("삭제된 행 수: " + rows);
			
			if(rows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				
				if(rs.next()) {
					int bno = rs.getInt(1);
					System.out.println("저장된 bno: " + bno);
					
					String btitle = rs.getString(2);
					System.out.println("저장된 btitle: " + bno);
				} //데이터가 없으면 false //예제에서는 상관 없긴 함
				rs.close();
			}
			
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
