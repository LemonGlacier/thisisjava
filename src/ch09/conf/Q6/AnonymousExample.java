package ch09.conf.Q6;

public class AnonymousExample {

	public static void main(String[] args) {
		Anonymous anony = new Anonymous();
		anony.field.run();     //필드값+run()메소드
		anony.method1();
		anony.method2(new Vehicle() {      //메소드의 매개값으로 익명 구현 객체 대입

			@Override
			public void run() {
				System.out.println("트럭이 달립니다.");
			}
			
		});

	}

}
