package Programmers;

import java.util.Scanner;

public class VerityString {

	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		String s = scn.nextLine();
		boolean answer=(s.length()==4 || s.length()==6);    //answer=true; 하고 if문에 조건 써도 됨
		if(answer) {
			for(int i=0; i<s.length();i++) {
				if(s.charAt(i)>'9') {             //char타입 ''쓰기!!
					answer=false;
					break;
				} 
			}
		}
		System.out.println(answer);

	}

}
