package ch08.sec10.exam02;

public class Bus implements Vehicle {

	@Override
	public void run() {
		System.out.println("bus runs");
		
	}
	public void checkFare() {
		System.out.println("check fare");
	}

}
