package ch14.sec07.exam03;

public class PrintThread extends Thread{
	@Override
	public void run() {
		
			while(true) {
				System.out.println("실행중...");
				if(Thread.interrupted()) {      //if(isInterrupted())
					break;
				}
			}
	
		System.out.println("리소스 정리");
		System.out.println("실행 종료");
	}

}
