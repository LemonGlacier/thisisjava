package ch08.sec06;

public class Television implements RemoteControl{
	private int volume;

	@Override
	public void turnOn() {
		System.out.println("turn on the tv");
		
	}

	@Override
	public void turnOff() {
		System.out.println("turn off the tv");
		
	}

	@Override
	public void setVolume(int volume) {
		if(volume > RemoteControl.MAX_VOLUME) {
			this.volume = RemoteControl.MAX_VOLUME;
		} else if(volume < RemoteControl.MIN_VOLUME) {
			this.volume = RemoteControl.MIN_VOLUME;
		} else {
			this.volume = volume;
		}
		System.out.println("T volume now: " + this.volume);
		
	}
	@Override
	public int getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}

}
