package Programmers;

import java.util.Scanner;

public class RemoveMinimum {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String[] strAnswer=scn.nextLine().split(",");        //배열분리
		int[] answer = new int[strAnswer.length];            //int 배열       
		int minloc=0;                                        //최소값 위치 초기화
		int min=Integer.parseInt(strAnswer[0]);              //최소값 인덱스0으로 초기화
		for(int i=0; i<strAnswer.length; i++) {
		   answer[i]=Integer.parseInt(strAnswer[i]);         //배열 타입 int 바꿈        
		}
		for(int j=0;j<answer.length; j++) {                  //min값 찾기
			if(min>answer[j]) {
				min=answer[j];
				minloc=j;                                    //최소값 위치
			}
		}
		if(answer.length == 1){                              //입력 배열 길이 1일때
			answer[0]=-1;
			System.out.println(answer[0]);
		} else {
			int[] newAnswer = new int[strAnswer.length-1];
			for(int k=0; k<minloc; k++) {                    //minloc없이 항목>min 비교할 수도 있음
				   newAnswer[k]=answer[k];
				   System.out.print(newAnswer[k]+","); 
				}
			for(int k=minloc+1; k<strAnswer.length; k++) {
				   newAnswer[k-1]=answer[k];
				   System.out.print(newAnswer[k-1]+",");     //배열의 출력은 어떻게 하는가
				}                                            //Array.sort로 오름차순 비교한 사람도 있는데 원래대로 돌리는 법을 안 배움
		}
	}

}
