package teamproject.comProjectPP.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import teamproject.comProjectPP.DTO.Basket;
import teamproject.comProjectPP.DTO.Basket_Detail;
import teamproject.comProjectPP.server.ConnectionProvider;

public class DAO_Basket {
	String Output;
	
	public String Create (Basket basket) {
		String sql = "insert into basket (users_user_id)  values  ( ? ) ";
		Connection conn = ConnectionProvider.getConnection();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, basket.getUser_Id());
			pstmt.executeUpdate();
			pstmt.close();
			Output = "success";
			
		} catch (SQLException e) {
			Output = "fail";
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Output;
	}
	
	
	
	
	
	
	
}
