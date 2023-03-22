package ch09.sec02.exam01;

public class A {
	//클래스 구성 멤버: 필드, 생성자, 메소드 +중첩 클래스Nested Class
	//필드
	int field;
	B field2;
	//생성자
	A() {
		B b = new B();
		b.methodB();
	}
	//메소드
	void methodA() {
		B b = new B();
		b.methodB();
	}
	public class B{
		public void methodB() {
			System.out.println("methodB() 실행");
		}
	}

}
