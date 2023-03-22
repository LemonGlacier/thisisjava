package CodingTest;

import java.util.Scanner;

public class Ex4_0830 {

	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		int n = Integer.parseInt(scn.nextLine());
		String grd[] = scn.nextLine().split(" ");      //String[] grd
		String cyl1 = scn.nextLine();
		String cyl2 = scn.nextLine();
		int sum = 0;
		
		for(int i=0;i<n;i++) {
			char ox1 = cyl1.charAt(i);
			char ox2 = cyl2.charAt(i);
			boolean con1 = (ox1 == 'O');
			boolean con2 = (ox2 == 'O');
			if (con1 && con2) {
				sum += Integer.parseInt(grd[i]);
			}
		}
		System.out.println("총 점수\n");
		System.out.println(sum);
	}

}
