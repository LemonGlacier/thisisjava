package CodingTest;

import java.util.Scanner;

public class Ex5AAA_0830 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		String[] Num = scn.nextLine().split(" ");
		int n = Integer.parseInt(Num[0]);
		int wn = Integer.parseInt(Num[1]);
		String[] mmrz= new String[n];
		String[] word= scn.nextLine().split(" "); 
		int memory = 0;                       //다 찬 메모리
		int time = 0;
		
		for(int i =0; i<wn; i++) {
			boolean dup = false;               //중복 검증
			int loc = 0;                       //위치 0 초기화
			for(int j =0; j<memory;j++) {       //같은 거 있는지 대조하기 위해 돌리기
				if(mmrz[j].equals(word[i])) {   //같은 거 있을 때
					dup = true;                 //중복 true
					loc =j;                     //중복 위치 j
					break;                      //같은 거 있는지 돌리는 for문 나가기
				}                               //같은 거 있을 때 할 것들
			}                                   //j 변수 for문 끝
			if(dup) {
				time++;                          //아는 거라 +1초
				for(int f=loc;f<memory-1;f++) {   //중복 위치부터 memory까지 mmrz재배치
					mmrz[f] = mmrz[f+1];          //한 칸씩 당김
				}
				mmrz[memory-1] = word[i];         //끝자리에 word저장
			} else {
				time +=3;                         //모르는 거라 +3초
				if(memory == n) {                 //메모리가 없을 때
					int sum=0;
					for(int k=0;k<n; k++) {
						sum += mmrz[k].length();  //mmrz항목들 문자길이 합
					}
					int avg = sum/n;
					for(int h=0;h<n;h++) {          //문자 대체할 자리를 위한 for문
						if(mmrz[h].length()<=avg) {  //문자 길이가 평균보다 짧으면
							System.out.println("**" + mmrz[h]);
							System.out.println("**" + avg);
							for(int f=h;f<memory-1;f++) {   //대체 위치부터 memory까지 mmrz재배치
								mmrz[f] = mmrz[f+1];          //한 칸씩 당김
							}
							mmrz[memory-1] = word[i];         //끝자리에 word저장
							break;                   //문자 대체 자리 for문 나가기
						}
					}
				} else {                        //메모리가 있을 때
					mmrz[memory++] = word[i];   //mmrz에 word값 저장
				}
			}
			for (int g = 0; g < memory; g++) {
	            System.out.print(mmrz[g] + " ");  //mmrz값 검증
			}
			System.out.println("- " + time);      //time값 검증

		}
		System.out.println(time);                //답안 출력
	}
}
