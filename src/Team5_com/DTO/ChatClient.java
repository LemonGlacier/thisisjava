package Team5_com.DTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONObject;

public class ChatClient {
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	String chatName;
	//int selectP=1;
	private Scanner scanner = new Scanner(System.in);
	int t; //총페이지 오류

	// 메소드
	public void connect() throws IOException {
		socket = new Socket("localhost", 50001);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		System.out.println("[클라이언트] 서버에 연결됨");
	}
	
		
		private void startFB(SocketClient user) {
			System.out.println("[자유 게시판]");
			System.out.println("------------------------------------------------------");            
			System.out.println("");
			System.out.printf("%-6s%-12s%-40s%-16s\n\\t(%2d)","번호","작성자", "제목", "날짜", "(댓글)");     //**바이트 수만큼 글자수를 넣어 줄까
			System.out.println("------------------------------------------------------");
			int selectP =1;                                           //**얘를 어따 넣어야 하나
			//System.out.print("선택>");
			//Scanner scanner = new Scanner(System.in);   ///**
			//String key = scanner.nextLine();
			//ServerSocket serverSocket = new ServerSocket(50001);
			
			try {
				//String sql = "select Free_Bno, Free_Btitle, Free_Bcontent, Free_Date, Free_Comment_Num, User_Id from free_board order by free_bno desc";  //매개변수화된 select문  //내림차순
				//PreparedStatement pstmt = conn.prepareStatement(sql);                                  //컴파일된 sql문 인터페이스
				//ResultSet rs = pstmt.executeQuery();                                         //select문에 기술된 컬럼으로 구성된 행 집합
				
				//** 서버에 정보 요청
				
				/*while((selectP-1)*5<=selectP*5) {                                      //데이터 행 여러 개(커서:beforeFirst>>...)
					JSONObject root = new JSONObject();                         //데이터 행을 읽고 JSON 으로 저장
					root.put("Free_Bno", rs.getString("Free_Bno"));                    //free_bno컬럼 값
					root.put("Free_Btitle", rs.getString("Free_Btitle"));
					root.put("Free_Bcontent", rs.getString("Free_Bcontent"));
					root.put("Free_Date", rs.getDate("Free_Date"));
					root.put("Free_Comment_Num", rs.getInt("Free_Comment_Num"));              
					root.put("User_Id", rs.getString("User_Id"));    */           //**이거 보내는 것도 페이징 고려해서 보내야 하나
					
					//JSONObject(toString으로 변환해서) 보내기
					//String json = fboard.toString();
					//Socket socket = serverSocket.accept();
					//SocketClient user = new SocketClient(this, socket);
					//user.send(root.toString());                               //**send() , SocketClient 만들기 확인			
					
					//클라이언트가 JSONObject를 받아서 출력           //**Client 클래스
					//try { while(rs.next) {  
					receiveFB();
					/*System.out.printf("%-6s%-12s%-16s%-40s \n",
							fboard.get("Free_Bno"),
							fboard.getFree_Btitle(),
							fboard.getFree_Bcontent(),
							fboard.getFree_Date(),
							fboard.getFree_Comment_Num(),
							fboard.getUser_Id()
							);*/
					
					//**여기다 번호 선택을 넣어야하나??
				//}                                                 //커서:afterLast     //**페이징 처리
				//rs.close();                                        //메모리 해제
				//pstmt.close();                                     //PreparedStatement 닫기
				
			}catch(Exception e) {
				e.printStackTrace();
				//exit();
			}
			
			//mainMenu();   //**메인메뉴 가기
		}
	
	
	
	
	
	
	
	
	
