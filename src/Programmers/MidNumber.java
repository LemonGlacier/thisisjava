package Programmers;

import java.util.Scanner;

public class MidNumber {

	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		String solution = scn.nextLine();
        String answer = null;
        int mid = solution.length()/2;
        if(solution.length()%2==0) {   
            answer = String.valueOf(solution.charAt(mid-1)) + String.valueOf(solution.charAt(mid));
        } else {
        	answer = String.valueOf(solution.charAt(mid));
        }
        System.out.println(answer);

	}

}
