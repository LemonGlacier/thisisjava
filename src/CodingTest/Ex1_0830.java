package CodingTest;

import java.util.Scanner;

public class Ex1_0830 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.print("시작수: ");
		int sN = Integer.parseInt(scn.nextLine());
		System.out.print("끝수: ");
		int eN = Integer.parseInt(scn.nextLine());
		System.out.print("배수: ");
		int mN = Integer.parseInt(scn.nextLine());
		int sum = 0;
		
		for(int i=sN;i<=eN;i++) {
			if(i%mN == 0) {
				sum += i;
			}
		}
		System.out.println(sum);

	}

}
