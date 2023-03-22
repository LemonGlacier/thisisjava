package ch09.sec04.exam03;

public class A {
	public void method1(final int arg) {
		final int var =1;
		
		class B {
			void method2() {
				System.out.println("arg: " + arg);
				System.out.println("var: " + var);
			}
		}
		//arg = 2;
		//var = 2;
		System.out.println("arg: " + arg);
		System.out.println("var: " + var);
	}
	public static void main(String[] args) {
		A a = new A();
		a.method1(3);
	}

}
