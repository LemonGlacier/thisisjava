package teamproject.comProjectPP.View;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;

import teamproject.comProjectPP.DTO.Basket;
import teamproject.comProjectPP.client.Basket_Function;
import teamproject.comProjectPP.client.Client;
import teamproject.comProjectPP.client.UserFunction;

public class Menu_View {
	Scanner sc = new Scanner(System.in);
	UserFunction userFunction = new UserFunction(); 
	Basket basket = new Basket();
	Basket_Function basket_Function = new Basket_Function();
	
	boolean flag;
	String selectNumber;
	Client client;
	JSONObject JsonObject;
	
	public Menu_View(Client client) {
		this.client = client;
	}
	
	public void MainMenu() throws IOException {
		
		System.out.println();
		System.out.println("[1. 상품목록] | [2. 회원 로그인 / 가입] | [3. 종료] ");
		System.out.println("---------------------------------------------------");
		System.out.print("메뉴 선택 > :");
		selectNumber = sc.nextLine(	);
		if(selectNumber.equals("1")) {
			
		
		} else if (selectNumber.equals("2")) {
			User();
		} else if (selectNumber.equals("3")) {
			client.unconnect();
		} else {
			MainMenu();
		}
		
		}
		
		
		
		
		
	public void User() throws IOException {
		System.out.println();
		System.out.println("[1. 회원 로그인] | [2. 회원 가입] | [3. 메인 메뉴]");
		System.out.println("------------------------------------");
		System.out.print("메뉴 선택 > :");
		selectNumber = sc.nextLine();
		
		if(selectNumber.equals("1")) {
			flag = true;
			while(flag) {
				userFunction.login(client);
				JsonObject= new JSONObject(client.receive());
				String step = JsonObject.getString("step");
				
				if(step.equals("manager")) {
					System.out.println("[Manager Menu]");
					ManagerMenu();
				} else if(step.equals("normal")) {
					System.out.println("[Login Complete]");
					LoginMenu();
				} else if(step.equals("fail")) {
					System.out.println(JsonObject.getString("message"));
					User();
				}
			}
			
		} else if(selectNumber.equals("2")) {	
			flag = true;
			userFunction.idConfirm(client);
			JsonObject= new JSONObject(client.receive());
			String step = JsonObject.getString("step");
			
			while(flag) {
				if (step.equals("next")) {
					break;
				} else {
					userFunction.idConfirm(client);
				}
			}
			userFunction.Join(client);
			JsonObject= new JSONObject(client.receive());
			JsonObject.getString("message");
			
			basket_Function.CreateBasket(client);
			JsonObject= new JSONObject(client.receive());
			JsonObject.getString("message");
			
			
			LoginMenu();
			
			//메뉴 선텍 오류
		} else if(selectNumber.equals("3")) {
			MainMenu();
		} else {
			System.out.println("메뉴 선택 오류");
			User();
		}
	}
	
	public void LoginMenu() throws IOException {
		System.out.println();
		System.out.println("[1. 마이 페이지] | [2. 로그아웃] | [3. 장바구니] | [4. 게시판] | [5. 종료]");
		System.out.print("선택 > : ");
		selectNumber = sc.nextLine();
		if( selectNumber.equals("1" )) {
			
			userFunction.check(client);
			JsonObject= new JSONObject(client.receive());
			String step = JsonObject.getString("step");
			if(step.equals("true")) {
				System.out.println(JsonObject.getString("message"));
			} else {
				System.out.println(JsonObject.getString("message"));
			}
			
			
			MyPage();
			
		} else if( selectNumber.equals("2")) {
			userFunction.UserLogOut(client);
			System.out.println("메인 페이지로 돌아갑니다.");
			MainMenu();
			
			
		} else if (selectNumber.equals("3")) {
			
		} else if (selectNumber.equals("4")) {              ///////****임시
			System.out.println("[게시판 선택] | [1. 자유 게시판] | [2. 리뷰게시판]");
			System.out.print("선택> ");
			switch(sc.nextLine()) {
			case "1" ->{
				FreeBoard_View freeboard_view = new FreeBoard_View(client);
				freeboard_view.startFB();
				JsonObject= new JSONObject(client.receive());
				System.out.println(JsonObject.toString());
				System.out.println(JsonObject.getString("message"));
				LoginMenu();				
			}
			case "2" -> {
				ReviewBoard_View rv = new ReviewBoard_View(client);
				rv.startRb();
				JsonObject= new JSONObject(client.receive());
				System.out.println(JsonObject.toString());
				System.out.println(JsonObject.getString("message"));
				LoginMenu();
			}
			}
			
			
			
		} else if (selectNumber.equals("5")) {
			System.out.println("접속을 종료합니다.");
			client.unconnect();
			
		} else {
			LoginMenu();
		}
		
	}
	
	
	
	
	public void MyPage() throws IOException  {
		System.out.println();
		System.out.println("[1. 회원정보 수정] | [2. 회원 탈퇴] | [3. 뒤로 가기]");
		System.out.print("선택 > : ");
		selectNumber = sc.nextLine();
		if(selectNumber.equals("1")) {
			
			userFunction.UserInfo(client);
			JsonObject= new JSONObject(client.receive());
			//기존 회원정보 출력
				System.out.println(JsonObject.getString("id"));
				System.out.println(JsonObject.getString("name"));
				System.out.println(JsonObject.getString("phone"));
				System.out.println(JsonObject.getString("email"));
				System.out.println(JsonObject.getString("nickname"));
				System.out.println(JsonObject.getString("address"));
				
				 userFunction.Modify(client);
				 JsonObject= new JSONObject(client.receive());
				 System.out.println(JsonObject.getString("message"));
				 
				 
		} else if ( selectNumber.equals("2")) {
			userFunction.Delte(client);
			JsonObject= new JSONObject(client.receive());
			System.out.println(JsonObject.getString("message"));
			
			MainMenu();
			
		} else if (selectNumber.equals("3")) {
			LoginMenu();
		} else {
			System.out.println("메뉴 선택 오류");
			MyPage();
		}
		
		
	}
	
	
	
	
	
	public void ManagerMenu() {
		System.out.println("");
		System.out.println("[1. 게시글 관리] | [2. 회원 관리] | [3. 댓글 관리] | [4. 상품 관리] | [5.로그아웃]");
		System.out.print("선택 > : ");
		selectNumber = sc.nextLine();
		
		
		
		
	}
	
	
	
	
	
	
	
}
