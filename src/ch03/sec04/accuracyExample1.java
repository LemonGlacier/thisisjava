package ch03.sec04;

public class accuracyExample1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int apple = 1;
		double pU = 0.1;
		int number = 7;
		
		double result = apple - number * pU;
		System.out.println("사과 1개에서 남은 양: " + result);
		
		double r2 = (apple * 10 - number) * pU;
		System.out.println("사과 1개에서 남은 양2: " + r2);
		
		double r3 = (apple * 10 - number)/10.0;
		System.out.println("사과 1개에서 남은 양3: " + r3);

	}

}
