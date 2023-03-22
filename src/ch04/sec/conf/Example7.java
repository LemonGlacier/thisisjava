package ch04.sec.conf;

import java.util.Scanner;

public class Example7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		boolean run = true;
		int money = 0;
		
		while(run) {
			System.out.println("----------------------------------");
			System.out.println("1. 예금 | 2. 출금 | 3. 조회 | 4. 종료");
			System.out.println("----------------------------------");
			System.out.print("선택> ");
			
			String strNum = scanner.nextLine();
			
			if (strNum.equals("1")) {
				System.out.print("예금액> ");
				//String strNum2 = scanner.nextLine();
				money = money + Integer.parseInt(scanner.nextLine());
				System.out.println("잔고> " + money);
			} else if(strNum.equals("2")) {
				System.out.print("출금액> ");
				String strNum3 = scanner.nextLine();
				money = money - Integer.parseInt(strNum3);
				System.out.println("잔고> " + money);
			} else if(strNum.equals("3")) {

				System.out.println("잔고> " + money);
			} else if(strNum.equals("4")) {
				run = false;
			}
		}
		System.out.println("프로그램 종료");

	}


}
