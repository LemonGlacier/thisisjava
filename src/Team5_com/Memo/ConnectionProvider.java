package Team5_com.Memo;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	
	private static Connection conn;
		
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.driver.OrcleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@kosa402.iptime.org:50011/orcl", "project1", "oracle");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/*
	public static synchronized Connection getConnection() {
		if(conn == null) {
			try {
				Class.forName("oracle.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@kosa402.iptime.org:50011/orcl", "project1", "oracle");
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
				return conn;

	}
	
	//방법3
	
	
	//커넥션 하나만 쓸 때 문제점 : 동시 요청 시 서버의 커넥션을 동시에 사용하려고 함 하나의 스레드가 커넥션을 사용하려고 할때 동시에 사용 문제

	
	
	public static Connection getConnection() {
		Connection conn = null;
		if(conn == null) {
			try {
				Class.forName("oracle.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@kosa402.iptime.org:50011/orcl", "project1", "oracle");
				
			} catch(Exception e) {
				e.printStackTrace();
			}
				return conn;

	}
	
}*/
}
