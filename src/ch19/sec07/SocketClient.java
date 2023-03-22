package ch19.sec07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.json.JSONObject;

public class SocketClient {
	//필드
	ChatServer chatServer;
	Socket socket;
	String clientIP;
	String chatName;
	DataInputStream dis;
	DataOutputStream dos;
	
	//생성자
	public SocketClient(ChatServer chatServer, Socket socket) {		
		try {
			this.chatServer = chatServer;
			this.socket = socket;
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
			InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
			this.clientIP = isa.getHostName();
			receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void receive() {
		//스레드 생성해 받을 준비:스레드 풀
		chatServer.threadPool.execute(()->{    //작업 큐에 작업 객체 
			try {                                  //dis.readUTF() 예외
				while(true) {
					//{"command" : "xxx", "data" : "chatName", ... }
					String receiveJson = dis.readUTF();         //연결이 끊기면 예외  
					JSONObject jsonObject = new JSONObject(receiveJson);
					String command = jsonObject.getString("command");
					
					switch(command) {
					//{"command" : "incoming", "data" : "chatName", ... }
					case "incoming": 
						this.chatName = jsonObject.getString("data");
						chatServer.sendToAll(this, " 들어오셨습니다.");   //this=SocketClient(클래스) 객체
						chatServer.addSocketClient(this);
						break;
					//{"command" : "message", "data" : "content", ... }
					case "message": 
						String message = jsonObject.getString("data");
						chatServer.sendToAll(this, message);
						break;
					}
				}
			} catch(IOException e) {             //dis.readUTF() 예외-연결이 끊겼을 때
				chatServer.sendToAll(this, "나가셨습니다.");
				chatServer.removeSocketClient(this);
			}
		});
		
	}
	
	public void send(String json) {
		try {
			dos.writeUTF(json);
			dos.flush();
		} catch (IOException e) {
		}
		
	}
	
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
		}
		
	}

}
