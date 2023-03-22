package Team5_com.DTO;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.json.JSONObject;

public class Product_List {

	//클라이언트가 호출하는 메소드
	public static void getEntireList(ChatClient chatClient) {
		try {
			// 전체 상품 행수
			String sql_totalrow = "select count(*) from product ";
			JSONObject jsonObject_row = new JSONObject();
			jsonObject_row.put("sql", sql_totalrow);
			jsonObject_row.put("rsNum", 1);
			chatClient.send(jsonObject_row.toString());
			String json_row = chatClient.dis.readUTF();
			JSONObject root_row = new JSONObject(json_row);
			
			Page page = new Page();
			page.setTotalRow(root_row.getInt("1"));
			page.setCurrPage(2);
			Paging.setPageInfo(page, 1, 1);
			
			
			// System.out.println(page.getTotalRow());
			
			// 상품목록출력
			String sql = "select rownum," + "product_id, product_name, product_price " + "from product where rownum >="
					+ String.valueOf(page.getStartRow()) + " and rownum <= " + String.valueOf(page.getEndRow());
			// System.out.println(sql);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("sql", sql);
			jsonObject.put("rsNum", 4);
			String json = jsonObject.toString();
			chatClient.send(json);
			System.out.println("상품목록");
			System.out.println("--------------------------------------------------");

			Thread thread = new Thread(() -> {
				try {
					
					for (int i = 0; i < page.getEndRow(); i++) {

						String json2 = chatClient.dis.readUTF();
						JSONObject root = new JSONObject(json2);
						Product product = new Product();
						// DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						product.setProduct_Id(root.getString("2"));
						product.setProduct_Name(root.getString("3"));
						product.setProduct_Price(root.getInt("4"));
						/*
						 * product.setProduct_Content(root.getString("4")); try {
						 * product.setProduct_Date(sdf.parse(root.getString("5"))); } catch
						 * (JSONException e) { // TODO Auto-generated catch block e.printStackTrace(); }
						 * catch (ParseException e) { // TODO Auto-generated catch block
						 * e.printStackTrace(); } product.setProduct_Graphic_Card(root.getString("6"));
						 * product.setCPU(root.getString("7"));
						 * product.setMainboard(root.getString("8"));
						 * product.setOS(root.getString("9")); product.setMemory(root.getString("10"));
						 */
						// System.out.println(product);
						System.out.println("--------------------------------------------------");
						// System.out.println(product.getProduct_Id()+product.getProduct_Name()+
						// product.getProduct_Price());
						System.out.printf("%-6s%-12s%-12d \n", product.getProduct_Id(), product.getProduct_Name(),
								product.getProduct_Price());
						/*
						 * //-16s%-16s%-16s%-16s%-16s%-16s%-16s\n", product.getProduct_Content(),
						 * sdf.format(product.getProduct_Date()), product.getProduct_Graphic_Card(),
						 * product.getCPU(), product.getMainboard(), product.getOS(),
						 * product.getMemory()
						 */

						System.out.println("--------------------------------------------------");

					}
					
					Paging.printPaging(page);

				} catch (IOException e) {
					System.out.println("[클라이언트] 서버에 연결끊김");
					System.exit(0);
				}
			});
			thread.start();

		} catch (Exception e) {
			System.out.println("[클라이언트] 서버 연결 안됨");
		}

	}

	public static void main(String[] args) {

	}
}