	public void receiveFB() {
		Thread thread = new Thread(() -> {
			try {
				//**페이징 처리하기
				//일단 int select=1 하고 출력을 (page-1)*rowsPerPage+1 ~ page*rowsPerPage 이런 거하고 전체 페이지수 구해서 몇 페이지까지 있는지 나오게 하고(221020 13-3)
				for(int i=0; i<5; i++){
					//String json = dis.readUTF();
					JSONObject root = new JSONObject(dis.readUTF());
					//Free_Board fboard= new Free_Board();
					//DateFormat sdf = new SimpleDateFormat();
					//fboard.setProduct_Id(root.getString("PRODUCT_ID"));
					System.out.printf("%-6s%-12s%-40s%-16s\t(%d)\n",
							root.getString("Free_Bno"),
							root.getString("User_Id"),
							root.getString("Free_Btitle"),
							//fboard.setFree_Bcontent(root.getString("Free_Bcontent")),
							root.getString("Free_Date"),                    //**getDate()???
							root.getInt("Free_Comment_Num")
							);

				}
				System.out.println();
				System.out.print("<이전> ");
				for(int j=1; j<=t; j++) System.out.print(j+" ");   //**총페이지를 따로 받을까
				System.out.println("<다음>");
				
				//System.out.print("선택> ");
				//Scanner scanner = new Scanner(System.in);   
				//String key = scanner.nextLine();                  //**메소드 끝날 때 받지 말고 메인스트림에서 받는 게 나을듯

			} catch (IOException e) {
				System.out.println("[클라이언트] 서버에 연결끊김");
				System.exit(0);
			}
		});
		thread.start();
	}
	
	//**자유게시판 게시글 쓰기
	public void createFB() {
		Free_Board fb = new Free_Board();
		System.out.println("########[자유 게시판]########");
		System.out.println("[새 게시물]");
		System.out.print("제목: ");
		fb.setFree_Btitle(scanner.nextLine());
		System.out.print("내용: ");
		fb.setFree_Bcontent(scanner.nextLine());
		System.out.print("작성자: ");
		fb.setUser_Id(scanner.nextLine());
		fb.setFree_Date(new Date()); 
		
		savePrint();
		String selectSave = scanner.nextLine();
		if(selectSave.equals("1")) {
			//저장
			try {
				JSONObject json = new JSONObject().put("creatFB",fb);
				//^^Client.send(json);     //**클라이언트   
				
			}catch(Exception e) {
				e.printStackTrace();
				//exit();
			}
		}

		
	}
	
	
	
	private void savePrint() {
		System.out.println("---------------------------------------");
		System.out.println("저장하시겠습니까? 1. 예 | 2. 아니오");
		System.out.println("---------------------------------------");
		System.out.print("선택> ");		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	public void receive() {
		Thread thread = new Thread(() -> {
			try {
				while (true) {

					String json = dis.readUTF();
					JSONObject root = new JSONObject(json);
					Product product= new Product();
					product.setProduct_Id(root.getString("PRODUCT_ID"));
					product.setProduct_Name(root.getString("PRODUCT_NAME"));
					System.out.println("<"+product.getProduct_Id()+"@"+product.getProduct_Name()+"> ");
				}

			} catch (IOException e) {
				System.out.println("[클라이언트] 서버에 연결끊김");
				System.exit(0);
			}
		});
		thread.start();
	}
	public void send(String json)throws IOException{
		dos.writeUTF(json);
		dos.flush();
	}
	public void unconnect() throws IOException {
		socket.close();
	}
	public static void main(String[] args) {
		try {
			ChatClient chatClient = new ChatClient();
			chatClient.connect();
			Scanner scanner = new Scanner(System.in);
			//System.out.print("대화명 입력: ");
			//chatClient.chatName = scanner.nextLine();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("command","incoming");
			jsonObject.put("data",chatClient.chatName);
			String json = jsonObject.toString();
			chatClient.send(json);
			chatClient.receive();
			/*System.out.println("-------------------------------------------------");
			System.out.println("보낼 메시지를 입력하고 Enter");
			System.out.println("채팅를 종료하려면 q 또는 Q를 입력하고 Enter");
			System.out.println("-------------------------------------------------");
			*/
			
			//**여기도 메소드로 처리하는 게 좋을까
			
			while(true) {
				String selectP =scanner.nextLine();
				switch(selectP) {
				//^^case "가" -> break;
				//^^case "나" -> break;
				
				default-> {
					jsonObject = new JSONObject();
					jsonObject.put("command","startFB");
					jsonObject.put("selectP",selectP);
					json = jsonObject.toString();
					chatClient.send(json);
				}
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			/*while(true) {
				String message=scanner.nextLine();
				if(message.toLowerCase().equals("q")) {
					break;
				}else {
					jsonObject = new JSONObject();
					jsonObject.put("command","message");
					jsonObject.put("data",message);
					json = jsonObject.toString();
					chatClient.send(json);
				}
			}*/
			//^^scanner.close();
			//^^chatClient.unconnect();
		}catch(Exception e) {
			System.out.println("[클라이언트] 서버 연결 안됨");
		}
		System.out.println("[클라이언트] 종료");
	}

}
