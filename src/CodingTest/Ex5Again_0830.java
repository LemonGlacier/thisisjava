package CodingTest;

import java.util.Scanner;

public class Ex5Again_0830 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		String[] Num = scn.nextLine().split(" ");
		int n = Integer.parseInt(Num[0]);
		int wn = Integer.parseInt(Num[1]);
		String[] mmrz= new String[n];
		String[] word= scn.nextLine().split(" "); 
		
		for(int i=0;i<n;i++) {
			for(int d=0; d<n; d++) {
				if(word[i].equals(mmrz[d])) {
					mmrz[d]= null; 
					/*for(int k=d+1;k<n;k++) {  //지운 거 이후로 하나씩 당겨주기 
						String chg= mmrz[k];
					      mmrz[k-1]= chg;   //mmrz하나씩 당겨주기
						 } //int k for문 끝*/
				}
			} 
			mmrz[i] = word[i];
			System.out.println("mmrz 값 검증1" +mmrz[i]);
		}
		System.out.println("mmrz 값 검증2" +mmrz[0]);
		System.out.println("mmrz 값 검증2" +mmrz[1]);
		System.out.println("mmrz 값 검증2" +mmrz[2]);
		if (n<wn) {
			for(int j=0;j<n;j++) {
			if(mmrz[j] == null) {
				mmrz[j]=word[n];
				break;
			} 
		}
			System.out.println("mmrz 값 검증3" +mmrz[0]);
			System.out.println("mmrz 값 검증3" +mmrz[1]);
			System.out.println("mmrz 값 검증3" +mmrz[2]);
		}
		
		

	}//main

}
