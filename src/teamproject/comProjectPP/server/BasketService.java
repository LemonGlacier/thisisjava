package teamproject.comProjectPP.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

public class BasketService {
	SocketClient user;
	public BasketService(SocketClient user) {
		this.user=user;
	}
	public void getOption1(String product_Id) {
		PreparedStatement pstmt;
		Connection conn = ConnectionProvider.getConnection();
		/*sql = "select d.detail_color ,d.detail_capacity,"
					+ "d.detail_qnt,d.detail_id "
					+ "from product p, product_detail d "
					+ "where p.product_id=d.product_product_id"
					+ "and p.product_id= '"+product_Id+"' "
					+ "and d.detail_qnt!=0";*/
		String sql = "select distinct d.detail_color "
					+ "from product p, product_detail d "
					+ "where p.product_id=d.product_product_id "
					+ "and p.product_id= '"+product_Id+"' "
					+ "and d.detail_qnt!=0";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		JSONObject root = new JSONObject();
			boolean isExist=rs.next();
			while(isExist){
				root=new JSONObject();
				//root.put("Detail_Id", rs.getString("detail_id"));
				root.put("Detail_Color", rs.getString("detail_color"));
				//root.put("Detail_Capacity", rs.getString("detail_capacity"));
				//root.put("Detail_Qnt", rs.getInt("detail_qnt"));
				isExist=rs.next();
				root.put("isExist", isExist);
				String json = root.toString();
				user.send(json);
				
			};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionProvider.exit(conn);
		}
	}
	public void getOption2(String product_Id, String color) {
		PreparedStatement pstmt;
		Connection conn = ConnectionProvider.getConnection();
		
		String sql = "select distinct d.detail_capacity "
					+ "from product p, product_detail d "
					+ "where p.product_id=d.product_product_id "
					+ "and p.product_id= '"+product_Id+"' "
					+ "and d.detail_qnt!=0 "
					+ "and d.detail_color='"+color+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		JSONObject root = new JSONObject();
			boolean isExist=rs.next();
			while(isExist) {
				root=new JSONObject();
				//root.put("Detail_Id", rs.getString("detail_id"));
				root.put("Detail_Capacity", rs.getString("detail_capacity"));
				//root.put("Detail_Capacity", rs.getString("detail_capacity"));
				//root.put("Detail_Qnt", rs.getInt("detail_qnt"));
				isExist=rs.next();
				root.put("isExist", isExist);
				String json = root.toString();
				user.send(json);
				
			};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionProvider.exit(conn);
		}
	}
}
