package teamproject.comProjectPP.Controller;

import org.json.JSONObject;

import teamproject.comProjectPP.DTO.Basket;
import teamproject.comProjectPP.DTO.Basket_Detail;
import teamproject.comProjectPP.Service.Service_Basket_Function;

public class Basket_Controller {
	JSONObject jsonObject;
	Basket basket = new Basket();
	Basket_Detail basket_detail = new Basket_Detail();
	Service_Basket_Function sbf = new Service_Basket_Function();
	String Output;
	String json;
	
	public String InputJson (JSONObject jsonObject) { 
		
		String command =  jsonObject.getString("Command");
		
		switch(command) {
		
		case "createBasket" :
			basket.setUser_Id(jsonObject.getString("id"));
			Output = sbf.Create_Basket(basket);
			
			if(Output.equals("success")) {
				jsonObject.put("controller", "basket_Function");
				jsonObject.put("message", "장바구니 생성 완료");
				json = jsonObject.toString();
			} else {
				jsonObject.put("controller", "basket_Function");
				jsonObject.put("message", "장바구니 생성 실패");
				json = jsonObject.toString();
			}
			break;
			
			
			
		case "deleteBasket" :
			
			
			
			break;
			
			
		}
		
		return json;
	}
	
	
	
	
}
