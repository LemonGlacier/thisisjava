package Team5_com.ClientCase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONObject;

import Team5_com.DTO.ChatClient;
import Team5_com.DTO.Free_Board;
import Team5_com.DTO.Free_Comment;
import Team5_com.DTO.Page;
import Team5_com.DTO.Paging;

public class FreeBoardView {
	//필드
	private static Scanner scanner = new Scanner(System.in);
	private ChatClient chatClient;
	
	//생성자
	FreeBoardView(ChatClient chatClient){	
		this.chatClient = chatClient;
	}
	
	//메소드
	//클라이언트		
		//자유 게시판 시작
		public void startFBC() {
			System.out.println("[자유 게시판]");
			System.out.println("------------------------------------------------------");            
			System.out.println("");
			System.out.printf("%-6s%-12s%-40s%-16s\n\t(%2d)","번호", "작성자", "제목", "날짜", "(댓글)");     //**바이트 수만큼 글자수를 넣어 줄까
			System.out.println("------------------------------------------------------");
			
			try {
				receiveFB();                      
					
			}catch(Exception e) {
				e.printStackTrace();
				//System.exit(0);       
			}
			
			BoardView.board();
		}
		
		//게시판 목록 받아서 출력
		public void receiveFB() {
				try {
					// 전체 행수
					//String totalRow = "select count(*) from free_board; ";  --이건 서버에서 실행하자
					JSONObject json = new JSONObject();
					json.put("command", "totalRowNumFB");              //메소드 만들기+case 추가
					chatClient.send(json.toString());
					//**receive();
					//^^String totalNum = chatClient.dis.readUTF();
					//^^JSONObject root_row = new JSONObject(totalNum);
					
					Page page = new Page();
					//^^page.setTotalRow(root_row.getInt("totalRowNumFB"));
					page.setCurrPage(1);
					//^^Paging.setPageInfo(page, 5, 5);                  //**페이지 수 조정하기
					
					// 목록출력
					//String sql = "select Free_Bno, Free_Btitle, Free_Bcontent, Free_Date, Free_Comment_Num, User_Id from free_board order by free_bno desc where Free_Bno >= "
						//	+ String.valueOf(page.getStartRow()) + " and Free_Bno<= " + String.valueOf(page.getEndRow());
					JSONObject jsonObject = new JSONObject().put("command", "printFBList");              //**--서버에서 sql 실행하기
					                                                                                 //메소드 만들기+case 추가    
					chatClient.send(jsonObject.toString());
					//chatClient.receive();
				
					System.out.println("--------------------------------------------------");
					
					Thread thread = new Thread(() -> {                   //**있어도되고 없어도되고(성능)
						//try {						                      //**스레드 안이라서 try 또 해줘야하나                   
							for (int i = 0; i < page.getEndRow(); i++) {

								//^^JSONObject root = new JSONObject(chatClient.dis.readUTF());

								//^^System.out.printf("%-6s%-12s%-40s%-16s\t(%d)\n",
										//^^root.getString("Free_Bno"),
										//^^root.getString("User_Id"),
										//^^root.getString("Free_Btitle"),
										//fboard.setFree_Bcontent(root.getString("Free_Bcontent")),
										//^^root.getString("Free_Date"),                    //**getDate()???
										//^^root.getInt("Free_Comment_Num")
								//^^);
								
							}						
							//^^Paging.printPaging(page);
							System.out.println("--------------------------------------------------"); 
							//**선택                                        //**페이지 어떡할지 정하기
							System.out.println("1. 페이지 선택 | 2. 게시글 보기 ");
							String key = scanner.nextLine();
							if(key.equals("1")) {
								System.out.println("선택> pp: 처음 | p: 이전 | n: 다음 | nn: 맨끝 | 페이지: 페이지 번호");
								System.out.print("선택> ");
								key = scanner.nextLine();
								switch(key) {
								//case "pp" ->
								//case "p" ->
								//case "n" ->
								//case "nn" ->
								//default ->
								}
							} else if(key.equals("2")) readFB();				
							

						//} catch (IOException e) {
							//System.out.println("[클라이언트] 연결끊김");               //**try catch는 한 번만 하면 되지??
							//System.exit(0);
						//}
					});
					thread.start();

				} catch (IOException e) {
					System.out.println("[클라이언트] 서버 연결끊김");
					System.exit(0);
				}
				BoardView.board();           
		}
		
