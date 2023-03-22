package ch20.oracle.sec12;

public class BoardExample1 {
	//Field
	
	//Constructor
	
	//Method	
	public void list() {                                         //게시물 목록 출력
		System.out.println();
		System.out.println("[Board list]");
		System.out.println("---------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s \n","no","writer","date","title");
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
		System.out.println();
	}

	public static void main(String[] args) {
		BoardExample1 boardExample = new BoardExample1();             //BoardExample1 객체 생성
		boardExample.list();                                       //list() 메소드 호출
	}
}
