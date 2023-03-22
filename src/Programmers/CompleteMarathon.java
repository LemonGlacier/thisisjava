package Programmers;

public class CompleteMarathon {
	 public static String solution(String[] participant, String[] completion) {
	        String answer = "";
	        for(int i=0; i<participant.length; i++) {
	            for(int j=0; j<completion.length; j++) {
	                if(participant[i].equals(completion[j])) {
	                    completion[j]=null;              //j값 구해서 중복인 경우 이름 나오게 할까
	                    participant[i]=null;
	                    break;                        //지워도 되지 않을까 했는데 p가 null이면 equals()가 안된대
	                }
	            }
	            if(participant[i] != null) {
	                 answer=participant[i];
	            }
	        } 
	        return answer;
	   }

	public static void main(String[] args) {
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = {"stanko", "ana", "mislav"};
		System.out.print(solution(participant, completion));
	}

}
