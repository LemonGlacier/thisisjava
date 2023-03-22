package ch08.sec11.exam02;

public class Driver {
	void drive(Vehicle vehicle) {      //매개변수를 인터페이스 타입으로 선언(구현 객체 대입)
		vehicle.run();
	}

}
