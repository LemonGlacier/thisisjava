package Team5_com.Memo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Team5_com.CC2.Board;

public class BoardService {
	public List<Board> getList() {   //^^daoboard
			
			Connection conn = null;
			
			try {
			
				conn = ConnectionProvider.getConnection();
				
				
			} catch(Exception e) {
				
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return null;
		}
	}

