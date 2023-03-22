package teamproject.comProjectPP.Service;

import teamproject.comProjectPP.DAO.DAO_Basket;
import teamproject.comProjectPP.DTO.Basket;
import teamproject.comProjectPP.DTO.Basket_Detail;

public class Service_Basket_Function {
	DAO_Basket Db = new DAO_Basket();
	String Output;
	
	public String Create_Basket(Basket basket ) {
		Db = new DAO_Basket();
		Output = Db.Create(basket);
		return Output;
	}
	
	
	
}
