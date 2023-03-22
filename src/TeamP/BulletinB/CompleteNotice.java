package TeamP.BulletinB;

	import java.util.Calendar;
	import java.util.Scanner;

	public class CompleteNotice {

		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			
			boolean run = true;
			int boardNum = 0;  // 게시글 번호
			String[][] boards = new String[100][5]; //String타입 배열 선언, 배열크기가 100, 5인 배열목록을 초기화(null) 저장.
			
			Calendar today = Calendar.getInstance();
			int year = today.get(Calendar.YEAR);
			int month = today.get(Calendar.MONTH) + 1;
			int day = today.get(Calendar.DAY_OF_MONTH);
			int hour = today.get(Calendar.HOUR);
			int minute = today.get(Calendar.MINUTE);
			
			while(run) {
				System.out.println("-----------------------------------------------");
				System.out.println("1.게시글 작성 | 2.게시글 목록 | 3.게시글 검색 | 4.종료 ");
				System.out.println("-----------------------------------------------");
				System.out.print("선택> ");
				
				int selectNum = Integer.parseInt(scanner.nextLine());
				System.out.println();
				
				switch(selectNum) {
					case 1:  // 게시글 작성
						System.out.print("제목> ");
						boards[boardNum][0] = scanner.nextLine();
						
						System.out.print("내용> ");
						boards[boardNum][1] = scanner.nextLine();
						
						System.out.print("작성자> ");
						boards[boardNum][2] = scanner.nextLine();
						
						System.out.print("작성자 비밀번호> ");
						boards[boardNum][3] = scanner.nextLine();
						
						boards[boardNum][4] = year + "." + month + "." + day + " " + hour + "시 " + minute + "분";
						System.out.println(boards[boardNum][4]);
						System.out.println();
						
						boardNum++;
						
						break;
						
					case 2: // 게시글 목록
						System.out.println("=================== 게시글 목록 ===================");
						System.out.println("No.\t 제목\t 작성자\t 작성날짜");
						
						for(int i = 0; i < boards.length; i++) {
							if(boards[i][0] == null) 
								continue;
							else
								System.out.printf("%d\t%s\t%s\t%s \n", i+1, boards[i][0], boards[i][2], boards[i][4]);
						}
						System.out.println("================================================");
						System.out.println("메뉴로 돌아가려면 -1을 누르세요.");
						
						//선택한 해당 게시물 내용 보기
						System.out.println("게시글 보기");
						System.out.print("게시글 선택(번호)> ");
						int num = Integer.parseInt(scanner.nextLine());
						System.out.println();
						
						if(num == -1) {
							break;
						} else if(boards[num-1][0] != null) {
							System.out.println("< " + num + "번 게시물 >");
							System.out.println("제목: " + boards[num-1][0]);
							System.out.println("내용: " + boards[num-1][1]);
							System.out.println("작성자: " + boards[num-1][2]);
							System.out.print("1.수정 2.삭제 > ");
							int selectUpdateOrDelete = Integer.parseInt(scanner.nextLine());
							
							//선택한 해당 게시글 내용 수정
							if(selectUpdateOrDelete == 1) {
								System.out.print("제목> ");
								String title = scanner.nextLine();
								
								System.out.print("내용> ");
								String content = scanner.nextLine();
								
								System.out.print("작성자> ");
								String writer = scanner.nextLine();
								
								System.out.print("작성자 확인 비밀번호 입력> ");
								String user = scanner.nextLine();
								if(user.equals(boards[num-1][3])) {
									boards[num-1][0] = title;
									boards[num-1][1] = content;
									boards[num-1][2] = writer;

								} else {
									System.out.println("잘못된 비밀번호입니다.");
								}
						
							}
							System.out.println();
							
							//선택한 해당 게시글 내용 삭제
							if(selectUpdateOrDelete == 2) {
								System.out.print("작성자 확인 비밀번호 입력> ");
								String user = scanner.nextLine();
								if(user.equals(boards[num-1][3])) {
									for(int i=0; i<boards[num-1].length; i++) {
										boards[num-1][i] = null;
									}
								} else {
									System.out.println("잘못된 비밀번호입니다.");
								}
						
								System.out.println();
							}
						} else {
							System.out.println("해당 게시글이 없습니다.");
						}
						break;
						
					case 3: // 게시글 검색
						System.out.println("============= 검색 방법 =============");
						System.out.println("1.제목 검색 | 2. 작성자 검색 | 3.날짜 검색");
						System.out.println("===================================");
						System.out.print("선택>");
						
						String strNum = scanner.nextLine();
						
						System.out.println();
						if(strNum.equals("1")) {
							// 제목으로 검색
							System.out.print("제목 검색>");
							String search = scanner.nextLine();
							
							System.out.println("================================================");
							System.out.println("No.\t 제목\t 작성자\t 작성날짜");
							
							for(int i=0;i<boards.length;i++) {
								if(boards[i][0] != null && boards[i][0].contains(search)) {
									System.out.printf("%d\t%s\t%s\t%s \n", i+1, boards[i][0], boards[i][2], boards[i][4]);
								}	
							}
							
							System.out.println("================================================");

						} else if(strNum.equals("2")) {
							// 작성자로 검색
							System.out.print("작성자 검색>");
							String search = scanner.nextLine();
							
							System.out.println("================================================");
							System.out.println("No.\t 제목\t 작성자\t 작성날짜");
							
							for(int i=0;i<boards.length;i++) {
								if(boards[i][0] != null && boards[i][2].contains(search)) {
									System.out.printf("%s\t%s\t%s\t%s \n", boardNum, boards[i][0], boards[i][2], boards[i][4]);
								}	
							}
							
							System.out.println("================================================");
						
						} else if(strNum.equals("3")) {
							System.out.print("날짜 검색>");
							String search = scanner.nextLine();
							
							System.out.println("================================================");
							System.out.println("No.\t 제목\t 작성자\t 작성날짜");
							
							for(int i=0;i<boards.length;i++) {
								if(boards[i][0] != null && boards[i][4].contains(search)) {
									System.out.printf("%d\t%s\t%s\t%s \n", i+1, boards[i][0], boards[i][2], boards[i][4]);
								}	
							}
							
							System.out.println("================================================");
						
						}
						
						break;
					
					case 4: // 종료
						run = false;
				}
			} 
			System.out.println("프로그램 종료");

		}

	}

