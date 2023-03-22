package Team5_com.ClientCase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.json.JSONObject;

public class SocketClient {
	ChatServer chatServer;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	static String LogID;
	static String LogPW;
	
	Users users = new Users();
	
	public SocketClient(ChatServer chatServer, Socket socket) {
		this.chatServer = chatServer;
		this.socket = socket;
		try {
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
			receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//------------------------------------------------------------------------------
	public void receive() {
		chatServer.threadPool.execute(() -> {
			try {
				while (true) {                             
					String receiveJson = dis.readUTF();//연결된 상태에서 대기
					JSONObject jsonObject = new JSONObject(receiveJson);
					String command = jsonObject.getString("command");
					
					switch(command) {
					
					case "login" : 
						chatServer.UserInforeceive(this, jsonObject.getString("id"), jsonObject.getString("pwd"));	
						users.setUser_Id(jsonObject.getString("id"));
						users.setUser_Pwd(jsonObject.getString("pwd"));
						
						LogID = users.getUser_Id();
						LogPW= users.getUser_Pwd();
						
						
						
						break;
					case "join" : 
						chatServer.UserJoin(this, jsonObject.getString("id"), jsonObject.getString("pwd"),
								jsonObject.getString("name"), jsonObject.getString("phone"), jsonObject.getString("email"),
								jsonObject.getString("nickname"), jsonObject.getString("address"));
						break;
					
					case "userDelete" :
						chatServer.UserDelete(this, jsonObject.getString("id"), jsonObject.getString("pwd"));
						break;
						
						
					}
					
				}
				
			} catch (IOException e) {
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
