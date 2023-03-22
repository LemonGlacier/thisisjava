package ch15.conf.Q08;

public class Student {
	public int studentNum;
	public String name;
	
	public Student(int studentNum, String name) {
		this.studentNum = studentNum;
		this.name = name;
	}
	@Override
	public int hashCode() {
		return studentNum;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student target) {
			if(this.studentNum==target.studentNum) {
				return true;
			} 
		}
		return false;
	}
	/* 답지
	 * @Override
	 * public boolean equals(Object obj) {
	 *  if(!(obj instanceof Student)) return false;
	 *  Student student = (Student) obj;
	 *  if(studentNum != student.studentNum) return false;
	 *  return true;
	 *  }
	 */

}
