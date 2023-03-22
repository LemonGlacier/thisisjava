package ch19.review;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

public class ChatClient {
	//필드
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	String chatName;
	
	//메소드: 서버 연결
	public void connect() throws IOException {
		socket = new Socket("localhost", 50001);                  //채팅 서버에 연결 요청 후 Socket을 필드에 저장
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		System.out.println("[클라이언트] 서버에 연결됨");
	}
	
	//메소드: JSON 받기
	public void receive() {
		Thread thread = new Thread(()->{                              //작업 스레드
			try {
				while(true) {
					String json = dis.readUTF();                      //JSON 읽기 
					JSONObject root = new JSONObject(json);          //JSONObject로 파싱
					String clientIp = root.getString("clientIp");
					String chatName = root.getString("chatName");
					String message = root.getString("message");
					System.out.println("<" + chatName + "@" + clientIp + ">" + message);
				}				
			} catch(Exception e1) {
				System.out.println("[클라이언트] 서버 연결 끊김");        //서버와 통신 끊어지면 dis.UTF()에서 IOException 발생
				System.exit(0);
			}
		});
		thread.start();                                           //스레드 시작
	}
	
	//메소드: 서버로 JSON 보내기
	public void send(String json) throws IOException {
		dos.writeUTF(json);
		dos.flush();
	}
	
	//메소드: 서버 연결 종료
	public void unconnect() throws IOException {
		socket.close();
	}

	public static void main(String[] args) {
		try {
			ChatClient chatClient = new ChatClient();               //채팅 클라이언트 시작 //ChatClient 객체 생성
			chatClient.connect();                                   //서버와 연결
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("대화명 입력: ");
			chatClient.chatName = scanner.nextLine();              //대화명 입력 받기
			
			JSONObject jsonObject = new JSONObject();              //서버에 JSON 메시지 보내기
			jsonObject.put("command", "incoming");
			jsonObject.put("data", chatClient.chatName);
			String json = jsonObject.toString();
			chatClient.send(json);
			
			chatClient.receive();                                  //채팅 서버에서 보내는 메시지 받기
			
			System.out.println("----------------------------------------------");
			System.out.println("보낼 메시지 입력 후 Enter");
			System.out.println("채팅을 종료하려면 q 입력 후 Enter");
			System.out.println("----------------------------------------------");
			
			while(true) {
				String message = scanner.nextLine();
				if(message.toLowerCase().equals("q")) break;
				else {                                           //JSON 생성해 서버로 보냄
					jsonObject = new JSONObject();
					jsonObject.put("command", "message");
					jsonObject.put("data", message);
					json = jsonObject.toString();
					chatClient.send(json);
				}
			}
			scanner.close();
			chatClient.unconnect();
			
		} catch (IOException e) {
			System.out.println("[클라이언트] 서버 연결 안됨");			
		}

	}

}