		//**자유게시판 게시글 쓰기
			public void createFB() {
				Free_Board fb = new Free_Board();
				System.out.println("########[자유 게시판]########");
				System.out.println("[새 게시글]");
				System.out.print("제목: ");
				fb.setFree_Btitle(scanner.nextLine());
				System.out.print("내용: ");
				fb.setFree_Bcontent(scanner.nextLine());
				System.out.print("작성자: ");
				fb.setUser_Id(scanner.nextLine());
				System.out.print("비밀번호: ");
				//^^fb.setFree_Pwd(scanner.nextLine());
				fb.setFree_Date(new Date()); 
				
				BoardView.savePrint();
				String selectSave = scanner.nextLine();
				if(selectSave.equals("1")) {
					//저장
					try {
						//JSONObject json = new JSONObject().put("creatFB",fb);
						String json = new JSONObject().put("command", "CreateFB").put("FB",fb).toString();
						chatClient.send(json);     //**클라이언트                    //**서버에서 디비 저장 실행하기
						System.out.println("게시글이 생성되었습니다.");
						
					}catch(Exception e) {
						e.printStackTrace();
						//exit();
					}
				}
				startFBC();
			}
			
			//자유게시판 게시글 보기
			public void readFB() {
				System.out.print("게시글 번호 선택> ");
				String selectFBN = scanner.nextLine();
				//String sql = "select Free_Btitle, Free_Bcontent, Free_Date, Free_Comment_Num, User_Id from free_board order by Free_Bno desc where Free_Bno = " + key;
				try {
					JSONObject jsonObject = new JSONObject()
						.put("command", "ReadFB")            //**--서버에서 sql 실행하기 //메소드 만들기+case 추가 
						.put("Free_Bno", selectFBN);            
					String jsonReadFB = jsonObject.toString();
					chatClient.send(jsonReadFB);
					//chatClient.receive();
					
					//^^JSONObject root = new JSONObject(chatClient.dis.readUTF());
					System.out.println("[게시글 내용]");
					System.out.println("******************************");
					System.out.println("번호: " + selectFBN);          
					//^^System.out.println("제목: " + root.getString("Free_Btitle"));
					//^^System.out.println("내용: " + root.getString("Free_Bcontent"));
					//^^System.out.println("작성자: " + root.getString("User_Id"));
					//^^System.out.println("작성일자: " + root.getString("Free_Date"));
					System.out.println();
					//^^System.out.println("댓글 수: " + root.getInt("Free_Comment_Num"));
					System.out.println("******************************");
					
					BoardView.subMenuPrint();
					String key = scanner.nextLine();
					switch(key) {
					//^^case "1" -> readFComment(selectFBN, root.getInt("Free_Comment_Num"));
					case "2" -> createFComment();
					case "3" -> updateFB(selectFBN);
					case "4" -> deleteFB(selectFBN);
					//default -> mainMenu();
					
					}
				
				} catch (IOException e) {
					e.printStackTrace();
				}
				//보드로 가줘야 하나
				startFBC();
			}

			/*public void subMenuPrint() {
				System.out.println("-----------------------------------------------------------------");
				System.out.println("1. 댓글 보기 | 2. 댓글 쓰기 | 3. 게시글 수정 | 4. 게시글 삭제 | 5. 메인 메뉴 ");
				System.out.println("-----------------------------------------------------------------");
				System.out.print("선택> ");		
			}*/
			
			public void readFComment(String selectFBN, int free_Comment_Num) {   //**너무 긴가
				try {
					Page page = new Page();
					page.setTotalRow(free_Comment_Num);
					page.setCurrPage(1);
					//^^Paging.setPageInfo(page, 5, 5);   
					
					JSONObject jsonObject = new JSONObject()
						.put("command", "ReadFCo")            //**--서버에서 sql 실행하기 //메소드 만들기+case 추가 
						.put("Free_Bno", selectFBN);            
					String jsonReadCo = jsonObject.toString();
					
					chatClient.send(jsonReadCo);
					
					Thread thread = new Thread(()->{
					for(int i =0; i<page.getEndRow(); i++) {
						//chatClient.receive();
						//^^JSONObject root = new JSONObject(chatClient.dis.readUTF());						
						//^^System.out.printf("%-6s%-12s%-40s%-16s\n",
								//^^root.getString("Free_Conum"),
								//^^root.getString("Co_User_Id"),                      //**이름 주의!
								//^^root.getString("Free_Cocontent"),                 //**좀 더 길게 줘야 하나?? 40s
								//^^root.getString("Free_Codate")                    //**getDate()???
						//^^);
						
					}
					//^^Paging.printPaging(page);
					System.out.println("--------------------------------------------------"); 
					//**선택                                        //**페이지 어떡할지 정하기
					System.out.println("1. 페이지 선택 | 2.  ");  //댓글 수정??
					String key = scanner.nextLine();
					switch(key) {
					case "1" -> {
						System.out.println("선택> pp: 처음 | p: 이전 | n: 다음 | nn: 맨끝 | 페이지: 페이지 번호");
						System.out.print("선택> ");
						String select = scanner.nextLine();
						switch(select) {
						//case "pp" ->
						//case "p" ->
						//case "n" ->
						//case "nn" ->
						//default ->
						}
					}
					//case "2" -> 댓글 수정
					}
				});
					} catch (IOException e) {
						e.printStackTrace();
					}
				startFBC();
			}
			
