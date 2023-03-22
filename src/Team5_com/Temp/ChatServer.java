package Team5_com.Temp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

import ch20.oracle.sec12.Board;

public class ChatServer {
	// 필드
	ServerSocket serverSocket;
	ExecutorService threadPool = Executors.newFixedThreadPool(100);
	Map<String, SocketClient> chatRoom = Collections.synchronizedMap(new HashMap<>());
	private Connection conn;

	// 메소드
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
