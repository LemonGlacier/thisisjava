package teamproject.comProjectPP.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import teamproject.comProjectPP.DTO.Page;
import teamproject.comProjectPP.DTO.Product;

public class ProductDao {

	public List<Product> selectProducts(Page page) {
		PreparedStatement pstmt;
		Connection conn = ConnectionProvider.getConnection();
		String sql = "select rnum ,  product_id, product_name, product_price from ("
				+ "select rownum as rnum, product_id, product_name, product_price from product where rownum <=? "
				+ ")where rnum >=? ";
		List<Product> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(page.getEndRow()));
			pstmt.setString(2, String.valueOf(page.getStartRow()));
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Product pro = new Product();

				pro.setProduct_Id(rs.getString("product_id"));
				pro.setProduct_Name(rs.getString("product_name"));
				pro.setProduct_Price(rs.getInt("product_price"));
				list.add(pro);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionProvider.exit(conn);
		}

		return list;
	}

	public int selectCountProduct() {
		PreparedStatement pstmt;
		Connection conn = ConnectionProvider.getConnection();
		String sql = "select count(product_id)as count from product";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				

				return rs.getInt("count");
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionProvider.exit(conn);
		}

		return 0;
	}

}
