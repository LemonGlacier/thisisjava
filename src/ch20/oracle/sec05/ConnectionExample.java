package ch20.oracle.sec05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionExample {

	public static void main(String[] args) {
		/*Connection conn=null;
		try {
			//JDBC driver을 메모리로 로딩하고 DriverManager에 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//DB와 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.24:1521/orcl","java","oracle");
			System.out.println("연결 성공");
			
			//DB 작업
			
		} catch (ClassNotFoundException e) {         //BuildPath 확인
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(SQLException e) {
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
		}*/
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			try(Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.24:1521/orcl","java","oracle")) {
				System.out.println("연결 성공");
				//DB 작업
			} catch(Exception e) {
				
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
	}

}
