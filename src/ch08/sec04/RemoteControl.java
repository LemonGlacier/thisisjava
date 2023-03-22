package ch08.sec04;

public interface RemoteControl {
	int MAX_VOLUME=10;
	int MIN_VOLUME=0;                //상수 취급
	
	//추상 메소드 선언
	void turnOn();  
	void turnOff();
	void setVolume(int volume);      //실행부{} 없음

}
