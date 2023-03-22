package ch20.oracle.sec12;

import java.util.Scanner;

public class BoardExample2 {
	//Field
	private Scanner scanner = new Scanner(System.in);
	
	//Constructor
	
	//Method	
	public void list() {                                         //게시물 목록 출력
		System.out.println();
		System.out.println("[Board list]");
		System.out.println("---------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n","no","writer","date","title");
		System.out.println("---------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s \n", "1", "winter","2022.01.27","welcome to the board");
		System.out.printf("%-6s%-12s%-16s%-40s \n", "2", "winter", "2022.01.27","so cold in this winter");
		mainMenu();                                             //mainMenu() 메소드 호출
	}
	
	public void mainMenu() {
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println("Main Menu: 1. Create | 2. Read | 3. Clear | 4. Exit");
		System.out.print("Select: ");
		String menuNo = scanner.nextLine();
		System.out.println();
		
		switch(menuNo) {
		case "1" -> create();
		case "2" -> read();
		case "3" -> clear();
		case "4" -> exit();
		}
	}
	
	private void exit() {
		System.exit(0);
	}

	private void clear() {
		System.out.println("*** clear() ***");
		list();
	}

	private void read() {
		System.out.println("*** read() ***");
		list();
	}

	public void create() {
		System.out.println("*** create() ***");
		list();
	}

	public static void main(String[] args) {
		BoardExample2 boardExample = new BoardExample2();             //BoardExample2 객체 생성
		boardExample.list();                                       //list() 메소드 호출

	}

}
