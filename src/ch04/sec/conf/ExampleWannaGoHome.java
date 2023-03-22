package ch04.sec.conf;

import java.util.Scanner;

public class ExampleWannaGoHome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = (int) (Math.random() *45) +1;
	
		System.out.println("오늘의 로또 번호: " + num);
		
		int score = (int)(Math.random()*20) +81;
		System.out.println("점수: " + score);
		
		String grade;
		
		if(score>=90) {
			System.out.println("-합격-");
			if (score >= 95) {
				grade = "A+";
			} else {
				grade = "A";
			}
			} else {
				System.out.println("-분발-");
				if(score>=85) {
					grade = "B+";
				} else {
					grade = "B";
				}
			}
			System.out.println("학점: " + grade);
		
			//switch문
			int nn = (int)(Math.random()*4) + 1;
			
			switch(nn) {
			case 1 :
				System.out.println("1번");
				break;
			case 2 :
				System.out.println("2번");
				break;
			case 3 :
				System.out.println("3번");
				break;
		    default :
			    System.out.println("4번");
						
			}
			
			switch(nn) {
			case 1,3 -> {
				 System.out.println("홀수");
			}
			case 2,4 -> {
				 System.out.println("짝수");
			}
			/*default -> {
				System.out.println("사실 없어");
			}*/
			}
			
			//for문
			int ss=0;
			int sm=0;
			for(int i=1;i<=100;i++) {
				ss = ss + i;
				sm += i;
			}
			System.out.println("1~100 합: " + ss);
			System.out.println("1~100 합: " + sm);
			
			for(int c=1; c<=2; c++) {
				System.out.println("\n~~" + c + "동~~");
			 for (int a =1; a <= 5; a++) {
				for(int b=1; b<=2; b++) {
					System.out.println(a + "층" + (a*100 + b) + "호");
				}
				}
			}
			
			//while문
			int p = 1;
			while(p<=10) {
				System.out.print(p + " ");
				p++;
			}
			
			//키콘트롤
			
			Scanner scn = new Scanner(System.in);
			boolean run = true;
			int tr = 0;
			
			while(run) {
				System.out.println("\n~*~*~*~*~*~*~*~*~*~*~*~*");
				System.out.println("1. 귀가 | 2. 공부 | 3. 종료");
				System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*");
				System.out.print("선택: ");
				
				String sN = scn.nextLine();
				
				if(sN.equals("1")) {
					tr--;
					System.out.println("피로도: " + tr*10);	
				} else if(sN.equals("2")) {
					tr++;
					System.out.println("피로도: " + tr*10);	
				} else if(sN.equals("3")) {
					run = false;
					
				}
				
			} System.out.println("끝"); 
			
	}

}
