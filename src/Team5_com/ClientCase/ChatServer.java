package Team5_com.ClientCase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

public class ChatServer {
	// 필드
		ServerSocket serverSocket;
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		//private Connection conn;
		DataInputStream dis;
		DataOutputStream dos;
		JSONObject jsonObject = new JSONObject();
		String json;
		
		 

		public void start() throws IOException {
			serverSocket = new ServerSocket(50001);
			System.out.println("[서버] 시작됨");
			ConnectionProvider.getConnection();
			Thread thread = new Thread(() -> {
				try {
					while (true) {
						Socket socket = serverSocket.accept();
						SocketClient sc = new SocketClient(this, socket); // this는 ChatServer
					}
				} catch (Exception e) {
				}
			});
			thread.start();
		}
		
		public void UserJoin(SocketClient socket, String id, String pwd, String name, String phone, String email,
											String nickname, String address) {
			String sql = "insert into users (user_id, user_pwd, user_name, user_phone, user_email, user_nickname, user_address) " +
					"values(?, ?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();
			try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				pstmt.setString(3, name);
				pstmt.setString(4, phone);
				pstmt.setString(5, email);
				pstmt.setString(6, nickname);
				pstmt.setString(7, address);
				
				pstmt.executeUpdate();
				pstmt.close();
				
				jsonObject.put("command",	"userJoin");
				jsonObject.put("message", "회원가입 성공");
				json = jsonObject.toString();
				socket.send(json);
				
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put("command", "userJoin");
				jsonObject.put("message", "회원가입 실패");
				json = jsonObject.toString();
				socket.send(json);
				
				
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void UserInforeceive(SocketClient socket, String id, String pwd) {
			String sql = "select user_Id, user_Pwd from users where user_Id='" + id + 
								"' and user_pwd = '" + pwd + "'";
			Connection conn = ConnectionProvider.getConnection();
			try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				jsonObject.put("command", "loginReply");
				jsonObject.put("message", "로그인 되었습니다");
				jsonObject.put("id", id);
				jsonObject.put("pwd", pwd);  //비번 저장 필요??
				json = jsonObject.toString();
				socket.send(json);
				//ID, Pwd 구분하지 않음. db 331page
				
			}  else  {
				jsonObject.put("command", "loginReply");
				jsonObject.put("message", "로그인 실패");
				json = jsonObject.toString();
				socket.send(json);
			} 
			pstmt.close();
			rs.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			
		}
		
		
		public void UserDelete(SocketClient socket, String id, String pwd) {
			String sql = "select user_id, user_pwd from users where user_id =? ";
			Connection conn = ConnectionProvider.getConnection();
			try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			jsonObject.put("command", "userDelete");
			jsonObject.put("message", "조회");
			json = jsonObject.toString();
			socket.send(json);
			
			ResultSet rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				String password = rs.getString("user_pwd");
				String Id = rs.getString("user_id");
				if (pwd.equals(password) && id.equals(Id)) {
					sql = "delete from users where user_id = ? " +  "and user_pwd = ? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setString(2, pwd);
					pstmt.executeUpdate();
					pstmt.close();
					jsonObject.put("command", "userDelete");
					jsonObject.put("message", id + "삭제 완료");
					json = jsonObject.toString();
					socket.send(json);
				} else {
					jsonObject.put("command", "userDelete");
					jsonObject.put("message", "비밀번호가 틀립니다.");
					json = jsonObject.toString();
					socket.send(json);
				}

			} else {
				jsonObject.put("command", "userDelete");
				jsonObject.put("message", id + "가 존재하지 않습니다");
				json = jsonObject.toString();
				socket.send(json);
			}
			

			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		
		
		
		public static void main(String[] args) {
			try {
				ChatServer chatServer = new ChatServer();
				chatServer.start();
				
				
				
			} catch (IOException e) {
				System.out.println("[서버] " + e.getMessage());
			}
		}

}
