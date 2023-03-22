package Programmers;

public class FindKim {
	public static String solution(String[] seoul) {
        int n = 0;
        for(int i=0; i<seoul.length;i++) {
            if(seoul[i].equals("Kim")) {
                n=i;
            }
        }  
        return "김서방은 " + n + "에 있다";
    }

	public static void main(String[] args) {
		String[] seoul= {"Jane", "Kim"};
		System.out.print(solution(seoul));
	}

}
