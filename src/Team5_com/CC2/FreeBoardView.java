package Team5_com.CC2;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONObject;

import Team5_com.DTO.ChatClient;
import Team5_com.DTO.Free_Board;
import Team5_com.DTO.Free_Comment;

public class FreeBoardView implements Paging{ //**상속을 할까 말까
	//필드
	private static Scanner scanner = new Scanner(System.in);
	private ChatClient chatClient;
	//String bname = "FB";
	//public Page Page;
	String id; //아이디 저장 오류나서 임시로 넣음
	String a; //dis.readUTF() 오류나서 임시로 넣음
	
	//생성자
	FreeBoardView(ChatClient chatClient){	
		this.chatClient = chatClient;
	}
	
	//메소드
	//클라이언트		
		//자유 게시판 시작
		public void startFB() {
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
					String totalNum = a;//chatClient.dis.readUTF();
					JSONObject root_row = new JSONObject(totalNum);
					
					Page page = new Page();
					page.setTotalRow(root_row.getInt("totalRowNumFB"));
					page.setCurrPage(1);
					setPageInfo(page, 5, 5);                  //**페이지 수 조정하기
					
					// 목록출력
					//String sql = "select Free_Bno, Free_Btitle, Free_Bcontent, Free_Date, Free_Comment_Num, User_Id from free_board order by free_bno desc where Free_Bno >= "
						//	+ String.valueOf(page.getStartRow()) + " and Free_Bno<= " + String.valueOf(page.getEndRow());
					JSONObject jsonObject = new JSONObject().put("command", "printFBList");              //**페이지 넣어줘야 겠는데
					                                                                                 //메소드 만들기+case 추가    
					chatClient.send(jsonObject.toString());
					//chatClient.receive();
				
					System.out.println("--------------------------------------------------");					              
							for (int i = 0; i < page.getEndRow(); i++) {

								JSONObject root = new JSONObject(a); //chatClient.dis.readUTF());

								System.out.printf("%-6s%-12s%-40s%-16s\t(%d)\n",
										root.getString("Free_Bno"),
										root.getString("User_Id"),
										root.getString("Free_Btitle"),
										//fboard.setFree_Bcontent(root.getString("Free_Bcontent")),
										root.getString("Free_Date"),                    //**getDate()???
										root.getInt("Free_Comment_Num")
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
									receiveFB();
									}
								case "p" -> {
									page.setCurrPage(page.getCurrPage() - 1);
									receiveFB();
								}
								case "n" ->{
									page.setCurrPage(page.getCurrPage() + 1);
									receiveFB();
								}
								case "nn" ->{
									page.setCurrPage(page.getTotalPage());
									receiveFB();
								}
								default ->{
									//페이지 번호 보게 할 거야? 그냥 이전 다음만 하게 하나
									BoardView.board();
								}
								}
							} else if(key.equals("2")) readFB();				
							
				} catch (IOException e) {
					System.out.println("[클라이언트] 서버 연결끊김");
					System.exit(0);
				}
				BoardView.board();           
		}
		
		//**자유게시판 게시글 쓰기
			public void createFB() {
				if(id !=null) {
					Free_Board fb = new Free_Board();
					System.out.println("########[자유 게시판]########");
					System.out.println("[새 게시글]");					               
					while(true) {
						System.out.print("제목: ");  
						String valid = scanner.nextLine();
						if(valid.length()<=10) {
							fb.setFree_Btitle(valid); break;
						} else System.out.println("글자수 10자 이하로 작성해주세요.");
					}
					while(true) {
						System.out.print("내용: "); 
						String valid = scanner.nextLine();
						if(valid.length()<=333) {
							fb.setFree_Bcontent(valid); break;
						} else System.out.println("글자수 333자 이하로 작성해주세요.");
					}
					fb.setFree_Date(new Date()); 
					fb.setUser_Id(id);
					
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
				} else System.out.println("로그인 후 이용해 주세요.");
				startFB();
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
					
					JSONObject root = new JSONObject(a);           //chatClient.dis.readUTF());
					System.out.println("[게시글 내용]");
					System.out.println("******************************");
					System.out.println("번호: " + selectFBN);          
					System.out.println("제목: " + root.getString("Free_Btitle"));
					System.out.println("내용: " + root.getString("Free_Bcontent"));
					System.out.println("작성자: " + root.getString("User_Id"));
					System.out.println("작성일자: " + root.getString("Free_Date"));
					System.out.println();
					System.out.println("댓글 수: " + root.getInt("Free_Comment_Num"));
					System.out.println("******************************");
					
					BoardView.subMenuPrint();
					String key = scanner.nextLine();
					switch(key) {
					case "1" -> readFComment(selectFBN, root.getInt("Free_Comment_Num"));
					case "2" -> createFComment(selectFBN);
					case "3" -> updateFB(selectFBN, root.getString("User_Id"));
					case "4" -> deleteFB(selectFBN, root.getString("User_Id"));
					//default -> mainMenu();
					}
				
				} catch (IOException e) {
					e.printStackTrace();
				}
				//보드로 가줘야 하나
				startFB();
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
					setPageInfo(page, 5, 5);   
					
					JSONObject jsonObject = new JSONObject()
						.put("command", "ReadFCo")            //**--서버에서 sql 실행하기 //메소드 만들기+case 추가 
						.put("Free_Bno", selectFBN);            
					String jsonReadCo = jsonObject.toString();
					
					chatClient.send(jsonReadCo);
					
					for(int i =0; i<page.getEndRow(); i++) {
						//chatClient.receive();
						JSONObject root = new JSONObject(a);       //chatClient.dis.readUTF());						
						System.out.printf("%-6s%-12s%-40s%-16s\n",
								root.getString("Free_Conum"),
								root.getString("Co_User_Id"),                      //**이름 주의!
								root.getString("Free_Cocontent"),                 //**좀 더 길게 줘야 하나?? 40s
								root.getString("Free_Codate")                    //**getDate()???
								);
						
					}
					printPaging(page);
					System.out.println("--------------------------------------------------"); 
					//**선택                                        //**페이지 어떡할지 정하기
					System.out.println("1. 페이지 선택 | 2. 댓글 수정 | 3. 댓글 삭제 ");  //댓글 수정??
					String key = scanner.nextLine();
					switch(key) {
					case "1" -> {
						System.out.println("선택> pp: 처음 | p: 이전 | n: 다음 | nn: 맨끝");
						System.out.print("선택> ");
						key = scanner.nextLine();
						switch(key) {
						case "pp" -> {
							page.setCurrPage(1);		
							readFComment(selectFBN, free_Comment_Num);
							}
						case "p" -> {
							page.setCurrPage(page.getCurrPage() - 1);
							readFComment(selectFBN, free_Comment_Num);
						}
						case "n" ->{
							page.setCurrPage(page.getCurrPage() + 1);
							readFComment(selectFBN, free_Comment_Num);
						}
						case "nn" ->{
							page.setCurrPage(page.getTotalPage());
							readFComment(selectFBN, free_Comment_Num);
						}
						default ->{
							//페이지 번호 보게 할 거야? 그냥 이전 다음만 하게 하나
							readFComment(selectFBN, free_Comment_Num);
						}
						}
					}
					case "2" -> updateFComment(selectFBN);
					case "3" -> deleteFComment(selectFBN);
					}				
					} catch (IOException e) {
						e.printStackTrace();
					}
				startFB();
			}
			
			private void createFComment(String selectFBN) {   //**봐서 board로 옮길까  
				Free_Comment fc = new Free_Comment();
				System.out.println("########[자유 게시판 댓글 쓰기]########");
				if(id != null) {						
					while(true) {
						System.out.print("내용: "); 
						String valid = scanner.nextLine();
						if(valid.length()<=33) {
							fc.setFree_Cocontent(valid); break;
						} else System.out.println("글자수 33자 이하로 작성해주세요.");
					}
					fc.setUser_Id(id);
					fc.setFree_Codate(new Date()); 
					fc.setFree_Bno(selectFBN);
					
					BoardView.savePrint();
					String selectSave = scanner.nextLine();
					if(selectSave.equals("1")) {
						//저장
						try {
							String json = new JSONObject().put("command", "CreateFC").put("FC",fc).toString();
							chatClient.send(json);     //**클라이언트                    //**서버에서 디비 저장 실행하기
							System.out.println("댓글이 생성되었습니다.");
							
						}catch(Exception e) {
							e.printStackTrace();
							//exit();
						}
					}
				} else System.out.println("로그인 후 이용해 주세요.");
				startFB();
			}
			
			public void updateFB(String selectFBN, String user_id) {				
				try {
					if(id == user_id) {
						//자유 게시판 글 수정
						System.out.println("[게시글 수정]");
						Free_Board fb = new Free_Board();
						while(true) {
							System.out.print("제목: ");  
							String valid = scanner.nextLine();
							if(valid.length()<=10) {
								fb.setFree_Btitle(valid); break;
							} else System.out.println("글자수 10자 이하로 작성해주세요.");
						}
						while(true) {
							System.out.print("내용: "); 
							String valid = scanner.nextLine();
							if(valid.length()<334) {
								fb.setFree_Bcontent(valid); break;
							} else System.out.println("글자수 333자 이하로 작성해주세요.");
						}
						
						//**fb.setUser_Id(user_id);
						fb.setFree_Date(new Date()); 
						
						BoardView.savePrint();
						String selectSave = scanner.nextLine();
						if(selectSave.equals("1")) {
							//저장//실행
							try {
								String jsonUpdateFB = new JSONObject().put("command", "UpdateFB").put("Free_Bno", selectFBN).put("FB",fb).toString();
								chatClient.send(jsonUpdateFB);     //**클라이언트                    //**서버에서 디비 수정 실행하기
								System.out.println("게시글이 수정되었습니다.");
								
							}catch(Exception e) {
								e.printStackTrace();
							}
						}						
					} else System.out.println("권한이 없습니다.");
					startFB();
				} catch (Exception e) {
					e.printStackTrace();
				}    
				BoardView.board();
			}
			
			public void deleteFB(String selectFBN, String user_id) {			
				try {
					if(id == user_id) {
						System.out.println("[현재 게시글 삭제]");
						BoardView.savePrint();
						String selectSave = scanner.nextLine();
						if(selectSave.equals("1")) {
							try {
								String jsonDel = new JSONObject().put("command", "DeleteFB").put("Free_Bno", selectFBN).toString();
								chatClient.send(jsonDel);
								System.out.println("게시글이 삭제되었습니다.");
							} catch(Exception e) {
								e.printStackTrace();
							}
						}		
					} else System.out.println("권한이 없습니다.");
				} catch (Exception e) {
					e.printStackTrace();
				}    
				startFB();
			}
	//답글 수정/삭제
			public void updateFComment(String selectFBN) {
				System.out.println("[댓글 수정]");
				System.out.print("댓글 선택> ");
				String selCoNum = scanner.nextLine();
				JSONObject json = new JSONObject().put("command", "FCId").put("FBN", selectFBN).put("FCN", selCoNum).put("User_Id", id);
				//chatClient.send(json);
				JSONObject root = new JSONObject(a);       //chatClient.dis.readUTF());    //**boolean으로 받기
				if(root.getBoolean("Right_Id")) {
					Free_Comment fc = new Free_Comment();
					while(true) {
						System.out.print("내용: "); 
						String valid = scanner.nextLine();
						if(valid.length()<=33) {
							fc.setFree_Cocontent(valid); break;
						} else System.out.println("글자수 33자 이하로 작성해주세요.");
					}
					fc.setFree_Codate(new Date()); 
					fc.setFree_Bno(selectFBN);
						
					BoardView.savePrint();
					String selectSave = scanner.nextLine();
					if(selectSave.equals("1")) {
						//저장
						try {
							String jsonfcu = new JSONObject().put("command", "UpdateFC").put("FC",fc).toString();
							chatClient.send(jsonfcu);     //**클라이언트                    //**서버에서 디비 저장 실행하기
							System.out.println("댓글이 수정되었습니다.");	
						}catch(Exception e) {
							e.printStackTrace();
						}
					}						
				} else System.out.println("권한이 없습니다.");
				startFB();
			}
			
			public void deleteFComment(String selectFBN) {
				try {
					System.out.println("[댓글 삭제]");
					System.out.print("댓글 선택> ");
					String selCoNum = scanner.nextLine();
					JSONObject json = new JSONObject().put("command", "FCId").put("Free_Bno", selectFBN).put("FCN", selCoNum).put("User_Id", id);
					chatClient.send(json.toString());
					JSONObject root = new JSONObject(a);            //chatClient.dis.readUTF());    //**boolean으로 받기
					if(root.getBoolean("Right_Id")) {
						System.out.println("[현재 게시글 삭제]");
						BoardView.savePrint();
						String selectSave = scanner.nextLine();
						if(selectSave.equals("1")) {
							try {
								String jsonDel = new JSONObject().put("command", "DeleteFC").put("Free_Bno", selectFBN).put("FCN", selCoNum).toString();
								chatClient.send(jsonDel);
								System.out.println("댓글이 삭제되었습니다.");
							} catch(Exception e) {
								e.printStackTrace();
							}
						}		
						
					} else System.out.println("권한이 없습니다.");
				} catch (IOException e) {
					e.printStackTrace();
				}    
				startFB();
			}
				
			
			//delete r+q 통합?
	
	
	
	
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
