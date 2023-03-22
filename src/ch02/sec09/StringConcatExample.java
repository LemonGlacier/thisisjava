package ch02.sec09;

public class StringConcatExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int value = 1 + 2 + 3;
		System.out.println(value);
		
		String str1 = 1 + 2 + "3";
		System.out.println(str1);
		String str2 = 1 + "2" + 3;
		System.out.println(str2);
		String str3 = "1" + 2 + 3;
		System.out.println(str3);
		String str4 = "1" + (2 + 3);
		System.out.println(str4);
		

	}

}
