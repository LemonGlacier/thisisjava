package Team5_com.BService;

import org.json.JSONObject;

import Team5_com.DAO.DAOBoard;

public class BoardService {
	//필드
	DAOBoard daob;
	
	//메소드
	public String readB(JSONObject json) {
		switch(json.getString("BN")) {
		//case "FB" -> daob.readFB();
		}
		String a = "";
		return a;
	}

}
