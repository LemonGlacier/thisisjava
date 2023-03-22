package Programmers;

public class WordNumberString2 {
	    public static int solution(String s) {
	    	String stran = s.replace("one", "1");
	          stran = stran.replace("two", "2");
	          stran = stran.replace("three", "3");
	          stran = stran.replace("four", "4");
	          stran = stran.replace("five", "5");
	          stran = stran.replace("six", "6");
	          stran = stran.replace("seven", "7");
	          stran = stran.replace("eight", "8");
	          stran = stran.replace("nine", "9");
	          stran = stran.replace("zero", "0");
	        int answer=Integer.parseInt(stran);
	        return answer;
	    }

	public static void main(String[] args) {
		String s = "2three45sixseven";
		System.out.println(solution(s));

	}

}
