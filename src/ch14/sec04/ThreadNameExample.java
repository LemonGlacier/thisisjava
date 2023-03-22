package ch14.sec04;

public class ThreadNameExample {

	public static void main(String[] args) {
		Thread mainThread = Thread.currentThread();
		System.out.println(mainThread.getName() + "실행");
		
		for(int i =0; i<3; i++) {
			Thread threadA = new Thread() {  //익명 자식 객체 생성
				@Override
				public void run() {
					System.out.println(getName() + "실행");  //객체 내라 참조변수 생략(getName 앞에)
				}
			};
			threadA.start();
		}
		
		Thread chatThread = new Thread() {
			@Override
			public void run() {
				System.out.println(getName() + "실행");
			}
		};
	     chatThread.setName("chat-thread");
	     chatThread.start();

	}

}
