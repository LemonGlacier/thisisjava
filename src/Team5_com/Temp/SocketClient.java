package Team5_com.Temp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.json.JSONObject;

public class SocketClient {
	ChatServer chatServer;
	Socket socket;

	DataInputStream dis;
	DataOutputStream dos;

	String chatName;
	String clientIp;

	public SocketClient(ChatServer chatServer, Socket socket) {
		this.chatServer = chatServer;
		this.socket = socket;
		try {
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
			InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
			this.clientIp = isa.getHostName();
			receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void receive() {

		chatServer.threadPool.execute(() -> {
			try {
				while (true) {
					//{"command" : "incoming","data":"chatName"}
					//{"command": "message","data":"xxxx"}
					String receiveJson = dis.readUTF();//연결된 상태에서 대기
					JSONObject jsonObject = new JSONObject(receiveJson);
					String command = jsonObject.getString("command");
					switch(command) {
					case "incoming":
						//this.chatName=jsonObject.getString("data");
						//chatServer.sendToAll(this, "들어오셨습니다.");
						chatServer.getList(this);
						break;
					case "message":
						String message = jsonObject.getString("data");
						//chatServer.sendToAll(this, message);
						chatServer.getList(this);
						break;
					}
				}
			} catch (IOException e) {
				//연결이 끊겼을때
				//chatServer.sendToAll(this, "나가셨습니다.");
				chatServer.removeSocketClient(this);
			}
		});

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
