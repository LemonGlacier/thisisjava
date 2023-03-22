package ch05.sec05;

public class EqualsExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String sV1 = "홍길동";
		String sV2 = "홍길동";
		
		if(sV1 == sV2) {
			System.out.println("sV1과 sV2는 참조가 같음");
		} else {
			System.out.println("sV1과 sV2는 참조가 다름");
		}
		
		if(sV1.equals(sV2)) {
			System.out.println("sV1과 sV2는 문자열이 같음");
		}
		
		String sV3 = new String("홍길동");
		String sV4 = new String("홍길동");
		
		if(sV3 ==sV4) {
			System.out.println("sV3과 sV4는 참조가 같음");
		} else {
			System.out.println("sV3과 sV4는 참조가 다름");
		}
		
		if(sV3.equals(sV4)) {
			System.out.println("sV3과 sV4는 문자열이 같음");
		}

	}

}
