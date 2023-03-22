package Team5_com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONObject;

import Team5_com.CC2.Board;
import Team5_com.CC2.ConnectionProvider;
import Team5_com.DTO.Free_Board;
import Team5_com.DTO.Free_Comment;

public class DAOBoard {
	//필드
	String json;
	Connection conn = ConnectionProvider.getConnect();
	
	//메소드
	//list
	
	//String sql = "select Free_Bno, Free_Btitle, Free_Bcontent, Free_Date, Free_Comment_Num, User_Id from free_board order by free_bno desc where Free_Bno >= "
	//	+ String.valueOf(page.getStartRow()) + " and Free_Bno<= " + String.valueOf(page.getEndRow());
	
	//select c1,c2 from( select rnum, c1, c2 from (select rownum as rname, c1, c2 from Board where rownum<= ? order by bno) where rnum> = ?)
	
	//row
	public String totalRowNumFB() {
		try {
		String sql = "select count(*) from FREE_BOARD; ";  
		JSONObject json = new JSONObject();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) json.put("totalRowNumFB", rs.getInt("count(*)")).toString();
		else json=null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public String totalRowNumRB() {
		try {
		String sql = "select count(*) from REVIEW_BOARD; ";  
		JSONObject json = new JSONObject();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) json.put("totalRowNumRB", rs.getInt("count(*)")).toString();
		else json=null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public String totalRowNumQB() {
		try {
		String sql = "select count(*) from QNA_BOARD; ";  
		JSONObject json = new JSONObject();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) json.put("totalRowNumQB", rs.getInt("count(*)")).toString();
		else json=null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public String totalRowNumFC() {
		try {
		String sql = "select count(*) from FREE_COMMENT; ";  
		JSONObject json = new JSONObject();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) json.put("totalRowNumFC", rs.getInt("count(*)")).toString();
		else json=null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public String totalRowNumQC() {
		try {
		String sql = "select count(*) from QNA_COMMENT; ";  
		JSONObject json = new JSONObject();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) json.put("totalRowNumQC", rs.getInt("count(*)")).toString();
		else json=null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	//create
	public void createFB(JSONObject json) {
		try {
		String sql = "insert into free_board (FREE_BNO, FREE_BTITLE, FREE_BCONTENT, FREE_DATE, USERS_USER_ID) values (FREE_BNO.nextval, ?, ?, ?, ?) ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, json.getString("Free_Btitle"));
		pstmt.setString(2, json.getString("Free_Bcontent"));
		pstmt.setString(3, json.getString("Free_Date"));
		pstmt.setString(4, json.getString("User_Id"));
		pstmt.executeUpdate();
		pstmt.close();
				
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createFC(JSONObject json) {
		try {
		//String sql = "insert into free_comment (Free_CoContent, Free_CoDate, User_Id, Free_Board_Free_Bno) values (?, ?, ?, ?); ";
		String sql = "insert into FREE_COMMENT (FREE_CONUM, FREE_COCONTENT, FREE_CODATE, USER_USER_ID, FREE_BOARD_FREE_BNO) values (FREE_CONUM.nextval, ?, ?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, json.getString("Free_Cocontent"));
		pstmt.setString(2, json.getString("Free_Codate"));
		pstmt.setString(3, json.getString("User_Id"));
		pstmt.setString(4, json.getString("Free_Bno"));
		pstmt.executeUpdate();
		pstmt.close();
				
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createRB(JSONObject json) {
		try {
			String sql = "insert into REVIEW_BOARD (REVIEW_BNO, REVIEW_BTITLE, REVIEW_BCONTENT, REVIEW_DATE, REVIEW_RATE, USER_USER_ID, ORDER_DETAIL_ORDER_DETAIL_ID) values (REVIEW_BNO.nextval, ?, ?, ?, ?, ?, ?) ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, json.getString("REVIEW_BTITLE"));
			pstmt.setString(2, json.getString("REVIEW_BCONTENT"));
			pstmt.setString(3, json.getString("REVIEW_DATE"));
			pstmt.setInt(4, json.getInt("REVIEW_RATE"));
			pstmt.setString(5, json.getString("USER_USER_ID"));
			pstmt.setString(6, json.getString("ORDER_DETAIL_ID"));
			pstmt.executeUpdate();
			pstmt.close();
					
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public void createQB(JSONObject json) {
		try {
			String sql = "insert into QNA_BOARD (QNA_BNO, QNA_BTITLE, QNA_BCONTENT, QNA_BDATE, PRODUCT_PRODUCT_ID, USER_USER_ID, ORDER_DETAIL_ORDER_DETAIL_ID) values (QNA_BNO.nextval, ?, ?, ?, ?, ?, ?) ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, json.getString("QNA_BTITLE"));
			pstmt.setString(2, json.getString("QNA_BCONTENT"));
			pstmt.setString(3, json.getString("QNA_BDATE"));
			pstmt.setString(4, json.getString("PRODUCT_ID"));
			pstmt.setString(5, json.getString("USER_ID"));
			//pstmt.setBoolean(6, json.getBoolean("QNA_COMMENT_EXIST"));
			pstmt.setString(6, json.getString("ORDER_DETAIL_ID"));
			pstmt.executeUpdate();
			pstmt.close();
					
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public void createQC(JSONObject json) {
		
	}
		
	//read
	public String readFB(String bno) {
		try {
			//String sql = "select Free_Btitle, Free_Bcontent, Free_Date, Free_Comment_Num, User_Id from free_board where Free_Bno = ? order by Free_Bno desc ";
			String sql = "select FREE_BNO, FREE_BTITLE, FREE_BCONTENT, FREE_DATE, FREE_COMMENT_NUM, USERS_USER_ID, (select count(*) from free_comment where FREE_BOARD_FREE_BNO= ? ) as FCN from free_board where free_bno = ? order by Free_Bno desc ";
			JSONObject json = new JSONObject();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.setString(2, bno);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Free_Board fb = new Free_Board();            //**fb/b
				fb.setFree_Bno(rs.getString("FREE_BNO"));
				fb.setFree_Btitle(rs.getString("FREE_BTITLE"));
				fb.setUser_Id(rs.getString("USERS_USER_ID"));
				//content까지 보내든가 select에서 빼든가
				fb.setFree_Date(rs.getDate("Free_Date"));
				fb.setFree_Comment_Num(rs.getInt("FCN"));               
				json.put("FB", fb).toString();
			} else json=null;
			rs.close();
			pstmt.close();
			
		} catch(Exception e) {
			json = e.getMessage();
		}
		return json;
	}
	
	public String readFC(String bno, String cno) {
		try {
			String sql = "select FREE_CONUM, FREE_COCONTENT, FREE_CODATE, USER_USER_ID from FREE_COMMENT order by FREE_CONUM desc where FREE_CONUM = ? and FREE_BOARD_FREE_BNO = ? ";
			JSONObject json = new JSONObject();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cno);
			pstmt.setString(2, bno);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Free_Comment fc = new Free_Comment();            //**fb/b
				fc.setFree_Conum(rs.getString("FREE_CONUM"));
				fc.setFree_Cocontent(rs.getString("FREE_COCONTENT"));
				fc.setUser_Id(rs.getString("USER_USER_ID"));
				fc.setFree_Codate(rs.getDate("FREE_CODATE"));
				
				json.put("FC", fc).toString();
			} else json=null;
			rs.close();
			pstmt.close();
			
		} catch(Exception e) {
			json = e.getMessage();
		}		
		return json;
	}
	
	public String readRB(String bno) {
		return json;
	}
	public String readQB(String bno) {
		return json;
	}
	
	public String readQC(String bno, String cno) {
		return json;
	}
	
	//update
	public void updateFB(JSONObject json) {
		
	}
	
	//delete
	public void deleteFB(String bno) {
		try {
			String sql = "delete from FREE_BOARD where FREE_BNO = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFC(String bno, String cno) {
		try {
			String sql = "delete from FREE_COMMENT where FREE_BNO = ? and FREE_CONUM = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.setString(2, cno);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRB(String bno) {
		try {
			String sql = "delete from REVIEW_BOARD where REVIEW_BNO = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteQB(String bno) {
		try {
			String sql = "delete from QNA_BOARD where QNA_BNO = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteQC(String bno, String cno) {
		try {
			String sql = "delete from QNA_COMMENT where QNA_BOARD_QNA_BNO = ? and QNA_CONUM = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.setString(2, cno);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
