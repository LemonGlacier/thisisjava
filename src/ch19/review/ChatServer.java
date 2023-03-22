package ch19.review;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

public class ChatServer {  //채팅 서버 실행 클래스: 클라이언트 연결 요청 수락 및 통신용 SocketClient 생성
	//필드
	ServerSocket serverSocket;               //TCP
	ExecutorService threadPool = Executors.newFixedThreadPool(100);   //스레드풀 생성:동시 채팅
	Map<String, SocketClient> chatRoom = Collections.synchronizedMap(new HashMap<>());   //통신용 SocketClient 관리
	                                //= new Hashtable<>();                     //멀티 스레드 환경:동기화
	
	//메소드: 서버 시작
	public void start() throws IOException{
		serverSocket = new ServerSocket(50001);    //ServerSocket 객체 생성 후 50001에 바인딩
		System.out.println("[서버] 시작");
		
		Thread thread = new Thread(()->{      //작업 스레드가 처리할 Runnable 람다식으로 제공
			try {
				while(true) {
					Socket socket = serverSocket.accept();   //통신용 Socket(클라이언트 주소 갖고 있음)
					SocketClient sc = new SocketClient(this, socket);	//this = chatServer				
				}				
			} catch(IOException e) {				
			}
		});      //작업 스레드 생성
		thread.start();		
	}
	
	//메소드: 클라이언트 연결 시 SocketClient 생성 및 추가
	public void addSocketClient(SocketClient socketClient) {
		String key = socketClient.chatName + "@" + socketClient.clientIp;
		chatRoom.put(key, socketClient);
		System.out.println("in: " + key);
		System.out.println("현재 채팅자 수: " + chatRoom.size() + "\n");
	}
	
	//메소드: 클라이언트 연결 종료 시 SocketClient 제거
	public void removeSocketClient(SocketClient socketClient) {
		String key = socketClient.chatName + "@" + socketClient.clientIp;
		chatRoom.remove(key);
		System.out.println("out: " + key);
		System.out.println("현재 채팅자 수: " + chatRoom.size() + "\n");
	}
	
	//메소드: 모든 클라이언트에서 메시지 보냄
	public void sendToAll(SocketClient sender, String message) {
		JSONObject root = new JSONObject();
		root.put("clientIp", sender.clientIp);
		root.put("chatName", sender.chatName);
		root.put("message", message);
		String json = root.toString();                 //메시지 보내는 사람 정보
		
		Collection<SocketClient> socketClients = chatRoom.values();  //Map<String, SocketClient> chatRoom
		for(SocketClient sc: socketClients) {
			if(sc == sender) continue;            //보내는 사람은 넘어감
			sc.send(json);
		}
	}
	
	//메소드: 서버 종료
	public void stop() {
		try{
			serverSocket.close();
			threadPool.shutdownNow();
			chatRoom.values().stream().forEach(sc->sc.close());    //chatRoom의 SocketClient 닫음
			System.out.println("[서버] 종료");
		} catch(IOException e1) {
		}
	}
	
	public static void main(String[] args) {
		try {
			ChatServer chatServer = new ChatServer();          //ChatServer객체 생성: 채팅 서버 시작
			chatServer.start();
			
			System.out.println("----------------------------------------------");
			System.out.println("서버를 종료하려면 q 입력 후 Enter");
			System.out.println("----------------------------------------------");
			
			Scanner scanner = new Scanner(System.in);
			while(true) {
				String key = scanner.nextLine();
				if(key.equals("q")) break;            //q 입력하면 나감
			}
			scanner.close();
			chatServer.stop();
			
		} catch(IOException e){
			System.out.println("[서버] " + e.getMessage());
		}
	}
}
