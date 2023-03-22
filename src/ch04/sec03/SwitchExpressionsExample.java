package ch04.sec03;

public class SwitchExpressionsExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char grade = 'B';
		
		switch(grade) {
		case 'A', 'a' -> {
			System.out.println("우수 회원");
		}
		case 'B', 'b' -> {
			System.out.println("일반 회원");
		}
		default -> {
			System.out.println("손님");
		}

		}
		
		switch(grade) {
		case 'A', 'a' -> System.out.println("우수 회원");
		case 'B', 'b' -> System.out.println("일반 회원");
		case 'C', 'c' -> System.out.println("손님");
		}

	}

}
