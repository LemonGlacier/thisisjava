package Team5_com.CC2;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONObject;

import Team5_com.ClientCase.ChatClient;

public class BoardView implements Paging{
	//필드
	private static Scanner scanner = new Scanner(System.in);
	private ChatClient chatClient;
	//public Page Page;
	
	//생성자
	BoardView(ChatClient chatClient){	
		this.chatClient = chatClient;
	}
	
	//메소드
	//일단 메인메뉴에서 게시판을 선택했다면
			public static void board() {
				System.out.println("-------------------------------------------------------");
				System.out.println("1. 자유 게시판 | 2. 리뷰 게시판 | 3. 문의 게시판 | 4. 메인 페이지 ");
				System.out.println("-------------------------------------------------------");
				System.out.print("선택>");
				//Scanner scanner = new Scanner(System.in);   
				String key = scanner.nextLine();
				switch (key) {
				//case "1" -> FreeBoardView.startFB();   //**상속하고 객체 만들까
				//case "2" -> startRB(); 
				//case "3" -> startQB(); 
				//default -> //메인페이지
				}	
			}
			
			/*
			public void startB(String boardName) {
				String kname = (boardName.equals("FB"))? "자유": ((boardName.equals("RB"))?"리뷰" : "문의");
				System.out.println("[" + kname + " 게시판]");
				System.out.println("------------------------------------------------------");            
				System.out.println("");
				System.out.printf("%-6s%-12s%-40s%-16s\n\t(%2d)","번호", "작성자", "제목", "날짜", "(댓글)");     //**바이트 수만큼 글자수를 넣어 줄까
				System.out.println("------------------------------------------------------");
				
				try {
					receiveB(boardName);                      
						
				}catch(Exception e) {
					e.printStackTrace();
					//System.exit(0);       
				}
				
				board();
			}
			
			//게시판 목록 받아서 출력
			public void receiveB(String boardName) {//**FB를 받아서 여기서 처리할 지 상속을 해서 만들지
					try {
						// 전체 행수
						//String totalRow = "select count(*) from free_board; ";  --이건 서버에서 실행하자
						JSONObject json = new JSONObject();
						json.put("command", "totalRowNumB");              //메소드 만들기+case 추가
						json.put("BN", boardName);
						chatClient.send(json.toString());
						//**receive();
						String totalNum = chatClient.dis.readUTF();
						JSONObject root_row = new JSONObject(totalNum);
						
						Page page = new Page();
						page.setTotalRow(root_row.getInt("totalRowNumB"));
						page.setCurrPage(1);
						setPageInfo(page, 5, 5);                  //**페이지 수 조정하기
						
						//**여기부터 새 메소드로 넣을까?? 그냥 위에서 부터 해??
						// 목록출력
						//String sql = "select Free_Bno, Free_Btitle, Free_Bcontent, Free_Date, Free_Comment_Num, User_Id from free_board order by free_bno desc where Free_Bno >= "
							//	+ String.valueOf(page.getStartRow()) + " and Free_Bno<= " + String.valueOf(page.getEndRow());
						JSONObject jsonO = new JSONObject().put("command", "printBList").put("BN", boardName);   //**--서버에서 sql 실행하기
						                                                                                 //메소드 만들기+case 추가    
						chatClient.send(jsonO.toString());
						//chatClient.receive();
					
						System.out.println("--------------------------------------------------");					                
								for (int i = 0; i < page.getEndRow(); i++) {
									JSONObject root = new JSONObject(chatClient.dis.readUTF());

									System.out.printf("%-6s%-12s%-40s%-16s\t(%d)\n",   //**이부분은 bno로 받을지 free_bno로 받을지
											root.getString("Bno"),
											root.getString("User_Id"),  //**닉네임으로할까
											root.getString("Btitle"),
											//fboard.setFree_Bcontent(root.getString("Free_Bcontent")),
											root.getString("Date"),                    //**getDate()???
											root.getInt("Comment_Num")
											);
								}						
								printPaging(page); 
								System.out.println("--------------------------------------------------"); 
								//**선택                                        //**페이지 어떡할지 정하기
								System.out.println("1. 페이지 선택 | 2. 게시글 보기 ");
								String key = scanner.nextLine();
								if(key.equals("1")) {
									System.out.println("선택> pp: 처음 | p: 이전 | n: 다음 | nn: 맨끝");
									System.out.print("선택> ");
									key = scanner.nextLine();
									switch(key) {
									case "pp" -> {
										page.setCurrPage(1);		
										receiveB(boardName);
										}
									case "p" -> {
										page.setCurrPage(page.getCurrPage() - 1);
										receiveB(boardName);
									}
									case "n" ->{
										page.setCurrPage(page.getCurrPage() + 1);
										receiveB(boardName);
									}
									case "nn" ->{
										page.setCurrPage(page.getTotalPage());
										receiveB(boardName);
									}
									default ->{
										//페이지 번호 보게 할 거야? 그냥 이전 다음만 하게 하나
										board();
									}
									}
								} else if(key.equals("2")) readB(boardName);				

					} catch (IOException e) {
						System.out.println("[클라이언트] 서버 연결끊김");
						System.exit(0);
					}
					board();           
			}
			*/
			
