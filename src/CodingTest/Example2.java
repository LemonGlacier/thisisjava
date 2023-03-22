package CodingTest;

import java.util.Scanner;

public class Example2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		String N = scanner.nextLine();
		String[] arr = N.split(",");
		int[] newArr = new int[arr.length];
		int sum = 0;
		int min = 0; // 초기화 값

		for (int i = 0; i < 7; i++) {
			newArr[i] = Integer.parseInt(arr[i]);
			if (newArr[i] % 2 != 0) {
				sum = sum + newArr[i];
			}
		}		
		for (int j = 0; j < 7; j++) {
			if (newArr[j] % 2 != 0) {
				min = newArr[j];
				break;
			} 
		}
		for (int k = 0; k < 7; k++) {
			if (newArr[k] % 2 != 0) {
				if (min > newArr[k]) {
					min = newArr[k];
				}
			}
		}
		
		if (sum == 0) {
			System.out.println("-1");
		} else {
			System.out.println(sum);
			System.out.println(min);
		}

	}

}
