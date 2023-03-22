package ch13.sec05;

public class Applicant <T> {
	public T kind;
	public Applicant(T kind) {      //T kind를 매개변수로 갖는 생성자
		this.kind =kind; 
	}

}
