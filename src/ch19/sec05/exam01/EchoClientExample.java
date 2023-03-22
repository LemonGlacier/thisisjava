package ch19.sec05.exam01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClientExample {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 50001);
			System.out.println("[클라이언트] 연결 성공");
			
			//--------------------------------------
			//데이터 보내기
			String sendMessage = "나는 자바가 좋아~";
			OutputStream os = socket.getOutputStream();
			byte[] bytes = sendMessage.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			System.out.println("[클라이언트] 데이터 보냄: " + sendMessage);
			
			//데이터 받기
			InputStream is = socket.getInputStream();
			bytes = new byte[1024];
			int readByteCount= is.read(bytes);
			String receiveMessage = new String(bytes, 0 , readByteCount, "UTF-8");
			System.out.println("[클라이언트] 데이터 받음: " + receiveMessage);
			
			//--------------------------------------
			
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
