package ch20.oracle.sec09.exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSelectExample {

	public static void main(String[] args) {
		Connection conn=null;
		try {
			//JDBC driver을 메모리로 로딩하고 DriverManager에 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//DB와 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","java","oracle");
			System.out.println("연결 성공");
			
			//DB 작업
			String sql = "select userid, username, userpassword, userage, useremail from users where userid=?";  //하나의 행(id 한 개)
			
			//PreparedStatement 얻기
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno", "btitle"});
			
			pstmt.setString(1, "winter");
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				/*String userId = rs.getString("userid");
				String userName = rs.getString("username");
				String userPassword = rs.getString("userpassword");
				int userAge = rs.getInt(4);
				String userEmail = rs.getString(5);
				
				printUser(userId, userName, userPassword, userAge, userEmail);
				*/
				
				User user = new User();
				user.setUserId(rs.getString("userId"));
				user.setUserName(rs.getString("userName"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserAge(rs.getInt("userAge"));
				user.setUserEmail(rs.getString("userEmail"));
				printUser(user);
			}else {                 //가져온 행이 없을 때
				System.out.println("사용자 아이디가 존재하지 않음");
			}
			rs.close();
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
	/*public static void printUser(String userId, String userName, String userPassword, int userAge, String userEmail*) {
		System.out.println("userId: " + userId);
		System.out.println("userName: " + userName);
		System.out.println("userPassword: " + userPassword);
		System.out.println("userAge: " + userAge);
		System.out.println("userEmail: " + userEmail);
	}*/
	public static void printUser(User user) {
		/*System.out.println("userId: " + user.getUserId());
		System.out.println("userName: " + user.getUserName());
		System.out.println("userPassword: " + user.getUserPassword());
		System.out.println("userAge: " + user.getUserAge());
		System.out.println("userEmail: " + user.getUserEmail());
		*/
		System.out.println(user);
	}

}
