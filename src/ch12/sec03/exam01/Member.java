package ch12.sec03.exam01;

public class Member {
	public String id;
	
	public Member(String id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {   //자식 객체 대입 가능
		if(obj instanceof Member target) {       //(obj instanceof Member)
			                                     //Member m = (Member) obj;
			if(id.equals(target.id)) {
				return true;
			}
		} 
			return false;
	}

}
