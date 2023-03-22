package ch05.sec05;

public class ReplaceExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String oStr = "자바 문자열은 불변입니다. 자바 문자열은 String입니다. ";
		String nStr = oStr.replace("자바","Java");
		
		System.out.println(oStr);
		System.out.println(nStr);

	}

}
