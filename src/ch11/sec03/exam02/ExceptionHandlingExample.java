package ch11.sec03.exam02;

public class ExceptionHandlingExample {

	public static void main(String[] args) {
		try {
		Class.forName("java.lang.String");
		System.out.println("주어진 클래스 있음");
		} catch(ClassNotFoundException e) {
			System.out.println("주어진 클래스 없음");
		}
		System.out.println();
		try {
			Class.forName("java.lang.String2");
			System.out.println("주어진 클래스 있음");
			} catch(ClassNotFoundException e) {
				System.out.println("주어진 클래스 없음");
				e.printStackTrace();
			}
	}

}
