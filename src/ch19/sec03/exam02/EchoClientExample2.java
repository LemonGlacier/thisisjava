package ch19.sec03.exam02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClientExample2 {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 50001);
			System.out.println("[클라이언트] 연결 성공");
			
			//--------------------------------------
			//데이터 보내기
			String sendMessage = "나는 자바가 좋아~";
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(sendMessage);
			dos.flush();
			System.out.println("[클라이언트] 데이터 보냄: " + sendMessage);
			
			//데이터 받기
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			String receiveMessage = dis.readUTF();
			System.out.println("[클라이언트] 데이터 받음: " + receiveMessage);
			
			//--------------------------------------
			//연결 끊기
			socket.close();
			System.out.println("[클라이언트] 연결 끊음");
		} catch (UnknownHostException e) {
			//IP 또는 도메인 표기 방법 잘못
			System.out.println("UnknownHostException: "+ e.toString());
		} catch (IOException e) {
			//IP 또는 Port 번호 존재하지 않음
			System.out.println("IOException: " + e.toString());
		}

	}

}
