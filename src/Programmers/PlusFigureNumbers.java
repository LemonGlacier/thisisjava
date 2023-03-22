package Programmers;

public class PlusFigureNumbers {

	    public static int solution(int n) {
	        int answer = 0;
	        //for로 더할까 split으로 더할까
	        String[] figures = (String.valueOf(n)).split("");  
	        for(int i=0; i<figures.length; i++) {
	            answer += Integer.parseInt(figures[i]);    //char 안 더해지나
	        }
	        return answer;
	    }
	
	public static void main(String[] args) {
		int n = 987;
		System.out.print(solution(n) + " ");
		
	}
}
