package ch09.sec03.exam01;

public class A {
	//Nested Class
	static class B {
		void methodB() {
			System.out.println("methodB() 실행");
		}
	}
	
	//Field
	B field1 = new B();
	static B field2 = new B();  //A객체 없이도 B사용 가능(static class B)하기 때문
	
	//Constructor
	A() {
		B field1 = new B();
	}
	
	//Method
	void method1() {
		B field1 = new B(); //A 객체가 있다는 가정하에 실행
	}
	
	static void method2() {
		B field1 = new B();  //
	}

}
