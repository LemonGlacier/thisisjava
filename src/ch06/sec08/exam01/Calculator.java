package ch06.sec08.exam01;

import java.util.Scanner;

public class Calculator {
	//리턴 값이 없는 메소드 선언
	void powerOn() {
		System.out.println("전원을 켭니다.");
	}
	void powerOff() {
		System.out.println("전원을 끕니다.");
	}
	
	//호출 시 두 정수 값을 전달받고 호출한 곳으로 결과값 int를 리턴하는 메소드 선언
	int plus(int x, int y) {
		int result = x + y;
		return result;          //리턴 값 지정;
	}
	
	//호출 시 두 정수 값을 전달받고 호출한 곳으로 결과값 int를 리턴하는 메소드 선언
	double divide(int x, int y) {
		double result = (double) x / (double) y;
		return result;          //리턴 값 지정;
	}
	
	//문제1 두 정수를 제공받아 첫째수의 둘째수 제곱을 리턴값으로 하는 메소드 선언
	int square(int x, int y) {
		int result=1;
		for(int i=0; i< y; i++) {
			result *=x;
		}
		return result;
	}
	
	//문제2 한 정수를 제공받고 팩토리얼의 값을 리턴하는 메소드 선언
	long facto(int x) {
		long result=1;
		for(int i=1; i<=x; i++) {
			result *=i;
		}
		return result;
	}
	
	//문제3 두 정수를 키보드에서 입력받고 첫번째수%두번째수 로 계산된 실수값을 출력하는 메소드 선언
	void divide2(int x, int y) {
		Scanner scn = new Scanner(System.in);
		String[] input = (scn.nextLine()).split(" ");
		x= Integer.parseInt(input[0]);
		y= Integer.parseInt(input[1]);
		double result = x%y;
		System.out.println(result);
	}
	
	//문제4 시작값과 뽑을 경우의 수(범위는 연속된 정수)를 입력받고 임의의 수를 뽑아 리턴하는 메소드 선언
	int randomNum(int x, int y) {
		Scanner scn = new Scanner(System.in);
		String[] input = (scn.nextLine()).split(" ");
		x= Integer.parseInt(input[0]);
		y= Integer.parseInt(input[1]);
		int result = (int)(Math.random()*y)+x;
		return result;
	}
}
