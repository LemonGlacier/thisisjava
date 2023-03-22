package ch07.sec04.exam02;

public class SupersonicAirplane extends Airplane {
	//상수 선언
	public static final int NORMAL =1;
	public static final int SUPERSONIC =2;
	
	//상태 필드 선언
	public int flyMode = NORMAL;
	
	@Override
	public void fly() {
		if(flyMode == SUPERSONIC) {
			System.out.println("supersonic flying");
		} else {
			super.fly();
		}
	}

}
