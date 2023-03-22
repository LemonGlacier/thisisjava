package ch06.sec10.exam02;

public class Television {
	//필드 선언
	static String company = "My Company";
	static String model = "LCD";
	static String info;
	
	//정적 블록
	static {
		System.out.println("1");
		info = "정보: " + company;
		info += "-" + model;
	}
	
	static {
		System.out.println("2");		
	}

}
