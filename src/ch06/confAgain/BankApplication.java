package ch06.confAgain;

import java.util.Scanner;

public class BankApplication {
	private static Scanner scn = new Scanner(System.in);
	private static Account[] balances= new Account[100];

	public static void main(String[] args) {
		int idx=0;
		
		boolean run = true;
		while(run) {
			System.out.println("----------------------------------");
			System.out.println("1.계좌생성|2.계좌목록|3.예금|4.출금|5.종료");
			System.out.println("----------------------------------");
			System.out.print("선택>");
			int answer = Integer.parseInt(scn.nextLine());
			System.out.println("------");
			switch(answer) {
			case 1: createAccount(); break;
			case 2: accountList(); break;
			case 3: deposit(); break;
			case 4: withdraw(); break;
			case 5: run=false;	break;
			}
			
		}
		System.out.println("프로그램을 종료합니다.");

	}
	
	public static void createAccount() {
		System.out.println("계좌생성");
		System.out.println("------");
		System.out.print("계좌번호: ");
		int num = Integer.parseInt(scn.nextLine());
		System.out.print("계좌주: ");
		String owner = scn.nextLine();
		System.out.print("초기입금액: ");
		int firstBal =Integer.parseInt(scn.nextLine());
		
		Account newAccount = new Account(num, owner, firstBal);
		for(int i =0; i<balances.length; i++) {
			if(balances[i] == null) {
				balances[i] = newAccount;
				System.out.println("계좌가 생성되었습니다.");
				break;
			}
		}
			
	}
	
	private static void accountList() {
		System.out.println("계좌목록");
		System.out.println("------");
		for(int i =0;i<100;i++) {
			Account account = balances[i];
			if(account != null) {
				System.out.print(account.getNum()+"      ");
				System.out.print(account.getOwner()+"      ");
				System.out.println(account.getBal()+"      ");
			}
		}
		
	}
	
	private static void deposit() {
		System.out.println("예금");
		System.out.println("------");
		System.out.print("계좌번호: ");
		int num = Integer.parseInt(scn.nextLine());
		System.out.print("예금액: ");
		int money = Integer.parseInt(scn.nextLine());
		Account account = findAccount(num);
		if(account == null) {
			System.out.println("계좌가 없습니다.");
			return;
		}
		account.setBal(account.getBal() + money);
		System.out.println("예금에 성공했습니다.");
	}
	
	private static void withdraw() {
		System.out.println("출금");
		System.out.println("------");
		System.out.print("계좌번호: ");
		int num = Integer.parseInt(scn.nextLine());
		System.out.print("출금액: ");
		int money = Integer.parseInt(scn.nextLine());
		Account account = findAccount(num);
		if(account == null) {
			System.out.println("계좌가 없습니다.");
			return;
		}
		account.setBal(account.getBal() - money);
		System.out.println("출금에 성공했습니다.");
	}
	
	private static Account findAccount(int num) {
		Account account = null;
		for(int i=0;i<balances.length;i++) {
			if(balances[i]!=null) {
				int dbNum=balances[i].getNum();
				if(dbNum==num) {
					account = balances[i];
					break;
				}
			}
		}
		return account;
	}

}
