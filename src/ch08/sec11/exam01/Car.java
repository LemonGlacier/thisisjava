package ch08.sec11.exam01;

public class Car {
	//필드
	Tire tire1 = new HankookTire();
	Tire tire2 = new HankookTire();
	
	//HankookTire tire1;    //이런 식으로 선언할 수도 있으나 타이어 객체 바꿀 수 없음
	
	//메소드
	void run() {
		tire1.roll();
		tire2.roll();
	}

}
