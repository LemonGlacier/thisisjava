package Team5_com.DTO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	public static Connection getConnect() {
		// db연결
		Connection conn = null;
		try {

			Class.forName("oracle.jdbc.OracleDriver");
			// 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@kosa402.iptime.org:50051/orcl", "team5", "oracle");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return conn;
	}

	public static void exit(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {

			}

		}

	}
}

