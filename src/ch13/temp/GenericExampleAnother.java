package ch13.temp;

public class GenericExampleAnother {
	
	public static <T,F> Box<T> boxing(T t) {   //제네릭 메소드
		Box<T> box = new Box<T>();
		box.set(t);
		return box;
	}

	public static void main(String[] args) {
		Box<Integer> box1 = boxing(100);    //제네릭 메소드 호출
		int intValue = box1.get();
		System.out.println(intValue);

	}

}
