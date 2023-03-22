package Programmers;

public class WinLottery {
	
	    public static int[] solution(int[] lottos, int[] win_nums) {
	        int[] answer = {0, 0};           //int[] answer = new int[2];
	        //for문 돌려서 찾을까 split은 돼있는 건가
	        //일단 일치하는 거 찾고 0 개수 세서 최고 순위는 일치개수+0/최저는 일치개수
	        int count = 0;
	        int zeroCount = 0;
	        //String win = "6654321";
	        int[] win = {6,6,5,4,3,2,1};
	        for(int i=0; i<6; i++) {
	            for(int j=0; j<6; j++) {
	                if(lottos[i] == win_nums[j]) {
	                    count++;
	                } 
	            }
	            if(lottos[i] == 0) {
	                    zeroCount++;
	            }
	        }
	           // answer[0]=Integer.parseInt(String.valueOf(win.charAt(count+zeroCount)));
	           // answer[1]=Integer.parseInt(String.valueOf(win.charAt(count)));	
	        answer[0]=win[count+zeroCount];
	        answer[1]=win[count];
	        return answer;
	 }

	public static void main(String[] args) {
		int[] lottos= {44, 1, 0, 0, 31, 25};
		int[] win_nums = {31, 10, 45, 1, 6, 19};
		for(int i:solution(lottos, win_nums)) {
			System.out.print(i + " ");
		}

	}

}
