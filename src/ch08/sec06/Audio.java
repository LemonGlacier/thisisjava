package ch08.sec06;

public class Audio implements RemoteControl{
	private int volume;
	private int memoryVolume;

	@Override
	public void turnOn() {
		System.out.println("turn on the audio");
		
	}

	@Override
	public void turnOff() {
		System.out.println("turn off the audio");
		
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
		System.out.println("A volume now: " + this.volume);
		
	}
	@Override
	public void setMute(boolean mute) {
		if(mute) {
			this.memoryVolume=this.volume;
			System.out.println("mute");
			setVolume(MIN_VOLUME);
		} else {
			System.out.println("non mute");
			setVolume(this.memoryVolume);
		}
	}
	@Override
	public int getVolume() {
		// TODO Auto-generated method stub
		return this.volume;
	}

}
