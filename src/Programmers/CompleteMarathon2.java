package Programmers;

import java.util.Arrays;

public class CompleteMarathon2 {
	 public static String solution(String[] participant, String[] completion) {
	        String answer = "";
	        Arrays.sort(participant);
	        Arrays.sort(completion);
	        for(int i =0; i<completion.length; i++) {
	        	if(!participant[i].equals(completion[i])) {
	        		answer=participant[i];
	        		break;
	        	} else {
	        		answer=participant[completion.length];
	        	}
	        }
	        return answer;
	   }

	public static void main(String[] args) {
		String[] participant = {"leo", "kiki", "eden"};
		String[] completion = {"eden", "kiki"};
		System.out.print(solution(participant, completion));
	}

}
