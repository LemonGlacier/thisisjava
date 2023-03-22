package ch04.sec.conf;

public class Example4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		while (true) {
			int n1 = (int)(Math.random()*6) + 1;
			int n2 = (int)(Math.random()*6) + 1;
			
			System.out.println("(" + n1 + "," + n2 +")");
			if (n1 +n2 == 5) {
				break;
			}
		}
		System.out.println("종료");

	}

}
