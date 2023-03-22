package teamproject.comProjectPP.Service;

import java.util.ArrayList;

import teamproject.comProjectPP.DAO.DAO_Board;
import teamproject.comProjectPP.DTO.Free_Board;
import teamproject.comProjectPP.DTO.Review_Board;

public class Service_Board_Function {
	DAO_Board Db = new DAO_Board();
	Free_Board free_Board = new Free_Board();
	String Output;
	int totalRow;
	
	public int getTotalRow() {
		Db = new DAO_Board();
		totalRow = Db.getTotalRowFb();
		return totalRow;
	}
	
	
	public String writeFb(Free_Board free_Board) {
		Db = new DAO_Board();
		Output = Db.Insert(free_Board);
		return Output;
	}
	
	public ArrayList<Free_Board> readFb(String readFb) {
		ArrayList<Free_Board> list = new ArrayList<>();
		Db = new DAO_Board();
		list = Db.Read(readFb);
		return list;
	}
	
	//review
	public String Or(String user_Id) {
		return Db.ordered(user_Id);
	}
	
	public String writeRb(Review_Board rb) {
		return Db.Insert(rb);
	}
	
	public int getTRRb() {
		return Db.getTRRb();
	}
	
	public ArrayList<Review_Board> readRb(int pageNo) {
		ArrayList<Review_Board> list = new ArrayList<>();
		list = Db.readRb(pageNo);
		return list;
	}
	
	public String detailRb(String selectBno) {
		return Db.detailRb(selectBno);
	}
	
	public String deleteRb(String bno) {
		return Db.deleteRb(bno);
	}
	
	public String updateRb(Review_Board rb) {
		return Db.updateRb(rb);
	}
	
	
	
}
