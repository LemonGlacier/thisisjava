package ch17.sec03;

import java.util.Arrays;
import java.util.List;

public class StreamPipeLineExample {

	public static void main(String[] args) {
		//List<Student> list = new ArrayList<>();
		//list.add(new Student("홍길동", 10));
		
		List<Student> list =List.of(
		  new Student("홍길동", 10),
		  new Student("김길동", 20),
		  new Student("서길동", 30)
		); 
		
		double avg = list.stream()
				.mapToInt(student->student.getScore())
				.average()
				.getAsDouble();
		System.out.println("평균 점수: " + avg);
		
		list.stream()
		.map(student->student.getName())              //이름으로 매핑
		.forEach(name->System.out.println(name));     //하나씩 출력
	}

}