			private void createFComment() {   //**봐서 board로 옮길까  //**혹시 댓글도 수정해야 하나요..??
				Free_Comment fc = new Free_Comment();
				System.out.println("########[자유 게시판 댓글 쓰기]########");
				//System.out.println("[댓글 입력]");
				System.out.print("내용: ");
				fc.setFree_Cocontent(scanner.nextLine());
				System.out.print("작성자: ");
				fc.setUser_Id(scanner.nextLine());
				//System.out.print("비밀번호: ");
				//fc.setFree_Pwd(scanner.nextLine());
				fc.setFree_Codate(new Date()); 
				
				BoardView.savePrint();
				String selectSave = scanner.nextLine();
				if(selectSave.equals("1")) {
					//저장
					try {
						//JSONObject json = new JSONObject().put("creatFB",fb);
						String json = new JSONObject().put("creatFC",fc).toString();
						chatClient.send(json);     //**클라이언트                    //**서버에서 디비 저장 실행하기
						System.out.println("댓글이 생성되었습니다.");
						
					}catch(Exception e) {
						e.printStackTrace();
						//exit();
					}
				}
				startFBC();
			}
			
			public void updateFB(String selectFBN) {
				System.out.print("비밀번호 입력> ");
				String key = scanner.nextLine();				
				try {
					String json = new JSONObject().put("command", "FBPwd").put("Free_Bno", selectFBN).put("Free_Pwd", key).toString();
					chatClient.send(json);     //**클라이언트                    //**서버에서 디비 수정 실행하기
					//receive();
					//^^JSONObject root = new JSONObject(chatClient.dis.readUTF());
					//^^String rightFPwd = root.getString("Free_Pwd");
					//^^if(rightFPwd.equals(key)) {
						//자유 게시판 글 수정
						System.out.println("[게시글 수정]");
						Free_Board fb = new Free_Board();
						System.out.print("제목: ");
						fb.setFree_Btitle(scanner.nextLine());
						System.out.print("내용: ");
						fb.setFree_Bcontent(scanner.nextLine());
						System.out.print("작성자: ");
						fb.setUser_Id(scanner.nextLine());
						System.out.print("비밀번호: ");
						//^^fb.setFree_Pwd(scanner.nextLine());
						fb.setFree_Date(new Date()); 
						
						BoardView.savePrint();
						String selectSave = scanner.nextLine();
						if(selectSave.equals("1")) {
							//저장//실행
							try {
								String jsonUpdateFB = new JSONObject().put("command", "UpdateFB").put("Free_Bno", selectFBN).put("FB",fb).toString();
								chatClient.send(json);     //**클라이언트                    //**서버에서 디비 수정 실행하기
								System.out.println("게시글이 수정되었습니다.");
								
							}catch(Exception e) {
								e.printStackTrace();
								//exit();
							}
						}
						startFBC();
						
						//^^} //비번 틀렸을 때
					startFBC();
				} catch (IOException e) {
					e.printStackTrace();
				}    
				BoardView.board();
			}
			
			public void deleteFB(String selectFBN) {
				System.out.print("비밀번호 입력> ");
				String key = scanner.nextLine();				
				try {
					String json = new JSONObject().put("command", "FBPwd").put("Free_Bno", selectFBN).put("Free_Pwd", key).toString();
					chatClient.send(json);     //**클라이언트                    //**서버에서 디비 수정 실행하기
					//receive();
					//^^JSONObject root = new JSONObject(chatClient.dis.readUTF());
					//^^String rightFPwd = root.getString("Free_Pwd");
					//^^if(rightFPwd.equals(key)) {
						String jsonDel = new JSONObject().put("command", "DeleteFB").put("Free_Bno", selectFBN).toString();
						chatClient.send(json);
						//receive();
						//^^}
				} catch (IOException e) {
					e.printStackTrace();
				}    
				startFBC();
			}
	//답글 수정/삭제
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
