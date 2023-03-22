package ch06.sec08.exam01;

public class CalculatorExample {

	public static void main(String[] args) {
		//Calculator 객체 생성
		Calculator myCalc = new Calculator();
		
		//리턴값이 없는 메소드 호출
		myCalc.powerOn();
		
		//plus메소드 호출 시 5와 6을 매개값으로 제공하고 덧셈결과를 리턴 받아 result1변수에 대입
		int result1 = myCalc.plus(5, 6);
		System.out.println("result1: " + result1);
		
		int x = 10;
		int y = 4;
		//divide() 메소드 호출 시 변수 x와 y의 값을 매개값으로 제공, 나눗셈 결과를 리턴 받아 result2 변수에 대입
		double result2 = myCalc.divide(x, y);
		System.out.println("result2: " + result2);
		
		//리턴값이 없는 메소드 호출
		myCalc.powerOn();
		
		//문1
		int a1= myCalc.square(x, y);
		System.out.println("a1: " + a1);
		
		//문2
		long a2 = myCalc.facto(x);
		System.out.println("a2: " + a2);
		
		//문3
		myCalc.divide2(x, y);
		
		//문4
		int a4 = myCalc.randomNum(x,y);
		System.out.println("a4: " + a4);
	}
}
