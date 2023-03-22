package Team5_com.DTO;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONObject;

import Team5_com.Memo.ConnectionProvider;

public class FreeBoardService {
	//필드
	Scanner scanner = new Scanner(System.in);
	
	//메소드
	//서버

	public void getFB(SocketClient user, int selectP) {
		Connection conn = null;
		try {                                                   //이거 아예 sql문에 where Free_Bno<selectP*5 이런 식으로 넣을까
			String sql = "select Free_Bno, Free_Btitle, Free_Bcontent, Free_Date, Free_Comment_Num, Users_User_Id from free_board order by free_bno desc;";  //매개변수화된 select문  //내림차순
			String countSql = "select count(*) from free_board;";
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);                                  //컴파일된 sql문 인터페이스
			ResultSet rs = pstmt.executeQuery();                                         //select문에 기술된 컬럼으로 구성된 행 집합
			ResultSet crs = conn.prepareStatement(countSql).executeQuery();
			
			//**페이징 처리
			//일단 int select=1 하고 출력을 (page-1)*rowsPerPage+1 ~ page*rowsPerPage 이런 거하고 전체 페이지수 구해서 몇 페이지까지 있는지 나오게 하기
			//int selectP = 1;
			//**일단 한 페이지 5개 출력(보내기)
			while((selectP-1)*5<=selectP*5) {                                     
				JSONObject root = new JSONObject();                         //데이터 행을 읽고 JSON 으로 저장
				root.put("Free_Bno", rs.getString("Free_Bno"));                    //free_bno컬럼 값
				root.put("Free_Btitle", rs.getString("Free_Btitle"));
				root.put("Free_Bcontent", rs.getString("Free_Bcontent"));
				root.put("Free_Date", rs.getString("Free_Date"));               //getDate()??
				root.put("Free_Comment_Num", rs.getInt("Free_Comment_Num"));              
				root.put("User_Id", rs.getString("User_Id"));    
				root.put("Whole_Num", crs.getInt("count(*)"));                //**굳이 5번 보낼 필요가 없긴한데
				
				selectP++;
				
				//JSONObject(toString으로 변환해서) 보내기
				//String json = root.toString();
				//Socket socket = serverSocket.accept();
				//SocketClient user = new SocketClient(this, socket);
				user.send(root.toString());                               //**send() , SocketClient 만들기 확인			
				
				//클라이언트가 JSONObject를 받아서 출력           //**Client 클래스
				//try { while(rs.next) {  
				//Client.receiveFB();
				/*System.out.printf("%-6s%-12s%-16s%-40s \n",
						fboard.get("Free_Bno"),
						fboard.getFree_Btitle(),
						fboard.getFree_Bcontent(),
						fboard.getFree_Date(),
						fboard.getFree_Comment_Num(),
						fboard.getUser_Id()
						);*/
				
				//**여기다 번호 선택을 넣어야하나??
			}                                                   //커서:afterLast     //**페이징 처리
			rs.close();                                        //메모리 해제
			pstmt.close();                                     //PreparedStatement 닫기
			
		}catch(SQLException e) {
			e.printStackTrace();
			//exit();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void startFBS(SocketClient user) {
		System.out.println("[자유 게시판]");
		System.out.println("------------------------------------------------------");             //**이거 출력도 클라이언트에서 할까
		System.out.println("");
		System.out.printf("%-6s%-12s%-40s%-16s\n","번호","작성자", "제목", "날짜");                    //**바이트 수만큼 글자수를 넣어 줄까 //**댓글 표시 해줄까
		System.out.println("------------------------------------------------------");
		//System.out.print("선택>");
		//Scanner scanner = new Scanner(System.in);   ///**
		//String key = scanner.nextLine();
		//^^ServerSocket serverSocket = new ServerSocket(50001);
		Connection conn = null;
		
		try {  //이거 지우거나 sql 받아서 실행하는 걸로 만들기
			conn = ConnectionProvider.getConnection();
			String sql = "select Free_Bno, Free_Btitle, Free_Bcontent, Free_Date, Free_Comment_Num, User_Id from free_board order by free_bno desc";  //매개변수화된 select문  //내림차순
			PreparedStatement pstmt = conn.prepareStatement(sql);                                  //컴파일된 sql문 인터페이스
			ResultSet rs = pstmt.executeQuery();                                         //select문에 기술된 컬럼으로 구성된 행 집합
			while(rs.next()) {                                      //데이터 행 여러 개(커서:beforeFirst>>...)
				JSONObject root = new JSONObject();                         //데이터 행을 읽고 JSON 으로 저장
				root.put("Free_Bno", rs.getString("Free_Bno"));                    //free_bno컬럼 값
				root.put("Free_Btitle", rs.getString("Free_Btitle"));
				root.put("Free_Bcontent", rs.getString("Free_Bcontent"));
				root.put("Free_Date", rs.getString("Free_Date"));               //**getDate()??  //SimpleDateFormat??
				root.put("Free_Comment_Num", rs.getInt("Free_Comment_Num"));              
				root.put("User_Id", rs.getString("User_Id"));                //**이거 보내는 것도 페이징 고려해서 보내야 하나
				
				//JSONObject(toString으로 변환해서) 보내기
				//String json = fboard.toString();
				//Socket socket = serverSocket.accept();
				//SocketClient user = new SocketClient(this, socket);
				user.send(root.toString());                               //**send() , SocketClient 만들기 확인			
				
				//클라이언트가 JSONObject를 받아서 출력         
				//try { while(rs.next) {  
				//^^receiveFB();
				/*System.out.printf("%-6s%-12s%-16s%-40s \n",
						fboard.get("Free_Bno"),
						fboard.getFree_Btitle(),
						fboard.getFree_Bcontent(),
						fboard.getFree_Date(),
						fboard.getFree_Comment_Num(),
						fboard.getUser_Id()
						);*/
				
				//**여기다 번호 선택을 넣어야하나??
			}                                                   //커서:afterLast     //**페이징 처리
			rs.close();                                        //메모리 해제
			pstmt.close();                                     //PreparedStatement 닫기
			
		}catch(SQLException e) {
			e.printStackTrace();
			//^^exit();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//mainMenu();   //**메인메뉴 가기
	}

	
	
	//클라이언트
	//일단 메인메뉴에서 게시판을 선택했다면
	public void board() {
		System.out.println("-------------------------------------------------------");
		System.out.println("1. 자유 게시판 | 2. 리뷰 게시판 | 3. 문의 게시판 | 4. 메인 페이지 ");
		System.out.println("-------------------------------------------------------");
		System.out.print("선택>");
		//Scanner scanner = new Scanner(System.in);   
		String key = scanner.nextLine();
		switch (key) {
		//case "1" -> Client이름.startFB(Socketclient 이름); break;
		//case "2" -> startRB(); break;
		//case "3" -> startQB(); break;
		//default -> //메인페이지
		}	
	}
	
	//총 열 개수 구하기--서버 메소드 아니냐
	public static String getTR(ChatClient chatClient) {
		try {
			// 전체 행수
			String sql_totalrow = "select count(*) from free_board ";
			JSONObject jsonObject_row = new JSONObject();
			jsonObject_row.put("sql", sql_totalrow);
			jsonObject_row.put("rsNum", 1);
			chatClient.send(jsonObject_row.toString());
			return chatClient.dis.readUTF();
	
		} catch (IOException e) {
			System.out.println("[클라이언트] 서버에 연결끊김");
			System.exit(0);
			return "";
		}
	}

	
	//자유 게시판 시작
	public void startFBC(SocketClient user) {
		System.out.println("[자유 게시판]");
		System.out.println("------------------------------------------------------");            
		System.out.println("");
		System.out.printf("%-6s%-12s%-40s%-16s\n\\t(%2d)","번호","작성자", "제목", "날짜", "(댓글)");     //**바이트 수만큼 글자수를 넣어 줄까
		System.out.println("------------------------------------------------------");
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
			//^^receiveFB();
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
	
	//게시판 목록 받아서 출력
	public void receiveFB(ChatClient chatClient) {
		//Thread thread = new Thread(() -> {
			try {
				String json_row = FreeBoardService.getTR(chatClient);
				
				JSONObject root_row = new JSONObject(json_row);
				
				Page page = new Page();
				page.setTotalRow(root_row.getInt("1"));
				page.setCurrPage(1);
				Paging.setPageInfo(page, 1, 1);                  //**페이지 수 조정하기
				
				// 상품목록출력
				String sql = "select Free_Bno, Free_Btitle, Free_Bcontent, Free_Date, Free_Comment_Num, User_Id from free_board order by free_bno desc where rownum >= "
						+ String.valueOf(page.getStartRow()) + " and rownum <= " + String.valueOf(page.getEndRow());
				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("sql", sql);                         //**서버에서 sql 실행하기
				String json = jsonObject.toString();
				chatClient.send(json);
				
				//System.out.println("#########[자유 게시판]#########");
				System.out.println("--------------------------------------------------");
				
				Thread thread = new Thread(() -> {
					try {						
						for (int i = 0; i < page.getEndRow(); i++) {

							JSONObject root = new JSONObject(chatClient.dis.readUTF());

							System.out.printf("%-6s%-12s%-40s%-16s\t(%d)\n",
									root.getString("Free_Bno"),
									root.getString("User_Id"),
									root.getString("Free_Btitle"),
									//fboard.setFree_Bcontent(root.getString("Free_Bcontent")),
									root.getString("Free_Date"),                    //**getDate()???
									root.getInt("Free_Comment_Num")
									);
							System.out.println("--------------------------------------------------");

						}						
						Paging.printPaging(page);

					} catch (IOException e) {
						System.out.println("[클라이언트] 서버에 연결끊김");
						System.exit(0);
					}
				});
				thread.start();

			} catch (IOException e) {
				System.out.println("[클라이언트] 서버에 연결끊김");
				System.exit(0);
			}
		//});
		//thread.start();
	}
	
	//**자유게시판 게시글 쓰기
		public void createFB(ChatClient chatClient) {
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
					//JSONObject json = new JSONObject().put("creatFB",fb);
					String json = new JSONObject().put("creatFB",fb).toString();
					chatClient.send(json);     //**클라이언트                    //**서버에서 디비 저장 실행하기
					
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
	
	
	//서버소켓
		//receive():json 명령어 넣기

}
