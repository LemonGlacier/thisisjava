package ch08.sec06;

public interface RemoteControl {
	int MAX_VOLUME=10;
	int MIN_VOLUME=0;                //상수 취급
	
	//추상 메소드 선언
	void turnOn();  
	void turnOff();
	void setVolume(int volume);      //실행부{} 없음
	int getVolume();
	
	default void setMute(boolean mute) {
		if(mute) {
			System.out.println("mute");
			setVolume(MIN_VOLUME);
		} else {
			System.out.println("non mute");
		}
	}
	static void changeBattery() {   //public
	System.out.println("change battery");
	}
}
