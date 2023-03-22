package teamproject.comProjectPP.server;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import teamproject.comProjectPP.DTO.Page;
import teamproject.comProjectPP.DTO.Product;

public class ProductController {
	SocketClient socketClient;
	public ProductController(SocketClient socketClient) {
		this.socketClient=socketClient;
	}
	
	void handleJson(JSONObject root) {
		ProductService ps  = new ProductService();
		int pageNo = root.getInt("pageNo");
		Page page = new Page(5,5,ps.getTotalRow(),pageNo);
		//Page page = new Page(5,5,25,pageNo);
		//여기서 행이 추가 된다면?
		List<Product> list = ps.getList(page);
		JSONObject sendRoot = new JSONObject();
		sendRoot.put("result", "product");
		JSONObject data = new JSONObject();
		JSONArray pl = new JSONArray();
		for(Product p:list) {
			JSONObject jop=new JSONObject();
			
			jop.put("product_id", p.getProduct_Id());
			jop.put("product_name", p.getProduct_Name());
			jop.put("product_price", p.getProduct_Price());
			
			pl.put(jop);
		}
		data.put("productList", pl);
		JSONObject pager = new JSONObject();
		pager.put("totalRow",page.getTotalRow());
		pager.put("rpp", page.getRowsPerPage());
		pager.put("ppg", page.getPagePerGroup());
		data.put("page", pager);
		root.put("data", data);
		String json=root.toString();
		socketClient.send(json);
	}
}
