package Team5_com.DTO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

public class ChatServer {
	// 필드
	ServerSocket serverSocket;
	ExecutorService threadPool = Executors.newFixedThreadPool(100);
	Map<String, SocketClient> chatRoom = Collections.synchronizedMap(new HashMap<>());
	private Connection conn;

	// 메소드
	
	//일단 메인메뉴에서 게시판을 선택했다면
	/*private void board() {
		
		System.out.println("------------------------------------------------------");
		System.out.println("1. 자유 게시판 | 2. 리뷰 게시판 | 3. 문의 게시판 | 4. 메인 페이지");
		System.out.println("------------------------------------------------------");
		System.out.print("선택>");
		Scanner scanner = new Scanner(System.in);   
		String key = scanner.nextLine();
		switch (key) {
		case "1" -> startFB();
		case "2" -> startRB();
		case "3" -> startQB();
		default -> //메인페이지
		}
		
		
	}*/
	
	public void getFB(SocketClient user, int selectP) {
		try {                                                   //이거 아예 sql문에 where Free_Bno<selectP*5 이런 식으로 넣을까
			String sql = "select Free_Bno, Free_Btitle, Free_Bcontent, Free_Date, Free_Comment_Num, Users_User_Id from free_board order by free_bno desc;";  //매개변수화된 select문  //내림차순
			String countSql = "select count(*) from free_board;";
			PreparedStatement pstmt = conn.prepareStatement(sql);                                  //컴파일된 sql문 인터페이스
			ResultSet rs = pstmt.executeQuery();                                         //select문에 기술된 컬럼으로 구성된 행 집합
			ResultSet crs = conn.prepareStatement(countSql).executeQuery();
			
			//**페이징 처리
			//일단 int select=1 하고 출력을 (page-1)*rowsPerPage+1 ~ page*rowsPerPage 이런 거하고 전체 페이지수 구해서 몇 페이지까지 있는지 나오게 하기
			//int selectP = 1;
			//**일단 한 페이지 5개 출력(보내기)
			while((selectP-1)*5<=selectP*5) {                                     
				JSONObject root = new JSONObject();                         //데이터 행을 읽고 JSON 으로 저장
				root.put("Free_Bno", rs.getString("Free_Bno"));                    //free_bno컬럼 값
				root.put("Free_Btitle", rs.getString("Free_Btitle"));
				root.put("Free_Bcontent", rs.getString("Free_Bcontent"));
				root.put("Free_Date", rs.getDate("Free_Date"));
				root.put("Free_Comment_Num", rs.getInt("Free_Comment_Num"));              
				root.put("User_Id", rs.getString("User_Id"));    
				root.put("Whole_Num", crs.getInt("count(*)"));                //**굳이 5번 보낼 필요가 없긴한데
				
				//JSONObject(toString으로 변환해서) 보내기
				//String json = root.toString();
				//Socket socket = serverSocket.accept();
				//SocketClient user = new SocketClient(this, socket);
				user.send(root.toString());                               //**send() , SocketClient 만들기 확인			
				
				//클라이언트가 JSONObject를 받아서 출력           //**Client 클래스
				//try { while(rs.next) {  
				//Client.receiveFB();
				/*System.out.printf("%-6s%-12s%-16s%-40s \n",
						fboard.get("Free_Bno"),
						fboard.getFree_Btitle(),
						fboard.getFree_Bcontent(),
						fboard.getFree_Date(),
						fboard.getFree_Comment_Num(),
						fboard.getUser_Id()
						);*/
				
				//**여기다 번호 선택을 넣어야하나??
			}                                                   //커서:afterLast     //**페이징 처리
			rs.close();                                        //메모리 해제
			pstmt.close();                                     //PreparedStatement 닫기
			
		}catch(SQLException e) {
			e.printStackTrace();
			exit();
		}
	}
	

	
	private void startFB(SocketClient user) {
		System.out.println("[자유 게시판]");
		System.out.println("------------------------------------------------------");             //**이거 출력도 클라이언트에서 할까
		System.out.println("");
		System.out.printf("%-6s%-12s%-40s%-16s\n","번호","작성자", "제목", "날짜");                    //**바이트 수만큼 글자수를 넣어 줄까 //**댓글 표시 해줄까
		System.out.println("------------------------------------------------------");
		//System.out.print("선택>");
		//Scanner scanner = new Scanner(System.in);   ///**
		//String key = scanner.nextLine();
		//^^ServerSocket serverSocket = new ServerSocket(50001);
		
		try {
			String sql = "select Free_Bno, Free_Btitle, Free_Bcontent, Free_Date, Free_Comment_Num, User_Id from free_board order by free_bno desc";  //매개변수화된 select문  //내림차순
			PreparedStatement pstmt = conn.prepareStatement(sql);                                  //컴파일된 sql문 인터페이스
			ResultSet rs = pstmt.executeQuery();                                         //select문에 기술된 컬럼으로 구성된 행 집합
			while(rs.next()) {                                      //데이터 행 여러 개(커서:beforeFirst>>...)
				JSONObject root = new JSONObject();                         //데이터 행을 읽고 JSON 으로 저장
				root.put("Free_Bno", rs.getString("Free_Bno"));                    //free_bno컬럼 값
				root.put("Free_Btitle", rs.getString("Free_Btitle"));
				root.put("Free_Bcontent", rs.getString("Free_Bcontent"));
				root.put("Free_Date", rs.getDate("Free_Date"));
				root.put("Free_Comment_Num", rs.getInt("Free_Comment_Num"));              
				root.put("User_Id", rs.getString("User_Id"));                //**이거 보내는 것도 페이징 고려해서 보내야 하나
				
				//JSONObject(toString으로 변환해서) 보내기
				//String json = fboard.toString();
				//Socket socket = serverSocket.accept();
				//SocketClient user = new SocketClient(this, socket);
				user.send(root.toString());                               //**send() , SocketClient 만들기 확인			
				
				//클라이언트가 JSONObject를 받아서 출력           //**Client 클래스
				//try { while(rs.next) {  
				//^^Client.receiveFB();
				/*System.out.printf("%-6s%-12s%-16s%-40s \n",
						fboard.get("Free_Bno"),
						fboard.getFree_Btitle(),
						fboard.getFree_Bcontent(),
						fboard.getFree_Date(),
						fboard.getFree_Comment_Num(),
						fboard.getUser_Id()
						);*/
				
				//**여기다 번호 선택을 넣어야하나??
			}                                                   //커서:afterLast     //**페이징 처리
			rs.close();                                        //메모리 해제
			pstmt.close();                                     //PreparedStatement 닫기
			
		}catch(SQLException e) {
			e.printStackTrace();
			exit();
		}
		
		//mainMenu();   //**메인메뉴 가기
	}



	
	
	












