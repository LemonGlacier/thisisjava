package Programmers;

import java.util.Arrays;

public class StealedClothes {
	    public static int solution(int n, int[] lost, int[] reserve) {
	        int answer = n-lost.length;
	        Arrays.sort(lost);
	        Arrays.sort(reserve);
	         for(int i=0; i<lost.length;i++) {
		            for(int j=0; j<reserve.length; j++) {
		                if(lost[i]==reserve[j]) {
		                    reserve[j]=-1;
	                        lost[i]=-1;
		                    answer++;
		                    break;
		                }
	                }
	         }
	        for(int i=0; i<lost.length;i++) {
		            for(int j=0; j<reserve.length; j++) {
		               if(Math.abs(lost[i]-reserve[j])==1) {
		                     answer++;
		                     reserve[j]=-1;
		                     break;
		               }    
		            }
		        }       
		        return answer;
		  }


	public static void main(String[] args) {
		int n = 3;
		int[] lost = {1,2};
		int[] reserve = {2,3};
		System.out.print(solution(n, lost, reserve));

	}

}
