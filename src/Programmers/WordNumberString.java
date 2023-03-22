package Programmers;

public class WordNumberString {
	    public static int solution(String s) {     //저 int 가 리턴타입
	    	String[] word={"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	    	for(int i =0; i<10; i++) {
	    		s=s.replace(word[i], String.valueOf(i));
	    	}
	    	int answer=Integer.parseInt(s);
	        return answer;
	    }

	public static void main(String[] args) {
		String s = "2three45sixseven";
		System.out.println(solution(s));

	}

}
