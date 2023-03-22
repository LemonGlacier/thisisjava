package TeamP.BulletinB;

import java.util.Calendar;
import java.util.Scanner;

public class TPBulletinBoardExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		boolean run = true;
		String title = null;
		String content = null;
		String writer = null;
		
		String[][] board = new String[100][5];
		
		while(run) {
			System.out.println("--------------------------------------------------");
			System.out.println("1. 게시글 작성 | 2. 게시글 목록 | 3. 게시글 검색 | 4. 종료");
			System.out.println("--------------------------------------------------");
			System.out.print("선택> ");
			
			String strNum = scanner.nextLine(); 
			
			if (strNum.equals("1")) {
				System.out.println(" [게시글 작성 중] ");
				System.out.print(" 제목> ");
				title = scanner.nextLine();
				
				System.out.print(" 내용> ");
				content = scanner.nextLine();
				
				System.out.print(" 작성자> ");
				writer = scanner.nextLine();
				
				System.out.print(" 작성 일자> ");
				Calendar now = Calendar.getInstance();
				int year = now.get(Calendar.YEAR);
				int month = now.get(Calendar.MONTH) + 1;
				int day = now.get(Calendar.DAY_OF_MONTH);
				int week = now.get(Calendar.DAY_OF_WEEK);
				
				int hour = now.get(Calendar.HOUR);
				int minute = now.get(Calendar.MINUTE);
				int second = now.get(Calendar.SECOND);
				System.out.print(year + "." + month + "." + day);
				switch (week) {
					case 1 : System.out.print("(일) ");
						break;
					case 2 : System.out.print("(월) ");
					    break;
					case 3 : System.out.print("(화) ");
					    break;
					case 4 : System.out.print("(수) ");
					    break;
					case 5 : System.out.print("(목) ");
					    break;
					case 6 : System.out.print("(금) ");
					    break;
					case 7 : System.out.print("(토) ");
					    break;
				}
				System.out.println(hour + ":" + minute + ":" + second);
				//분 두 자리 변환? %02d
				
				//board = new int[stN];
				
			} else if(strNum.equals("2")) {
				
				//게시글 보기
				System.out.println(" 제목> " + title);
				System.out.println(" 내용> " + content);
				System.out.println(" 작성자> " + writer);
				System.out.println(" 작성 일자> ");
				
			} else if(strNum.equals("3")) {
				
				System.out.println("--------------------------------------------------");
				System.out.println("1.  | 2. | 3.  | 4. ");
				System.out.println("--------------------------------------------------");
				System.out.print("선택> ");

				
			}
		}

	}

}
