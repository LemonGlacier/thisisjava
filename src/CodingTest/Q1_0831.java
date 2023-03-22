package CodingTest;

import java.util.Scanner;

public class Q1_0831 {

	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		String mt= scn.nextLine();
		int cal = 0;
		int sum = 0;
		int steep = 0;   
		boolean d = false;
		
		for(int i=0;i<mt.length();i++) {
			boolean a = (mt.charAt(i) == '(');
			boolean b = (mt.charAt(i) == ')');
			boolean c = (mt.charAt(i) == '#');			
			if(d) {                                 //#급경사 상태
				if(a) {                   
					sum++;
					steep=sum*2;
					cal +=steep;
				} else if(b) {
					steep=sum*2;
					cal +=steep;
					sum--;
				} else if(c) {
					d=false;                        //#급경사 끝
				}
			} else {
				if(a) {
					sum++;
					cal +=sum;
				} else if(b) {
					cal +=sum;
					sum--;
				} else if(c) { 
					d=true;                         //#급경사 시작
				}			
			}      			
		}       
		System.out.println(cal);

	} //main 끝

}
