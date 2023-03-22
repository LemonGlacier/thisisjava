package CodingTest;

import java.util.Scanner;

public class Ex5AgainAgain_0830 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		      Scanner sc = new Scanner(System.in);
		      String[] word = sc.nextLine().split(" ");
		      int n = Integer.parseInt(word[0]);
		      int w = Integer.parseInt(word[1]);
		      word = sc.nextLine().split(" ");
		      String[] remember = new String[n];
		      int len = 0;
		      int time = 0;

		      for (int i = 0; i < w; i++) {
		         // 현재 탐색중인 단어를 내가 알고있는지
		         boolean know = false;
		         int idx = 0;
		         for (int j = 0; j < len; j++) {
		            if (remember[j].equals(word[i])) {
		               know = true;
		               idx = j;
		               break;
		            }
		         }

		         if (know) {
		            time++;
		            // 알고 있는 단어를 최신화 시키는 과정
		            for (int j = idx; j < len - 1; j++) {
		               remember[j] = remember[j + 1];
		            }
		            remember[len - 1] = word[i];

		         } else {
		            time += 3;
		            // 기억 용량이 다 찬 경우
		            if (len == n) {
		               int sum = 0;
		               for (int j = 0; j < len; j++) {
		                  sum += remember[j].length();
		               }
		               int avg = sum / len;
		               for (int j = 0; j < len; j++) {
		                  if (remember[j].length() <= avg) {
		                     remember[j] = word[i];
		                     break;
		                  }
		               }
		            }
		            // 여유 있는 경우
		            else {
		               remember[len++] = word[i];
		            }
		         }
		         for (int k = 0; k < len; k++) {
		            System.out.print(remember[k] + " ");
		         }
		         System.out.println("- " + time);

		      }
		      System.out.println(time);

	}//

}
