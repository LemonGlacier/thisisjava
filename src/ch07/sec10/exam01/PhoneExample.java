package ch07.sec10.exam01;

public class PhoneExample {

	public static void main(String[] args) {
		// Phone phone = new Phone(); 직접 객체 생성 안됨
		
		SmartPhone smartPhone = new SmartPhone("홍길동");
		
		smartPhone.turnOn();
		smartPhone.internetSearch();
		smartPhone.turnOff();
		

	}

}
