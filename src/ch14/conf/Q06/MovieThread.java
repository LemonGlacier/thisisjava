package ch14.conf.Q06;

public class MovieThread extends Thread{
	@Override
	public void run() {
		while(true) {
			System.out.println("동영상을 재생합니다.");
			if(Thread.interrupted() ) {    //if(isInterrupted())
				break;
			}
		}
	}

}
