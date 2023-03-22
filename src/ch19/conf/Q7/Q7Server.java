package ch19.conf.Q7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q7Server {
	private static Scanner scn = new Scanner(System.in);
	private static List<Product> list;
	private static int no;

	public static void main(String[] args) throws Exception{
		
		while(true) {
			System.out.println("[상품 목록]");
			System.out.println("---------------------------------------");
			System.out.println("no\t name\t\t price\t stock");
			System.out.println("---------------------------------------");
			System.out.println("---------------------------------------");
			System.out.println("메뉴: 1. Create | 2. Update | 3. Delete | 4. Exit");
			System.out.print("선택: ");
			String answer = scn.nextLine();
			
			switch(answer) {
			case "1" : create7(); break;
			case "2" : update7(); break;
			case "3" : delete7(); break;
			default : exit7(); 
			}
		}
		//scn.close();
		

	}

	private static void create7() {
		Product pr = new Product();
		System.out.println("[상품 생성]");
		pr.setNo(no);
		System.out.print("상품 이름: ");
		pr.setProductName(scn.nextLine());
		System.out.print("상품 가격: ");
		pr.setProductPrice(Integer.parseInt(scn.nextLine()));
		System.out.print("상품 재고: ");
		pr.setProductStock(Integer.parseInt(scn.nextLine()));
		subMenu();
		if(scn.nextLine().equals("1")) {
			list.add(pr);	
			no++;
		}
	}

	private static void subMenu() {
		System.out.println("---------------------------------------");
		System.out.println("저장: 1. 예 | 2. 아니오 ");
		System.out.println("---------------------------------------");		
	}

	private static void update7() {
		System.out.println("[상품 수정]");
		System.out.print("상품 번호: ");
		int findNo =Integer.parseInt(scn.nextLine());
		for(Product i : list) {
			if(i.getNo()==findNo) {
				Product pr = new Product();
				System.out.print("상품 이름: ");
				pr.setProductName(scn.nextLine());
				System.out.print("상품 가격: ");
				pr.setProductPrice(Integer.parseInt(scn.nextLine()));
				System.out.print("상품 재고: ");
				pr.setProductStock(Integer.parseInt(scn.nextLine()));
				subMenu();
				if(scn.nextLine().equals("1")) {
					i = pr;	
				}
			}		
		}	
	}

	private static void delete7() {
		System.out.println("[상품 삭제]");
		System.out.print("상품 번호: ");
		int findNo =Integer.parseInt(scn.nextLine());
		for(Product i : list) {
			if(i.getNo()==findNo) {
				System.out.print("상품 번호 ["+findNo+"]를 삭제하시겠습니까?");
				subMenu();
				if(scn.nextLine().equals("1")) {
					list.remove(i);
				}
			}
		}
		
	}

	private static void exit7() {
		System.out.print("시스템을 종료합니다.");
		System.exit(0);
	}

}
