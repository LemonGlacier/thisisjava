package ch03.sec05;

import java.util.Scanner;

public class InfinityAndNaNCheckExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("x: ");
		int x = Integer.parseInt(scanner.nextLine());
		
		System.out.print("y: ");
		double y = Double.parseDouble(scanner.nextLine());
		
		double z = x / y;
		if(Double.isInfinite(z) || Double.isNaN(z)) {
			System.out.println("산출불가");
		} else {
			
			System.out.println("z: " + z);
			System.out.println("z + 2 : " + (z + 2));
			
		}
		
		//int result1 = 5/0;  //실행에러
		//double result2 = 5/0.0;  //정상 실행
		

	}

}
