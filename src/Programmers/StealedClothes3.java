package Programmers;

public class StealedClothes3 {
	    public static int solution(int n, int[] lost, int[] reserve) {
	        int answer = n;
	        int[] student = new int[n];
	        for(int i: reserve) {
	        	student[i-1]++;          //왜 1을 빼는가 데이터값으로 세나
	        }
	        for(int i: lost) {
	        	student[i-1]--;     
	        }
	        for(int i=0; i<n; i++) {
	        	if(student[i]==0) {
	        		if(i!=0 && student[i-1]==1) {
	        			student[i]=0;
	        			student[i-1]=0;
	        		} else if(i !=n-1 && student[i+1]==1) {
	        			student[i]=0;
	        			student[i-1]=0;
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
