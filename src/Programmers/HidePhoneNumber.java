package Programmers;

import java.util.Scanner;

public class HidePhoneNumber {

	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		String pNum= scn.nextLine();
		for(int i = 0; i<pNum.length()-4;i++) {
			System.out.print("*");
		} 	
		System.out.println(pNum.substring(pNum.length()-4));
	}

}
