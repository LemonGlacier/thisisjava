package TeamP.BulletinB;

import java.util.Calendar;
import java.util.Scanner;

public class NoticeBoard {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		boolean run = true;
		int boardNum = 0; // 게시글 번호
		String[][] boards = new String[100][5]; // String타입 배열 선언, 배열크기가 100, 4인 배열목록을 초기화(null) 저장.

		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int day = today.get(Calendar.DAY_OF_MONTH);
		
		int hour = today.get(Calendar.HOUR);
		int minute = today.get(Calendar.MINUTE);
		int second = today.get(Calendar.SECOND);

		/*while (run) {
			System.out.println("-----------------------------------------------");
			System.out.println("1.게시글 작성 | 2.게시글 목록 | 3.게시글 검색 | 4.종료 ");
			System.out.println("-----------------------------------------------");
			System.out.print("선택> ");

			int selectNum = Integer.parseInt(scanner.nextLine());
			System.out.println();

			switch (selectNum) {
			case 1: // 게시글 작성
				System.out.print("제목> ");
				boards[boardNum][0] = scanner.nextLine();

				System.out.print("내용> ");
				boards[boardNum][1] = scanner.nextLine();

				System.out.print("작성자> ");
				boards[boardNum][2] = scanner.nextLine();

				System.out.print("작성자 비밀번호> ");
				boards[boardNum][3] = scanner.nextLine();

				boards[boardNum][4] = year + "-" + month + "-" + day;
				System.out.println(boards[boardNum][4]);
				System.out.println();
				boardNum++;
				break;

			case 2: // 게시글 목록
				System.out.println("=================== 게시글 목록 ===================");
				System.out.println("No.\t 제목\t 작성자\t 작성날짜");
				for (int i = 0; (boards[i][0] != null) && (i < boards.length); i++) {
					System.out.printf("%d\t%s\t%s\t%s \n", i + 1, boards[i][0], boards[i][2], boards[i][4]);
				}
				System.out.println("================================================");
				System.out.println("메뉴로 돌아가려면 -1을 누르세요.");

				// 선택한 해당 게시물 내용 보기
				System.out.println("게시글 보기");
				System.out.print("게시글 선택(번호)> ");
				int num = Integer.parseInt(scanner.nextLine());
				System.out.println();

				if (num == -1) {
					break;
				} else if (boards[num - 1][0] != null) {
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
	                     
	               
	                     System.out.println();
	                     
	                  }
	                  System.out.println();
	                  

					// 선택한 해당 게시물 내용 삭제
					System.out.println("게시글을 삭제하시겠습니까?");
					System.out.println("1.예 2.아니요");
					int deleteProcess = Integer.parseInt(scanner.nextLine());
					
					if (deleteProcess == 1) {
						System.out.print("비밀번호 입력> ");
						String pw = scanner.nextLine();
						if (pw == user[boardNum]) {
							boards[boardNum][] = null;
						
					/*do {
						System.out.print("비밀번호 입력> ");
						String pw = scanner.nextLine();
					} while ( pw == user[boardNum]) { 
						boards[boardNum][] = null;
						
					}*/
					/*
						} else {
							break;
						}
					} else {
						break;
					}
					
					
					
					
		//끝			
				} else {
					System.out.println("해당 게시글이 없습니다.");
				}
				break;

			case 3: //

				break;

			case 4:
				run = false;
			}
		}
		System.out.println("프로그램 종료");*/

	}
}