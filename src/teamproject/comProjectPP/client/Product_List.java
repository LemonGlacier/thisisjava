package teamproject.comProjectPP.client;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import teamproject.comProjectPP.DTO.Page;
import teamproject.comProjectPP.DTO.Product;
import teamproject.comProjectPP.DTO.Product_Detail;

public class Product_List implements Paging {
	public Page page;
	public Client client;

	public Product_List(Client client) {
		this.client = client;

		page = new Page();
		page.setCurrPage(1);
	}

	public void getEntireList() throws IOException {
		Product[] product = null;
		try {

			// setPageInfo(page, 5, 5);
			// 상품목록출력
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("Command", "ProductList");
			JSONObject jd = new JSONObject();
			jd.put("pageNo", page.getCurrPage());
			jsonObject.put("data", jd);

			String json = jsonObject.toString();
			client.send(json);
			System.out.println("상품목록");
			System.out.println("--------------------------------------------------");
			System.out.printf("%-6s%-6s%-12s%-12s\n", "번호", "상품번호", "상품명", "가격");

			String json2 = client.receive();
			// 파싱필요
			JSONObject root = new JSONObject(json2);
			JSONObject data = root.getJSONObject("data");
			JSONArray pl = data.getJSONArray("productList");
			JSONObject pager = data.getJSONObject("page");
			page.setTotalRow(pager.getInt("totalRow"));
			page.setRowsPerPage(pager.getInt("rpp"));
			page.setPagePerGroup(pager.getInt("ppg"));

			setPageInfo(page);
			product = new Product[page.getEndRow() - page.getStartRow() + 1];
			
			for (int i = 0; i < pl.length(); i++) {

				JSONObject pro = pl.getJSONObject(i);

				product[i] = new Product();

				product[i].setProduct_Id(pro.getString("product_id"));
				product[i].setProduct_Name(pro.getString("product_name"));
				product[i].setProduct_Price(pro.getInt("product_price"));

				System.out.println("--------------------------------------------------");
				System.out.printf("%-6s", (i + 1) + " ");
				System.out.printf("%-8s%-12s%-12d \n", product[i].getProduct_Id(), product[i].getProduct_Name(),
						product[i].getProduct_Price());

				System.out.println("--------------------------------------------------");

			}

			printPaging(page);

		} catch (Exception e) {
			System.out.println("[클라이언트] 서버 연결 안됨");
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		String select = sc.nextLine();
		try {
			if (Integer.parseInt(select) <= page.getRowsPerPage()) {
				// System.out.println(product[Integer.parseInt(select)-1].getProduct_Id());
				product_detail(product[Integer.parseInt(select) - 1].getProduct_Id());
			}
		} catch (Exception e) {
			// e.printStackTrace();
			switch (select) {
			case "n":
				page.setCurrPage(page.getCurrPage() + 1);

				getEntireList();
				break;
			case "p":
				page.setCurrPage(page.getCurrPage() - 1);
				getEntireList();
				break;
			case "pp":
				page.setCurrPage(page.getStartPage() - 1);
				getEntireList();
				break;
			case "nn":
				page.setCurrPage(page.getEndPage() + 1);
				getEntireList();
				break;
			case "ppp":
				page.setCurrPage(1);
				getEntireList();
				break;
			case "nnn":
				page.setCurrPage(page.getTotalPage());
				getEntireList();
				break;
			}
		}

	}

	public void product_detail(String product_Id) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Command", "ProductContent");

		jsonObject.put("Product_Id", product_Id);

		String json = jsonObject.toString();

		try {
			client.send(json);
			String receiveJson = client.receive();
			JSONObject root = new JSONObject(receiveJson);
			Product product = new Product();
			// 상품명, 가격, 설명, 그래픽카드, cpu, 메인보드,os,ram

			product.setProduct_Name(root.getString("Product_Name"));
			product.setProduct_Price(root.getInt("Product_Price"));
			product.setProduct_Content(root.getString("Product_Content"));
			product.setProduct_Graphic_Card(root.getString("Product_Graphic_Card"));
			product.setCPU(root.getString("CPU"));
			product.setMainboard(root.getString("Mainboard"));
			product.setOS(root.getString("OS"));
			product.setMemory(root.getString("Memory"));

			System.out.println("상품명 : " + product.getProduct_Name());
			System.out.println("가격 : " + product.getProduct_Price());
			System.out.println("설명 : " + product.getProduct_Content());
			System.out.println("그래픽 카드 :" + product.getProduct_Graphic_Card());
			System.out.println("cpu : " + product.getCPU());
			System.out.println("메인보드 : " + product.getMainboard());
			System.out.println("운영체제 : " + product.getOS());
			System.out.println("RAM : " + product.getMemory());
			// 옵션
			Scanner sc = new Scanner(System.in);
			System.out.println("1.장바구니에 담기 2.뒤로가기");
			System.out.print("입력) ");

			if (sc.nextLine().equals("1")) {
				jsonObject = new JSONObject();
				jsonObject.put("Command", "ProductOption1");

				jsonObject.put("Product_Id", product_Id);
				json = jsonObject.toString();
				client.send(json);
				System.out.println();
				System.out.println("옵션 선택");
				System.out.println("---------------------------------------------------");
				System.out.print("1. 색상 : ");
				do {
					receiveJson = client.receive();
					root = new JSONObject(receiveJson);
					Product_Detail pd = new Product_Detail();

					pd.setDetail_Color(root.getString("Detail_Color"));

					System.out.print("[" + pd.getDetail_Color() + "]");
				} while (root.getBoolean("isExist"));
				System.out.println();
				System.out.print("색상입력 : ");
				String color = sc.nextLine();
				jsonObject = new JSONObject();
				jsonObject.put("Command", "ProductOption2");

				jsonObject.put("Product_Id", product_Id);
				jsonObject.put("Color", color);

				json = jsonObject.toString();
				client.send(json);
				System.out.print("2. 용량 : ");
				do {
					receiveJson = client.receive();
					root = new JSONObject(receiveJson);
					Product_Detail pd = new Product_Detail();

					pd.setDetail_Capacity(root.getString("Detail_Capacity"));

					System.out.print("[" + pd.getDetail_Capacity() + "]");
				} while (root.getBoolean("isExist"));
				System.out.println();
				System.out.print("용량입력 : ");
				String capacity = sc.nextLine();
				System.out.println("장바구니에 추가");
			} else {
				getEntireList();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
