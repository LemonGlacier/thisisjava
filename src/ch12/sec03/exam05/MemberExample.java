package ch12.sec03.exam05;

import java.util.HashSet;

public class MemberExample {

	public static void main(String[] args) {
		Member m1 =new Member("fall", "단풍");
		Member m2 =new Member("fall", "단풍");
		
		System.out.println(m1.hashCode());
		System.out.println(m2.hashCode());
		
		System.out.println(m1.equals(m2));
		
		HashSet hashSet = new HashSet();
		hashSet.add(m1);
		hashSet.add(m2);
		System.out.println(m1.equals(m2));
	}

}