	//---------------------------------------------------------------------------------
	private void getConnect() {
		// db연결
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@kosa402.iptime.org:50051/orcl", "team5", "oracle");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exit();
		}
	}

	public void start() throws IOException {
		serverSocket = new ServerSocket(50001);
		System.out.println("[서버] 시작됨");
		getConnect();
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

	private void exit() {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {

			}

		}
		System.exit(0);
	}

	public void addSocketClient(SocketClient socketClient) {
		String key = socketClient.chatName + "@" + socketClient.clientIp;
		chatRoom.put(key, socketClient);
		System.out.println("입장: " + key);
		System.out.println("현재 채팅자 수: " + chatRoom.size() + "\n");
	}

	public void removeSocketClient(SocketClient socketClient) {
		String key = socketClient.chatName + "@" + socketClient.clientIp;
		chatRoom.remove(key);
		System.out.println("나감: " + key);
		System.out.println("현재 채팅자 수: " + chatRoom.size() + "\n");
	}

	public void getList(SocketClient user) {
		String sql = "select PRODUCT_ID,PRODUCT_NAME from product";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			
			
			JSONObject root = new JSONObject();
			root.put("PRODUCT_ID", rs.getString("PRODUCT_ID"));
			root.put("PRODUCT_NAME", rs.getString("PRODUCT_NAME"));
			
			String json = root.toString();

			user.send(json);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			exit();
		}
		

	}

	public void stop() {
		try {
			serverSocket.close();
			threadPool.shutdownNow();
			chatRoom.values().stream().forEach(sc -> sc.close());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("[서버] 종료됨");
	}

	public static void main(String[] args) {

		try {
			ChatServer chatServer = new ChatServer();
			chatServer.start();
			System.out.println("-------------------------------------------------");
			System.out.println("서버를 종료하려면 q 또는 Q를 입력하고 Enter키를 입력하세요.");
			System.out.println("-------------------------------------------------");

			// TCP서버 시작

			// 키보드 입력
			Scanner scanner = new Scanner(System.in);
			while (true) {
				String key = scanner.nextLine();
				if (key.toLowerCase().equals("q")) {
					break;
				}
			}
			scanner.close();
			// TCP 서버 종료
			chatServer.stop();
		} catch (IOException e) {
			System.out.println("[서버] " + e.getMessage());
		}
	}
}
