package ch06.conf;

import java.util.Scanner;

public class BankApplication {
	private static Account[] ac = new Account[100];
	private static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {
		//int num = 0;
		run: while(true) {
			System.out.println("-------------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
			System.out.println("-------------------------------------------");
			System.out.print("선택> ");
			String choice = scn.nextLine();  
			//int select = scanner.nextInt();
			System.out.println("-------------");
			switch(choice) {
			case "1":
				createAc();
				break;
			case "2" :
				listAc();
				break;
			case "3" :
				deposit();
				break;
			case "4" :
				withdraw();
				break;
			case "5" :
				break run;
			}
		}
		System.out.println("프로그램 종료");
	}
		
		//계좌 생성
		private static void createAc() {
				System.out.println("계좌생성");
				System.out.println("-------------");
				System.out.print("계좌번호: ");
				String accountN = scn.nextLine();
				//String accountN = scn.next();
				System.out.print("계좌주: ");
				String owner = scn.nextLine();
				System.out.print("초기입금액: ");
				int firstD = Integer.parseInt(scn.nextLine());
				
				Account newAc = new Account(accountN, owner, firstD);
				for(int i =0; i < 100; i++) {
					if(ac[i] == null) {
						ac[i] = newAc;
						System.out.println("결과: 계좌가 생성되었습니다.");
						break;
					}
				
			}
				/* ac[num] = newAccount;
				 * num++;
				 */
		}
		//계좌 목록
		private static void listAc() {
			System.out.println("계좌목록");
			System.out.println("-------------");
			for(int i =0; i<100; i++) {
				Account acc = ac[i];
				if(acc != null) {
					System.out.print(acc.getAccountN() + "     ");
					System.out.print(acc.getOwner() + "     ");
					System.out.println(acc.getFirstD() + "     ");
				}
			}
		}
		//예금
		private static void deposit() {
			System.out.println("예금");
			System.out.println("-------------");
			System.out.print("계좌번호: ");
			String accountN = scn.nextLine();
			System.out.print("예금액: ");
			int money = Integer.parseInt(scn.nextLine());
			Account acc = findAcc(accountN);  
			if(acc == null) {
				System.out.println("결과: 계좌가 없습니다.");
				return;
			}
			acc.setFirstD(acc.getFirstD() + money);
			System.out.println("결과: 예금이 성공하였습니다.");
		}
		//출금
		private static void withdraw() {
			System.out.println("출금");
			System.out.println("-------------");
			System.out.print("계좌번호: ");
			String accountN = scn.nextLine();
			System.out.print("출금액: ");
			int money = Integer.parseInt(scn.nextLine());
			
			Account acc = findAcc(accountN);
			if(acc == null) {
				System.out.println("결과: 계좌가 없습니다.");
				return;
			}
			acc.setFirstD(acc.getFirstD() - money);
			System.out.println("결과: 출금이 성공하였습니다.");
		}
		//account 찾기
		private static Account findAcc(String accountN) {
			Account acc = null;
			for(int i = 0; i <100; i++) {
				if(ac[i] != null) {
					String dbAN = ac[i].getAccountN();
					if(dbAN.equals(accountN)) {
						acc = ac[i];
						break;
				}
			}
			
		}
		return acc;

	}

}
