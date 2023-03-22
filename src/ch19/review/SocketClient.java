package ch19.review;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.json.JSONObject;

public class SocketClient {         //클라이언트와 1:1통신
	//필드
	ChatServer chatServer;          //ChatServer() 메소드 호출
	Socket socket;                  //연결 끊기
	DataInputStream dis;
	DataOutputStream dos;
	String clientIp;
	String chatName;
	
	//생성자
	public SocketClient(ChatServer chatServer, Socket socket) {
		try {
			this.chatServer=chatServer;
			this.socket=socket;
			this.dis=new DataInputStream(socket.getInputStream());
			this.dos=new DataOutputStream(socket.getOutputStream());
			InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
			this.clientIp = isa.getHostName();
			receive();
			
		}catch(IOException e) {			
		}
	}
	//JSON 받기
	public void receive() {
		chatServer.threadPool.execute(()->{
			try {
				while(true) {
					String receiveJson = dis.readUTF();            //JSON 읽기 
					
					JSONObject jsonObject = new JSONObject(receiveJson);   //JSONObject로 파싱
					String command = jsonObject.getString("command");     //command 값 얻어냄
					
					switch(command) {
					case "incoming":                                     
						this.chatName = jsonObject.getString("data");
						chatServer.sendToAll(this, "들어옴");
						chatServer.addSocketClient(this);          //chatRoom에 SocketClient 추가
						break;
					case "message":
						String message = jsonObject.getString("data");
						chatServer.sendToAll(this, message);
						break;
						
					}					
				}
			} catch(IOException e) {                   //클라이언트가 채팅을 종료하면 readUTF()에서 IOException 발생
				chatServer.sendToAll(this, "나감");
				chatServer.removeSocketClient(this);        //chatRoom에 SocketClient 제거
			}
		});
	}
	
	//메소드: JSON 보내기
	public void send(String json) {        //ChatServer의 sendToAll() 메소드에서 호출
		try {
			dos.writeUTF(json);
			dos.flush();		
		} catch (IOException e) {	
		}
	}
	
	//메소드: 연결 종료
	public void close() {                //클라이언트와 연결 끊음 //ChatServer stop() 메소드에서 호출
		try {
			socket.close();
		} catch(Exception e) {
		}
	}

}
