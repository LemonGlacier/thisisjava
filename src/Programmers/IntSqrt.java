package Programmers;

public class IntSqrt {

	    public static long solution(long n) {
	        long answer = 0;
	        long i=(long)Math.sqrt(n);
	        //for(long i =1; i<=Math.sqrt(n);i++) {
	            if((i*i) == n) {
	                answer=(i+1)*(i+1);
	            } else {
	                answer=-1;
	            }
	        //}
	        return answer;
	    }


	public static void main(String[] args) {
		long n = 121;
		System.out.println(solution(n));

	}

}
