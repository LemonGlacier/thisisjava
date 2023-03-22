package teamproject.comProjectPP.View;

import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import teamproject.comProjectPP.DTO.Free_Board;
import teamproject.comProjectPP.DTO.Pager;
import teamproject.comProjectPP.client.Board_Function;
import teamproject.comProjectPP.client.Client;

public class FreeBoard_View {
	Scanner sc = new Scanner(System.in);
	String selectNumber;
	Board_Function boardFunction = new Board_Function();
	Client client;
	JSONObject jsonObject;
	JSONArray jsonArray;
	Free_Board free_Board= new Free_Board();
	Pager pager;
	
	public FreeBoard_View(Client client) {
		this.client = client;
	}
	
	
	public void startFB() {
		System.out.println("[자유 게시판] | [1. 게시글 작성] | [2. 게시글 목록] ");
		//System.out.println("[1. 게시글 작성] | ");
		System.out.println("------------------------------------------------------");    
		System.out.print("선택> ");
		selectNumber = sc.nextLine();
		
		if(selectNumber.equals("1")) {
			boardFunction.writeFb(client);
			
			
			
		} else if(selectNumber.equals("2")) {
			boardFunction.readFb(client);
			
			JSONObject root = new JSONObject(client.receive());
			JSONObject data = root.getJSONObject("data");
			JSONArray pl = data.getJSONArray("freeBoardList");
			System.out.println("[자유 게시판 게시글 목록]");
			System.out.println("--------------------------------------------------");
			System.out.printf("%-7s%-12s(%2s)%-10s%-16s\n", "|게시글 번호", "|   게시글 제목  ", "댓글","|   작성자", "|  작성 일자  ");
			
			
			for(int i = 0; i<pl.length(); i++) {
				JSONObject limitJop = pl.getJSONObject(i);
				free_Board.setFree_Bno(limitJop.getString("free_Bno"));
				free_Board.setFree_Btitle(limitJop.getString("free_Btitle"));
				free_Board.setFree_Bcontent(limitJop.getString("free_Bcontent"));
				free_Board.setFree_Date(limitJop.getString("free_Date"));
				free_Board.setUser_Id(limitJop.getString("user_Id"));
				free_Board.setFree_Comment_Num(1);//limitJop.getInt("free_Comment_Num"));
				
				System.out.printf(" %-7s%-20s(%2d) %-10s%-50s\n",free_Board.getFree_Bno(),
						free_Board.getFree_Btitle(),free_Board.getFree_Comment_Num(),free_Board.getUser_Id(), free_Board.getFree_Date());
			}
			
			System.out.println("[읽을 게시물 선택]");
			System.out.print("> : ");
			int selectNum =Integer.parseInt(sc.nextLine());
			pager.setPageNo(selectNum);
			
			
			
			
			
			
			
			
			

			
		}
		
		
		
	}
	
	
	
	
	
	
}
