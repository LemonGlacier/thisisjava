package Team5_com.ClientCase;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

public class UserFunction {
	Socket socket;
	Scanner sc = new Scanner(System.in);
	
	
	void login(ChatClient chatclient) {

		try {

		Users users = new Users();
		JSONObject jsonObject = new JSONObject();
		System.out.println();
		System.out.println("[로그인 ID] : ");
		users.setUser_Id(sc.nextLine());
		System.out.println("[비밀번호 pwd] : ");
		users.setUser_Pwd(sc.nextLine());
		
		jsonObject.put("command", "login");
		jsonObject.put("id", users.getUser_Id());
		jsonObject.put("pwd", users.getUser_Pwd());
		
		String json = jsonObject.toString();
		
		chatclient.send(json);
		System.out.println("로그인 대기");
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		sc.close();
	}
	
	void Join(ChatClient chatclient ) {
		try {
			Users users = new Users();
			JSONObject jsonObject = new JSONObject();
			
			System.out.println();
			System.out.println("[회원가입]");
			System.out.println("필수입력_[신규 ID] : ");
			users.setUser_Id(sc.nextLine());
			System.out.println("필수입력_[패스워드] : ");
			users.setUser_Pwd(sc.nextLine());
			System.out.println("필수입력_[이름] : ");
			users.setUser_Name(sc.nextLine());
			System.out.println("[전화번호] : " );
			users.setUser_Phone(sc.nextLine());
			System.out.println("[이메일] : ");
			users.setUser_Email(sc.nextLine());
			System.out.println("필수입력_[닉네임] : ");
			users.setUser_Nickname(sc.nextLine());
			System.out.println("필수입력_[주소] :");
			users.setUser_Address(sc.nextLine());
			
			jsonObject.put("command", "join");
			jsonObject.put("id", users.getUser_Id());
			jsonObject.put("pwd", users.getUser_Pwd());
			jsonObject.put("name", users.getUser_Name());
			jsonObject.put("phone", users.getUser_Phone());
			jsonObject.put("email", users.getUser_Email());
			jsonObject.put("nickname", users.getUser_Nickname());
			jsonObject.put("address", users.getUser_Address());
			
			String json = jsonObject.toString();
			chatclient.send(json);
			System.out.println("가입 정보 전송 완료");
			
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	void Delte(ChatClient chatclient) {
		Users users = new Users();
		JSONObject jsonObject = new JSONObject();
		
		System.out.println();
		System.out.println("[회원탈퇴]");
		System.out.println("필수입력_[탈퇴할 ID] : ");
		users.setUser_Id(sc.nextLine());
		System.out.println("필수입력_[패스워드 확인] : ");
		users.setUser_Pwd(sc.nextLine());
		
		jsonObject.put("command", "userDelete");
		jsonObject.put("id", users.getUser_Id());
		jsonObject.put("pwd", users.getUser_Pwd());
		
		String json = jsonObject.toString();
		try {
			chatclient.send(json);
			System.out.println("회원 탈퇴 요청 완료");
			
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}

