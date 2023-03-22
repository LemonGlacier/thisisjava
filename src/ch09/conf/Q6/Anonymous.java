package ch09.conf.Q6;

public class Anonymous {
	Vehicle field = new Vehicle() {   //Anonymous 클래스 Vehicle 타입 필드에 익명 구현 객체 대입

		@Override
		public void run() {
			System.out.println("자전거가 달립니다.");	
		}
	};
	
	void method1() {                  //메소드 로컬 변수 초기값에 익명 구현 객체 대입
		Vehicle localVar = new Vehicle() {

			@Override
			public void run() {
				System.out.println("승용차가 달립니다.");
			}
		};
		localVar.run();      //메소드 실행내용
	}
	
	void method2(Vehicle v) {    //매개값을 가지는 메소드 선언(실행 클래스에서 매개값에 익명구현객체 대입할 거임)
		v.run();
	}
}
