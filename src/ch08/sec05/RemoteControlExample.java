package ch08.sec05;

public class RemoteControlExample {

	public static void main(String[] args) {
		RemoteControl rc = new Television();
		rc.turnOn();
		rc.setVolume(5);
		rc.turnOff(); 
		
		rc.setMute(true);
		rc.setMute(false);
		System.out.println("volume now: " + rc.getVolume());
		
		rc = new Audio();
		rc.turnOn();
		rc.setVolume(7);
		
		rc.setMute(true);
		rc.setMute(false);
		System.out.println("volume now: " + rc.getVolume());
	}

}
