package ch11.sec03.exam03;

import java.lang.reflect.Array;

public class ExceptionHandlingExample {

	public static void main(String[] args) {
		String[] array = {"100", "1oo", null, "200"};
		
		for(int i =0; i<=array.length;i++) {
			try {
				System.out.println(array[i].length());
				int value = Integer.parseInt(array[i]);
			} catch(NumberFormatException e) {
				System.out.println("숫자로 변환 불가");
			} catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
				System.out.println("문자열 없음");	
			} catch(Exception e) {
				System.out.println("예상치 못한 예외");
			}
		}
	}

}
