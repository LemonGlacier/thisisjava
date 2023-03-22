package ch12.sec05;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class BytesToStringExample {

	public static void main(String[] args) {
		String data = "자바";
		
		try {
		//String -> byte[] 배열 인코딩
			byte[] arr1 = data.getBytes("EUC-KR");
			System.out.println(arr1.length);
			System.out.println(Arrays.toString(arr1));
			
			//byte[] -> String 디코딩
			String data2 = new String(arr1,"EUC-KR" );   //생성자 사용
			System.out.println(data2);
			
	    } catch (UnsupportedEncodingException e) {
	    	e.printStackTrace();
	    }
	}
}
