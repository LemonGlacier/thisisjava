package CodingTest;

import java.util.Scanner;

public class Ex3_0830 {

	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		String strP = scn.nextLine();
		
		for(int i =0; i<strP.length(); i++) {
			char charP =strP.charAt(i);
			if(charP+4>'z') {
				charP=(char) (charP+4-26);
			} else {
				charP=(char) (charP+4);
			}
			System.out.print(charP);
		}
		
	}

}
