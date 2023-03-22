package Programmers;

import java.util.Scanner;

public class ppp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		String a = scn.nextLine();
		int b = a.length();
		System.out.println("a길이 " + b);
		System.out.println("a길이/2: " + b/2);
		System.out.println("중간값 " + a.charAt(b/2));

	}

}
