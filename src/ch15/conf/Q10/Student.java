package ch15.conf.Q10;

public class Student implements Comparable<Student>{
	public String id;
	public int score;
	
	public Student(String id, int score) {
		this.id=id;
		this.score =score;
	}

	@Override
	public int compareTo(Student o) {      //o instanceof Student target 쓰고 변환 안 될 때는 뭘 리턴하지 고민했는데 Comparable 제네릭 타입을 Student로 하면 해결
		if(score<o.score) {return -1;}
		else if(score==o.score) return 0;
		return 1;
	}


}
