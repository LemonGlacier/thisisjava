package teamproject.comProjectPP.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONObject;

import teamproject.comProjectPP.DTO.Page;
import teamproject.comProjectPP.DTO.Product;

public class ProductService {
	public List<Product> getList(Page page) {
		// String sql = "select rownum as rnum ,product_id, product_name from product ";
		/*
		 * PreparedStatement pstmt; Connection conn = ConnectionProvider.getConnect();
		 * try { String sql = null;
		 * 
		 * if (startRow.equals(endRow)) { sql =
		 * "select rnum ,product_id, product_name, product_price from (select rownum as rnum,"
		 * +
		 * "product_id, product_name, product_price from product where rownum<= "+endRow
		 * + ") where rnum >="+startRow; pstmt = conn.prepareStatement(sql);
		 * //pstmt.setString(1, endRow); //pstmt.setString(2, startRow); } else { //sql
		 * = "select rownum," +
		 * "product_id, product_name, product_price from product where rownum >=" // +
		 * "? and rownum <= ?";
		 * sql="select rnum ,  product_id, product_name, product_price from (" +
		 * "select rownum as rnum, product_id, product_name, product_price from product where rownum <=? "
		 * + ")where rnum >=? "; pstmt = conn.prepareStatement(sql); pstmt.setString(1,
		 * endRow); pstmt.setString(2, startRow);
		 * 
		 * }
		 * 
		 * ResultSet rs = pstmt.executeQuery();
		 * 
		 * while (rs.next()) {
		 * 
		 * JSONObject root = new JSONObject(); for (int i = 1; i <= 4; i++) {
		 * root.put(String.valueOf(i), rs.getString(i));// "1" // System.out.println(i);
		 * }
		 * 
		 * // root.put("PRODUCT_NAME", rs.getString("PRODUCT_NAME"));
		 * 
		 * String json = root.toString();
		 * 
		 * user.send(json); } } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace();
		 * 
		 * 
		 * }finally { ConnectionProvider.exit(conn); }
		 */
		ProductDao productDao = new ProductDao();
		return productDao.selectProducts(page);
	}

	public int getTotalRow() {
		
		ProductDao productDao = new ProductDao();
		return productDao.selectCountProduct();
	}

	public static void getProductContent(SocketClient socketClient, String product_Id) {
		PreparedStatement pstmt;
		Connection conn = ConnectionProvider.getConnection();

		try {
			String sql = "select product_name,product_price,product_content,"
					+ "product_graphic_card,product_cpu,product_mainboard," + "product_os,product_memory "
					+ "from product " + "where product_id= '" + product_Id + "'";

			pstmt = conn.prepareStatement(sql);
			// " "
			ResultSet rs = pstmt.executeQuery();
			boolean isExist = rs.next();
			JSONObject root = new JSONObject();
			if (isExist) {

				root.put("Product_Name", rs.getString("product_name"));
				root.put("Product_Price", rs.getString("product_price"));
				root.put("Product_Content", rs.getString("product_content"));
				root.put("Product_Graphic_Card", rs.getString("product_graphic_card"));
				root.put("CPU", rs.getString("product_cpu"));
				root.put("Mainboard", rs.getString("product_mainboard"));
				root.put("OS", rs.getString("product_os"));
				root.put("Memory", rs.getString("product_memory"));
				String json = root.toString();
				socketClient.send(json);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.exit(conn);
		}
	}
}
