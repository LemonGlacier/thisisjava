package Team5_com.ClientCase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;


public class ChatClient {
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	String chatName;
	
	
	// 메소드
	public void connect() throws IOException {
		socket = new Socket("localhost", 50001);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		System.out.println("[클라이언트] 서버에 연결됨");
	}

	public JSONObject receive() {
		JSONObject jsonObject = null;
			try {
				String json = dis.readUTF();
				jsonObject = new JSONObject(json);
				String command = jsonObject.getString("command");
				
				switch(command) {
				
				case "loginReply" : 
				System.out.println("<" + jsonObject.getString("message") + ">");
				SocketClient.LogID = jsonObject.getString("id");
				SocketClient.LogPW= jsonObject.getString("pwd");  //비번 저장 필요??
				break;
				
				case "userJoin" :
				System.out.println("<" + jsonObject.getString("message") + ">");
				
				case "userDelete" :
				System.out.println("<" + jsonObject.getString("message") + ">");
				
				
				}
			} catch (IOException e) {
				System.out.println("[클라이언트] 서버 연결끊김");
				System.exit(0);
			}
			
			return jsonObject;
	}
	
	public void send(String json)throws IOException{
		dos.writeUTF(json);
		dos.flush();
	}
	
	
	
	public void unconnect() throws IOException {
		socket.close();
	}
	
	
	
	public static void main(String[] args) {
		UserFunction userFunction = new UserFunction(); 
		
		try {
			ChatClient chatClient = new ChatClient();
			chatClient.connect();
			Scanner sc = new Scanner(System.in);
			
			System.out.println("[1. 회원 로그인] | [2. 회원가입] | [3.회원탈퇴]");
			System.out.print("입력 > : ");
			String menuNo = sc.nextLine();
			
			if(menuNo.equals("1") ) {
			userFunction = new UserFunction(); 
			userFunction.login(chatClient);
			chatClient.receive();
			
			System.out.println(SocketClient.LogID);
			System.out.println(SocketClient.LogPW);  //비번 출력 필요??
			
			
			//회원가입.
			} else if (menuNo.equals("2")) {
			userFunction = new UserFunction(); 
			userFunction.Join(chatClient);
			chatClient.receive();
			 
			//회원탈퇴
			} else if(menuNo.equals("3")) {
			userFunction = new UserFunction();
			userFunction.Delte(chatClient);
			chatClient.receive();
			
			}
			 
			 
			 
			 
			 
			 
		}catch(Exception e) {
			e.getMessage();
			System.out.println("[클라이언트] 서버 연결 안됨");
		}
		System.out.println("클라이언트 종료");
	}
}
