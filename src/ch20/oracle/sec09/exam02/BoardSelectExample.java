package ch20.oracle.sec09.exam02;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardSelectExample {

	public static void main(String[] args) {
		Connection conn=null;
		try {
			//JDBC driver을 메모리로 로딩하고 DriverManager에 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//DB와 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","java","oracle");
			System.out.println("연결 성공");
			
			//DB 작업
			String sql = "select bno, btitle, bcontent, bwriter, bdate, bfilename, bfiledata from boards where bwriter=?";  //?에 값이 들어감
			
			//PreparedStatement 얻기
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "winter");
			
			ResultSet rs = pstmt.executeQuery();
			List<Board> boards = new ArrayList<>();
			while (rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBfilename(rs.getString("bfilename"));
				board.setBfiledata(rs.getBlob("bfiledata"));
				
				Blob blob = board.getBfiledata();
				if(blob!=null) {
					InputStream is = blob.getBinaryStream();
					OutputStream os = new FileOutputStream("C:/Temp/" + board.getBfilename());
					
					is.transferTo(os);
					os.flush();
					is.close();
					os.close();
				}
				
				boards.add(board);
			}
	        rs.close();
	        printBoards(boards);
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
	public static void printBoards(List <Board> boards) {
		//외부 반복자
		/*for(Board board : boards) {
			System.out.println(board);
		}*/           
		//내부 반복자
		boards.stream().forEach(b-> System.out.println(b));
	}

}
