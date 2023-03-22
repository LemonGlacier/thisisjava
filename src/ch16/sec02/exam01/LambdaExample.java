package ch16.sec02.exam01;

public class LambdaExample {

	public static void main(String[] args) {
		Person person = new Person();
		
		//실행문 두 개
		person.action(() -> {
			System.out.println("출근합니다.");
			System.out.println("프로그래밍합니다.");
		});
		
		//실행문 한 개
		person.action(()->System.out.println("퇴근합니다."));

	}

}
