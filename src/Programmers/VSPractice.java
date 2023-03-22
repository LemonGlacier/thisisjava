package Programmers;

import java.util.Scanner;

public class VSPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		String s = scn.nextLine();
		boolean answer=true;
		boolean a = (s.length()==4);
		boolean b = false;
		for(int i=0; i<4;i++) {
			System.out.print(s.charAt(i));
			int V= s.charAt(i);
			if(V>'9') {
				b=false;
				break;
			} else {
				b=true;
			}
		}

	}

}
