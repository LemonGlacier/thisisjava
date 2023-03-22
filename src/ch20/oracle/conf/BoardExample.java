package ch20.oracle.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BoardExample {
	//Field
	private Scanner scanner = new Scanner(System.in);
	private Connection conn;
	private String loginId;
	
	//Constructor
	public BoardExample() {
		try {
		//JDBC Driver 등록
		Class.forName("oracle.jdbc.OracleDriver");
		
		//연결
		conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","java","oracle");
		} catch(Exception e) {
			e.printStackTrace();
			exit();
		}
	}
	
	//Method	
	public void list() {                                        
		//타이틀 및 컬럼명 출력
		System.out.println();
		System.out.println("[Board list]" + ((loginId != null)? ("User: " + loginId) : ""));
		System.out.println("---------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n","no","writer","date","title");
		System.out.println("---------------------------------------------------------");
		//System.out.printf("%-6s%-12s%-16s%-40s \n", "1", "winter","2022.01.27","welcome to the board");
		//System.out.printf("%-6s%-12s%-16s%-40s \n", "2", "winter", "2022.01.27","so cold in this winter");
		
		//boards 테이블에서 게시물 정보 가져와 출력
		try {
			String sql = "select bno, btitle, bcontent, bwriter, bdate from boards order by bno desc";  //매개변수화된 select문  //내림차순
			PreparedStatement pstmt = conn.prepareStatement(sql);                                  //컴파일된 sql문 인터페이스
			ResultSet rs = pstmt.executeQuery();                                         //select문에 기술된 컬럼으로 구성된 행 집합
			while(rs.next()) {                                      //데이터 행 여러 개(커서:beforeFirst>>...)
				Board board = new Board();                         //데이터 행을 읽고 Board 객체에 저장
				board.setBno(rs.getInt("bno"));                    //bno컬럼 값
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				System.out.printf("%-6s%-12s%-16s%-40s \n",
						board.getBno(),
						board.getBwriter(),
						board.getBdate(),
						board.getBtitle()
						);
			}                                                   //커서:afterLast
			rs.close();                                        //메모리 해제
			pstmt.close();                                     //PreparedStatement 닫기
			
		}catch(SQLException e) {
			e.printStackTrace();
			exit();
		}
		
		mainMenu();                                             //mainMenu() 메소드 호출(메뉴 출력)
	}
	
	public void mainMenu() {
		System.out.println();
		System.out.println("---------------------------------------------------------");
		if(loginId == null) {
			System.out.println("Main Menu: 1. Create | 2. Read | 3. Clear | 4. Join | 5. Exit");
			System.out.print("Select: ");
			String menuNo = scanner.nextLine();
			System.out.println();
			
			switch(menuNo) {
			case "1" -> create();
			case "2" -> read();
			case "3" -> clear();
			case "4" -> join();
			case "5" -> exit();
		}
		} else {
			System.out.println("Main Menu: 1. Create | 2. Read | 3. Clear | 4. Logout | 5. Exit");
			System.out.print("Select: ");
			String menuNo = scanner.nextLine();
			System.out.println();

			switch(menuNo) {
			case "1" -> create();
			case "2" -> read();
			case "3" -> clear();
			case "4" -> logout();
			case "5" -> exit();
			}
		}
	}

	private void exit() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		System.out.println("** Bye **");
		System.exit(0);
	}
	
	public void subMenu() {
		System.out.println("---------------------------------------------------------");
		System.out.println("Sub Menu: 1. Ok | 2. Cancel");
		System.out.print("Select: ");
	}
	
	public void login() {
		//입력 받기
		User user = new User();
		System.out.println("[Login]");
		System.out.print("Id: ");
		user.setUserId(scanner.nextLine());
		System.out.print("Password: ");
		user.setUserPassword(scanner.nextLine());
		
		//보조 메뉴 출력
		subMenu();
		String menuNo = scanner.nextLine();
		if(menuNo.equals("1")) {
			//boards 테이블에 정보 저장
			try {
				String sql = "select userpassword from users where userid= ? ";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUserId());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					String dbPassword = rs.getString("userpassword");
					if(dbPassword.equals(user.getUserPassword())) {
						loginId = user.getUserId();
					} else {
						System.out.println("Not matched pwd");
					}
				} else {
					System.out.println("No Id exists");
				}
				rs.close();
				pstmt.close();
				
			} catch(Exception e) {
				e.printStackTrace();
				exit();
			}
		}
		list();
	}
	
	public void logout() {
		//로그인 아이디 없애기
		loginId = null;
		
		//게시물 목록 출력
		list();
	}
	
	public void join() {
		User user = new User();
		System.out.println("[New Member]");
		System.out.print("ID: ");
		user.setUserId(scanner.nextLine());
		System.out.print("Name: ");
		user.setUserName(scanner.nextLine());
		System.out.print("PW: ");
		user.setUserPassword(scanner.nextLine());
		System.out.print("Age: ");
		user.setUserAge(Integer.parseInt(scanner.nextLine()));
		System.out.print("Email: ");
		user.setUserEmail(scanner.nextLine());
		
		//보조 메뉴 출력
		subMenu();
		String menuNo = scanner.nextLine();
		if(menuNo.equals("1")) {
			//boards 테이블에 게시물 정보 저장
			try {
				String sql = "insert into users (userid, username, userpassword, userage, useremail) values (?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getUserName());
				pstmt.setString(3, user.getUserPassword());
				pstmt.setInt(4, user.getUserAge());
				pstmt.setString(5, user.getUserEmail());
				pstmt.executeUpdate();
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
				exit();
			}
		}
		//게시물 목록 출력
		list();
	}

	private void clear() {
		System.out.println("[Delete all posts]");
		subMenu();
		String menuNo = scanner.nextLine();
		if(menuNo.equals("1")) {
			//boards 테이블에 게시물 정보 전체 삭제
			try {
				String sql = "truncate table boards";                                    //전체 삭제
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();				
			}catch(Exception e) {
				e.printStackTrace();
				exit();
			}
		}		
		list();
	}

	private void read() {
		System.out.println("[Read the post]");
		System.out.print("bno: ");
		int bno = Integer.parseInt(scanner.nextLine());          //입력된 게시글 번호
		
		//boards 테이블에서 해당 게시글 가져와 출력
		try {
			String sql = "select bno, btitle, bcontent, bwriter, bdate from boards where bno=?";  //매개변수화된 select문  //조건 bno=?
			PreparedStatement pstmt = conn.prepareStatement(sql);                                  //컴파일된 sql문 인터페이스
			pstmt.setInt(1, bno);                       //?에 bno 넣기 //조건 bno 컬럼값에 입력값
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {                           //bno = primary key
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				System.out.println("####################");
				System.out.println("No: "+board.getBno());
				System.out.println("Title: "+board.getBtitle());
				System.out.println("Content: "+board.getBcontent());
				System.out.println("Writer: "+board.getBwriter());
				System.out.println("Date: "+board.getBdate());
				System.out.println("####################");
				
				//보조 메뉴 출력
				if(loginId != null && loginId.equals(board.getBwriter())) {
					subMenu();
					String menuNo = scanner.nextLine();
					System.out.println();
					
					if(menuNo.equals("1")) {
						update(board);
					} else if(menuNo.equals("2")) delete(board);
					}	
			}
			rs.close();
			pstmt.close();			
		} catch(Exception e) {
			e.printStackTrace();
			exit();
		}
		//게시글 목록 출력
		list();
	}
	
	public void update(Board board) {
		//수정 내용 입력받기
		System.out.println("[modified items]");
		System.out.print("Title: ");
		board.setBtitle(scanner.nextLine());
		System.out.print("Content: ");
		board.setBcontent(scanner.nextLine());
		//System.out.print("Writer: ");
		//board.setBwriter(scanner.nextLine());
		
		//보조 메뉴 출력
		subMenu();
		String menuNo = scanner.nextLine();
		if(menuNo.equals("1")) {
			//boards테이블에서 게시물 정보 수정
			try {
				String sql = "update boards set btitle=?, bcontent=? where bno=?";  
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getBtitle());                  //1번째 ? (btitle)
				pstmt.setString(2, board.getBcontent());
				pstmt.setInt(3, board.getBno());
				pstmt.executeUpdate();                                //select:executeQuery() / insert,delete,update:executeUpdate()
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
				exit();
			}
		}
		//게시물 목록 출력
		list();
	}
	
	public void delete(Board board) {
		//boards 테이블에 게시물 정보 삭제
		try {
			String sql = "delete boards where bno=?";      //from 생략
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBno());
			pstmt.executeUpdate();
			pstmt.close();		 
		}catch(Exception e) {
			e.printStackTrace();
			exit();
		}
		//게시물 목록 출력
		list();
	}

	public void create() {
		//입력 받기
		Board board = new Board();           //입력 값 board 객체로 저장
		System.out.println("[make new post]");
		System.out.print("title: ");
		board.setBtitle(scanner.nextLine());
		System.out.print("content: ");
		board.setBcontent(scanner.nextLine());
		if(loginId == null) {
		System.out.print("writer: ");
		board.setBwriter(scanner.nextLine());
		} else {
			board.setBwriter(loginId);
		}
		//보조 메뉴 출력
		subMenu();
		String menuNo = scanner.nextLine();
		if(menuNo.equals("1")) {
			//boards 테이블에 게시물 정보 저장
			try {
				String sql = "insert into boards (bno, btitle, bcontent, bwriter, bdate) values (SEQ_BNO.NEXTVAL, ?, ?, ?, SYSDATE)";  
				                                                                       //매개변수화된 select문 //SEQ_BNO 시퀀스에서 가져올 번호 NEXTVAL  
				PreparedStatement pstmt = conn.prepareStatement(sql);                                  //컴파일된 sql문 인터페이스
				pstmt.setString(1, board.getBtitle());
				pstmt.setString(2, board.getBcontent());
				pstmt.setString(3, board.getBwriter());
				pstmt.executeUpdate();
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
				exit();				
			}
		}
		//게시물 목록 출력
		list();
	}

	public static void main(String[] args) {
		BoardExample boardExample = new BoardExample();             //BoardExample 객체 생성
		boardExample.list();                                       //list() 메소드 호출

	}

}
