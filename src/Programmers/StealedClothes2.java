package Programmers;

public class StealedClothes2 {
	    public static int solution(int n, int[] lost, int[] reserve) {
	        int answer = n;
	        int[] student = new int[n];
	        for(int i=0; i<n;i++) {
	        	student[i]=1;       //굳이 1 안 넣고 1~-1 해도 될 듯
	        	for(int j=0; j<reserve.length; j++) {
	        		if(student[i] == reserve[j]) {
	        			student[i]++;
	        		}
	        	}
	        	for(int j=0; j<lost.length; j++) {
	        		if(student[i] == lost[j]) {
	        			student[i]--;
	        		}
	        	}
	        }
	        for(int i=0; i<n; i++) {
	        	if(student[i]==0) {
	        		if(i!=0 && student[i-1]==2) {
	        			student[i]=1;
	        			student[i-1]=1;
	        		} else if(i !=n-1 && student[i+1]==2) {
	        			student[i]=1;
	        			student[i-1]=1;
	        		} else {
	        			answer--;
	        		}
	        	}
	        }       
		        return answer;
		  }


	public static void main(String[] args) {
		int n = 5;
		int[] lost = {2,4};
		int[] reserve = {1,3,5};
		System.out.print(solution(n, lost, reserve));

	}

}
