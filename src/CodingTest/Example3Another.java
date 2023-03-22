package CodingTest;

import java.util.Scanner;

public class Example3Another {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		String[] strNum = sc.nextLine().split(",");
		int multi = 1;
		
		for(int i = 0; i <strNum.length; i++) {
			multi *= Integer.parseInt(strNum[i]);
		}
		System.out.println(multi);
		
		String[] num = String.valueOf(multi).split("");
		int[] numCount = new int[10];
		
		for(int i = 0; i < num.length; i++) {
			for(int j = 0; j <10; j++) {
				if(num[i].equals(String.valueOf(j))) {
					numCount[j]++;
				}
			}
		}
		for(int n : numCount) {
			System.out.println(n);
		}

	}

}
