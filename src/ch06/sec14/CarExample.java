package ch06.sec14;

public class CarExample {

	public static void main(String[] args) {
		Car myCar = new Car();
		myCar.setSpeed(-50);       //값 저장
		System.out.println(myCar.getSpeed());    //읽기
		
		myCar.setSpeed(50);
		System.out.println(myCar.getSpeed());
		
		if(!myCar.isStop()) {   //자동차가 현재 움직이고 있다면
			myCar.setStop(true);  //정지시켜
		}
		System.out.println(myCar.getSpeed());

	}

}