			public static void savePrint() {
				System.out.println("---------------------------------------");
				System.out.println("저장하시겠습니까? 1. 예 | 2. 아니오");
				System.out.println("---------------------------------------");
				System.out.print("선택> ");		
			}
			
			/*
			//게시글 쓰기
			public void createB(String boardName) {
				if(아이디저장 !=null) {
				Board b = new Board();
				String kname = (boardName.equals("FB"))? "자유": ((boardName.equals("RB"))?"리뷰" : "문의");
				System.out.println("########[" + boardName + " 게시판]########");
				System.out.println("[새 게시글]");
				System.out.print("제목: ");
				b.setTitle(scanner.nextLine());
				System.out.print("내용: ");
				b.setContent(scanner.nextLine());
				System.out.print("작성자: ");      //**작성자도 생략??
				b.setWriter(scanner.nextLine());
				b.setDate(new Date()); 
				
				savePrint();
				String selectSave = scanner.nextLine();
				if(selectSave.equals("1")) {
					//저장
					try {
						//JSONObject json = new JSONObject().put("creatFB",fb);
						String json = new JSONObject().put("command", "CreateB").put("B",b).toString();
						//**이것도 fb를 보낼지 상속을 해서 또 만들지
						chatClient.send(json);     //**클라이언트                    //**서버에서 디비 저장 실행하기
						System.out.println("게시글이 생성되었습니다.");
						
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				startB(boardName);
			} else System.out.println("로그인 후 이용해 주세요.");
			}
			
			//게시글 보기
			public void readB(String boardName) {
				System.out.print("게시글 번호 선택> ");
				String selectBN = scanner.nextLine();
				//String sql = "select Free_Btitle, Free_Bcontent, Free_Date, Free_Comment_Num, User_Id from free_board order by Free_Bno desc where Free_Bno = " + key;
				//**이것도 한 번에/따로(상속)
				try {
					JSONObject jsonObject = new JSONObject()
						.put("command", "ReadB")            //**--서버에서 sql 실행하기 //메소드 만들기+case 추가 
						.put("Free_Bno", selectBN);            
					String jsonReadB = jsonObject.toString();
					chatClient.send(jsonReadB);
					//chatClient.receive();
					
					JSONObject root = new JSONObject(chatClient.dis.readUTF());
					System.out.println("[게시글 내용]");
					System.out.println("******************************");
					System.out.println("번호: " + selectBN);          
					System.out.println("제목: " + root.getString("Free_Btitle"));
					System.out.println("내용: " + root.getString("Free_Bcontent"));
					System.out.println("작성자: " + root.getString("User_Id"));
					System.out.println("작성일자: " + root.getString("Free_Date"));
					System.out.println();
					System.out.println("댓글 수: " + root.getInt("Free_Comment_Num"));
					System.out.println("******************************");
					
					subMenuPrint();
					String key = scanner.nextLine();
					switch(key) {
					case "1" -> readComment(selectBN, root.getInt("Free_Comment_Num"));
					case "2" -> createComment();
					case "3" -> updateB(selectFBN);
					case "4" -> deleteB(selectFBN);
					//default -> mainMenu();
					
					}
				
				} catch (IOException e) {
					e.printStackTrace();
				}
				//보드로 가줘야 하나
				startB();
			}
			
			*/
			public static void subMenuPrint() {
				System.out.println("-----------------------------------------------------------------");
				System.out.println("1. 댓글 보기 | 2. 댓글 쓰기 | 3. 게시글 수정 | 4. 게시글 삭제 | 5. 메인 메뉴 ");
				System.out.println("-----------------------------------------------------------------");
				System.out.print("선택> ");		
			}
			
			//delete r+q 통합??
			
			
			
			
			
			

}

