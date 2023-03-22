package ch02.sec07;

public class PromotionExample {

	public static void main(String[] args) {
		// 자동타입변환
		byte byteValue = 10;
		int iV = byteValue;
		System.out.println("intValue: " + iV);
		
		char cV = '가';
		iV = cV;
		System.out.println("가의 유니코드: " + iV);
		
		iV = 50;
		long longValue = iV;
		System.out.println("longValue: " + longValue);
		
		longValue = 100;
		float floatValue = longValue;
		System.out.println("floatValue: " + floatValue);
		
		floatValue = 100.5f;
		double doubleValue = floatValue;
		System.out.println("doubleValue: " + doubleValue);
		
				

	}

}
