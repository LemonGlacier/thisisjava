package ch08.sec11.exam02;

public class DriverExample {

	public static void main(String[] args) {
		//Driver 객체 생성
		Driver driver = new Driver();
		
		//Vehicle 구현 객체 생성
		Bus bus = new Bus();
		Taxi taxi = new Taxi();
		
		//매개값으로 구현 객체 대입
		driver.drive(bus);
		driver.drive(taxi);
	}

}
