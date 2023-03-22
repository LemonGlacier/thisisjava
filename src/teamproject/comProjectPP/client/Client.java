package teamproject.comProjectPP.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

import teamproject.comProjectPP.DTO.Basket_Detail;
import teamproject.comProjectPP.DTO.Product;
import teamproject.comProjectPP.DTO.Users;

public class Client {
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	String chatName;
	Users user = new Users();
	Basket_Detail basket_detail = new Basket_Detail();
	JSONObject jsonObject = null;
	
	// 메소드
	public void connect() throws IOException {
		socket = new Socket("localhost", 50001);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		System.out.println("[클라이언트] 서버에 연결됨");
	}

	

	public void send(String json) throws IOException {
		dos.writeUTF(json);
		dos.flush();
	}

	public void unconnect() throws IOException {
		socket.close();
	}
	
	public String receive() {
		try {
			return dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
	
}
