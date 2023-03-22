package ch05.sec.conf;

import java.util.Scanner;

public class Example9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		boolean run = true;
		int stN = 0;
		/*int scores0 = 0;
		int scores1 = 0;
		int scores2 = 0;
		int sscc =0;*/
		
		int[] student = null;
		
		while(run) {
			System.out.println("--------------------------------------------------");
			System.out.println("1. 학생수 | 2. 점수입력 | 3. 점수리스트 | 4. 분석 | 5.종료");
			System.out.println("--------------------------------------------------");
			System.out.print("선택> ");
			
			String strNum = scanner.nextLine();        //바로 int로 받을 수도 있는 듯
			
			if (strNum.equals("1")) {
				System.out.print("학생수> ");
				/*String strNum2 = scanner.nextLine();
				stN = Integer.parseInt(strNum2);*/
				stN = Integer.parseInt(scanner.nextLine());
				
				student = new int[stN];
				
			} else if(strNum.equals("2")) {
						
				for(int i=0; i<student.length; i++) {
					
					System.out.print("scores[" + i + "]> ");
					
					student[i] = Integer.parseInt(scanner.nextLine());
				}
				
			}
			
			/*{
				System.out.print("scores[0]> ");
				String strNum3 = scanner.nextLine();
				scores0 = Integer.parseInt(strNum3);
				
				System.out.print("scores[1]> ");
				String strNum4 = scanner.nextLine();
				scores1 = Integer.parseInt(strNum4);
				
				System.out.print("scores[2]> ");
				String strNum5 = scanner.nextLine();
				scores2 = Integer.parseInt(strNum5);
				
			} */
			else if(strNum.equals("3")) {
				for(int i=0; i<stN; i++) {
				System.out.println("scores[" + i + "]" + student[i]);
				}

				/*System.out.println("scores[0]:  " + scores0);
				System.out.println("scores[1]:  " + scores1);
				System.out.println("scores[2]:  " + scores2);*/
				
			} else if(strNum.equals("4")) {
				
				int m =0;
				int sum =0;

				for( int i =0; i<student.length ; i++) {
					if(m<student[i]) {
						m = student[i];
					}
				} 
				
				System.out.println("최고 점수:  " + m);
				
				for( int i =0; i<student.length ; i++) {
					sum += student[i];
				} 
				
				//int avg = (scores0+scores1+scores2) / array.length;
				//int avg = (scores0+scores1+scores2) / stN;
				int avg = sum / stN;
				System.out.println("평균 점수:  " + avg );
				
			}else if(strNum.equals("5")) {
					run = false;
				}
			
			
		}
		System.out.println("프로그램 종료");


	}

}
