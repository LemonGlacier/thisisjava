package ch08.sec02;

public class RemoteControlExample {

	public static void main(String[] args) {
		RemoteControl rc=new Television();   //인터페이스타입 변수 rc를 통해 Television(구현)객체를 사용하겠다
		rc.turnOn();
		rc=new Audio();   
		rc.turnOn();

	}

}
