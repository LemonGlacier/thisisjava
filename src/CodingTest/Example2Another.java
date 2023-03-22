package CodingTest;

import java.util.Scanner;

public class Example2Another {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		String num = scn.nextLine();
		//배열 선언
		int[] arr = new int[7];    //홀수값 저장할 배열
		String[] arr1 = num.split(",");
		//변수 선언
		int sum = 0;
		int min = 0;
		
		for(int i=0; i<arr.length;i++) {
			arr[i] = Integer.parseInt(arr1[i]);
			if(arr[i]%2 == 1) {
				sum += arr[i];
				if(min == 0) {    //한번 실행
					min = arr[i];
				}
			}
		}
		//짝수일때 조건문
		if (sum == 0) {
			System.out.println("-1");
		} else {
			System.out.println(sum);
			System.out.println(min);
			
		}

	}

}
