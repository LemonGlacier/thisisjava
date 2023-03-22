package ch17.sec08;

import java.util.Arrays;

public class LoopingExample {

	public static void main(String[] args) {
		int[] intArr = {1,2,3,4,5};
		
		//잘못 작성
		Arrays.stream(intArr)
		.filter(a->a%2==0)
		.peek(n->System.out.println(n));     //최종 처리가 없어서 동작 안 함
		
		int total = Arrays.stream(intArr)
				.filter(a->a%2==0)
				.peek(n->System.out.println(n))
				.sum();       //최종처리
		System.out.println("총합: " + total + "\n");
		
		//최종 처리 메소드 forEach() 이용해 반복 처리
		Arrays.stream(intArr)
		.filter(a->a%2==0)
		.forEach(n->System.out.println(n));

	}

}
