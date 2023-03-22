package ch06.sec11.exam01;

public class KoreanExample {

	public static void main(String[] args) {
		// 객체 생성 시 주민번호와 이름 던달
		Korean k1 = new Korean("123456 - 134567", "감자바");
		
		//필드값 읽기
		System.out.println(k1.nation);
		System.out.println(k1.ssn);
		System.out.println(k1.name);
		
		//final 필드는 값 변경 불가
		//k1.nation = "USA";
		
		k1.name= "김자바";

	}

}
