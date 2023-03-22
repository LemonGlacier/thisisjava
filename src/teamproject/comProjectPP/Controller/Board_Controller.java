package teamproject.comProjectPP.Controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import teamproject.comProjectPP.DTO.Free_Board;
import teamproject.comProjectPP.DTO.Review_Board;
import teamproject.comProjectPP.Service.Service_Board_Function;

public class Board_Controller {
	JSONObject jsonObject;
	JSONArray jsonArray;
	Free_Board free_Board = new Free_Board();
	Review_Board rb = new Review_Board();
	String Output;
	String json;
	Service_Board_Function sbf = new Service_Board_Function();
	
	
	public String InputJson (JSONObject jsonObject) {
		String command =  jsonObject.getString("Command");
		
		switch(command) {
			case "writeFb" :
				free_Board.setUser_Id(jsonObject.getString("user_Id"));
				free_Board.setFree_Btitle(jsonObject.getString("free_Btitle"));
				free_Board.setFree_Bcontent(jsonObject.getString("free_Bcontent"));
				
				Output = sbf.writeFb(free_Board);
				
				if(Output.equals("success")) {
					jsonObject.put("Command", "Board_Function");
					jsonObject.put("message", "게시글 입력 완료");
					jsonObject.put("step", "next");
					json = jsonObject.toString();
					
				} else if( Output.equals("fail")) {
					jsonObject.put("Command", "Board_Function");
					jsonObject.put("message", "게시글 입력 오류");
					jsonObject.put("step", "retry");
					json = jsonObject.toString();
					
				} else {
					jsonObject.put("Command", "Board_Function");
					jsonObject.put("message", "error");
					json = jsonObject.toString();
				}
				break;
				
			case "readFb" :
				ArrayList<Free_Board> list = new ArrayList<>();
				list = sbf.readFb("readFb");
				
				JSONObject sendRoot = new JSONObject();
				sendRoot.put("result", "product");         //<<???
				JSONObject data = new JSONObject();
				JSONArray pl = new JSONArray();
				
				for( Free_Board F : list) {
					JSONObject limitJop = new JSONObject();
					limitJop.put("free_Bno", F.getFree_Bno());
					limitJop.put("free_Btitle", F.getFree_Btitle());
					limitJop.put("free_Bcontent", F.getFree_Bcontent());
					limitJop.put("free_Date", F.getFree_Date());
					limitJop.put("user_Id", "여기가 아니냐고");  //F.getUser_Id());
					limitJop.put("free_Comment_Num", 5);  //F.getFree_Comment_Num()
					
					pl.put(limitJop);
				}
				data.put("freeBoardList", pl);
				sendRoot.put("data",data);
				json = sendRoot.toString();
				
				break;
				
			case "choiceFb" : 
				//int totalRow = sbf.getTotalRow();
				//Pager pager = new Pager(5, 5, totalRow, jsonObject.getInt("pageNo"));
				break;
				
			case "writeRb" :
				rb.setUser_Id(jsonObject.getString("user_Id"));
				rb.setReview_Btitle(jsonObject.getString("review_Btitle"));
				rb.setReview_Bcontent(jsonObject.getString("review_Bcontent"));
				rb.setProduct_Name(jsonObject.getString("review_Product_Name"));
				rb.setReview_Rate(jsonObject.getInt("review_Rate"));
				rb.setOrder_Detail_Id(jsonObject.getString("order_Detail_Id"));
			
				Output = sbf.writeRb(rb);
				
				if(Output.equals("success")) {
					jsonObject.put("Command", "Board_Function");
					jsonObject.put("message", "게시글 입력 완료");
					jsonObject.put("step", "next");                     
					json = jsonObject.toString();
					
				} else if( Output.equals("fail")) {
					jsonObject.put("Command", "Board_Function");
					jsonObject.put("message", "게시글 입력 오류");
					jsonObject.put("step", "retry");
					json = jsonObject.toString();
					
				} else {
					jsonObject.put("Command", "Board_Function");
					jsonObject.put("message", "error");
					json = jsonObject.toString();
				}
				break;
				
			case "orderedRb" :
				json = sbf.Or(jsonObject.getString("user_Id"));		
				break;
				
			case "getTRRb" :
				json = jsonObject.put("trrb", sbf.getTRRb()).toString();
				break;
				
			case "readRb" :
				List<Review_Board> listR = new ArrayList<>();
				listR = sbf.readRb(jsonObject.getInt("pageNo"));
				
				JSONObject sendR = new JSONObject();
				//sendR.put("result", "product");         //<<???
				//JSONObject data = new JSONObject();
				JSONArray rl = new JSONArray();
				
				for(Review_Board R : listR) {
					JSONObject limitJop = new JSONObject();
					limitJop.put("review_Bno", R.getReview_Bno());
					limitJop.put("product_Name", R.getProduct_Name());
					limitJop.put("review_Btitle", R.getReview_Btitle());
					limitJop.put("review_Date", R.getReview_Date());
					limitJop.put("user_Id", R.getUser_Id());
					limitJop.put("review_Rate", R.getReview_Rate());
					limitJop.put("product_Name", R.getProduct_Name());
					
					rl.put(limitJop);
				}
				//data.put("freeBoardList", pl);
				sendR.put("RBList",rl);
				json = sendR.toString();
				break;
				
			case "detailRb" :
				json = sbf.detailRb(jsonObject.getString("selectBno"));				
				break;
				
			case "deleteRb" :			
				Output = sbf.deleteRb(jsonObject.getString("bno"));
				
				if(Output.equals("success")) {
					jsonObject.put("Command", "Board_Function");
					jsonObject.put("message", "게시글 입력 완료");
					jsonObject.put("step", "next");                      
					json = jsonObject.toString();
					
				} else if( Output.equals("fail")) {
					jsonObject.put("Command", "Board_Function");
					jsonObject.put("message", "게시글 입력 오류");
					jsonObject.put("step", "retry");
					json = jsonObject.toString();
					
				} else {
					jsonObject.put("Command", "Board_Function");
					jsonObject.put("message", "error");
					json = jsonObject.toString();
				}
				
				break;
				
			case "updateRb" :
				rb.setUser_Id(jsonObject.getString("user_Id"));
				rb.setReview_Btitle(jsonObject.getString("review_Btitle"));
				rb.setReview_Bcontent(jsonObject.getString("review_Bcontent"));
				rb.setReview_Rate(jsonObject.getInt("review_Rate"));
				rb.setReview_Bno(jsonObject.getString("review_Bno"));
				
				Output = sbf.updateRb(rb);
				
				if(Output.equals("success")) {
					jsonObject.put("Command", "Board_Function");
					jsonObject.put("message", "게시글 입력 완료");
					jsonObject.put("step", "next");                      
					json = jsonObject.toString();
					
				} else if( Output.equals("fail")) {
					jsonObject.put("Command", "Board_Function");
					jsonObject.put("message", "게시글 입력 오류");
					jsonObject.put("step", "retry");
					json = jsonObject.toString();
					
				} else {
					jsonObject.put("Command", "Board_Function");
					jsonObject.put("message", "error");
					json = jsonObject.toString();
				}
		}
		return json;
	}
	
	
	
	
	
	
}
