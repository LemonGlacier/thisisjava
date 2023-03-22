package Team5_com.BController;

import org.json.JSONObject;

import Team5_com.BService.BoardService;
import Team5_com.CC2.Board;

public class BoardController {
	//필드
	JSONObject jsonObject;
	String json;
	Board board;
	BoardService bs = new BoardService();
	
	//메소드
	public String InputJson (JSONObject jsonObject) {
		String command = jsonObject.getString("command");    //**이거 하나로 해야되는 거 아닌가
		
		switch(command) {
		case "readB" -> { bs.readB(jsonObject);
			/*switch(jsonObject.getString("BN")) {
			case "FB" ->{
				//서비스로
			}
			case "RB" ->
			case "QB" ->
			}*/
		}
		//case "createB" ->
		//case "updateB" ->
		//case "deleteB" ->
		}
		
		
		
		
		
		
		
		
		
		
		
		
		return json;
	}
	

}
