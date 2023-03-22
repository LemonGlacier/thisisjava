package Programmers;

public class ProgrammersApply {
	
	  public static int[] solution(int[] arr) {
	        int[] answer = new int[1];
			int minloc=0;
			int min=arr[0];
	        for(int j=0;j<arr.length; j++) {
				if(min>arr[j]) {
					min=arr[j];
					minloc=j;
				}
			}
			if(arr.length == 1){
	            answer[0]=-1;
			} else {
	            answer = new int[arr.length-1];
				for(int k=0; k<minloc; k++) {
					   answer[k]=arr[k];
					}
	            for(int h=minloc; h<answer.length; h++) {
					   answer[h]=arr[h+1];   
					}            
			}
	         return answer;
	    }

	public static void main(String[] args) {
		int[] arr= {1,4,5,7};
		for(int i:solution(arr)) {
			System.out.print(i + " ");
		}
	}

}
