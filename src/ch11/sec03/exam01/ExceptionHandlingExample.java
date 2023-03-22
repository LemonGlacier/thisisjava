package ch11.sec03.exam01;

import java.lang.reflect.Array;

public class ExceptionHandlingExample {

	public static void main(String[] args) {
		String[] array = {"100", "1oo"};
		
		for(int i =0; i<=array.length;i++) {
			try {
				System.out.println(array[i]);
				int value = Integer.parseInt(array[i]);
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("항목 수 부족");
			} catch(NumberFormatException e) {
				System.out.println("숫자로 변환 불가");
			}
		}

	}

}
