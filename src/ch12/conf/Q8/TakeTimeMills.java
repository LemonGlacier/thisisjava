package ch12.conf.Q8;

public class TakeTimeMills {

	public static void main(String[] args) {
		long time1 = System.nanoTime();
		int[] scores = new int[1000];
		for(int i =0; i<scores.length; i++) {
			scores[i] = i;
		}
		
		int sum = 0;
		for(int score : scores) {
			sum += score;
		}
		
		double avg = sum/scores.length;
		System.out.println(avg);
		long time2 = System.nanoTime();
		System.out.println(time2 - time1);

	}

}
