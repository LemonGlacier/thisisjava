package ch19.sec07;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

public class ChatServer {
	//필드
	ServerSocket serverSocket;   //private 붙여도 됨
	ExecutorService threadPool = Executors.newFixedThreadPool(100);   //인스턴스 필드
	Map<String, SocketClient> chatRoom = new Hashtable<>();  //멀티 스레드 환경
	                                //=Collections.synchronizedMap(new HashMap<>());
	
	//메소드
	public void start() throws IOException {
		serverSocket = new ServerSocket(50001);
		System.out.println("[서버] 시작됨");
		
		Thread thread = new Thread(()-> {
			try {
				while(true) {
				Socket socket = serverSocket.accept();
				SocketClient sc = new SocketClient(this, socket);   //바깥쪽에 있는 서버에 대한 참조를 매개값으로 받음			
				}
			} catch(Exception e) {
			}
		});
		thread.start();
	}
	
	public void addSocketClient(SocketClient socketClient) {
		String key = socketClient.chatName + "@" + socketClient.clientIP;
		chatRoom.put(key, socketClient);
		System.out.println("입장: " + key);
		System.out.println("현재 채팅자 수: " + chatRoom.size() + "\n");
	}
	
	public void removeSocketClient(SocketClient socketClient) {
		String key = socketClient.chatName + "@" + socketClient.clientIP;
		chatRoom.remove(key);
		System.out.println("나감: " + key);
		System.out.println("현재 채팅자 수: " + chatRoom.size() + "\n");
	}
	
	public void sendToAll(SocketClient sender, String message) {
		JSONObject root = new JSONObject();
		root.put("clientIP", sender.clientIP);
		root.put("chatName", sender.chatName);
		root.put("message", message);
		String json = root.toString();
		
		Collection<SocketClient> socketClients = chatRoom.values();
		for(SocketClient sc : socketClients) {
			if(sc==sender) continue;  //같은 객체냐(동등 아님)
			sc.send(json);
		}
	}
	
	public void stop() {
		try {
			serverSocket.close();
			threadPool.shutdownNow();
			chatRoom.values().stream().forEach(sc->sc.close());
			
			/*Collection<SocketClient> socketClients = chatRoom.values();
			for(SocketClient sc : socketClients) {
				sc.close);
			}*/
		} catch (IOException e) {
		}
		System.out.println("[서버] 종료");
	}

	public static void main(String[] args) {
		try {
			ChatServer chatServer = new ChatServer();
			chatServer.start();
			System.out.println("------------------------------------------------");
			System.out.println("서버를 종료하려면 q 또는 Q를 입력하고 Enter 키를 입력하세요.");
			System.out.println("------------------------------------------------");

			//키보드 입력
			Scanner scanner = new Scanner(System.in);
			while(true) {
				String key = scanner.nextLine();
				if(key.toLowerCase().equals("q")) {
					break;
				}
			}
			scanner.close();
			
			//서버 종료
			chatServer.stop();
						
		} catch (IOException e) {
			System.out.println("[서버] " + e.getMessage());
		}

	}

}
