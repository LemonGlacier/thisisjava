package CodingTest;

import java.util.Scanner;

public class Example3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		String N = scn.nextLine();
		String[] arr = N.split(",");
		int[] newArr = new int[arr.length];
		
		for(int i=0;i<3;i++) {
			newArr[i] = Integer.parseInt(arr[i]);
		}
		
		//int mul = newArr[0] * newArr[1] * newArr[2];
		int mul = 1;               //곱하기라서 초기값1
		for(int k=0;k<3;k++) {
			mul *= newArr[k];
		}
		//System.out.println(mul);
		String strmul = String.valueOf(mul);
		String[] arr2 = strmul.split("");
		int count = 0;
		for(int j=0;j<10;j++) {
			for(int i=0;i<arr2.length;i++) {
				if(arr2[i].equals(String.valueOf(j))) {
					count++;
				}
			}
			System.out.println(count);
			count=0;
		}
		
		
		

	}

}
