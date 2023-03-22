package ch14.sec08;

public class AutoSaveThread extends Thread{
	public void save() {
		System.out.println("작업 내용을 저장");
	}
	@Override
	public void run() {
		while(true) {
			try{
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				break;
			}
			save();
		}
		System.out.println("자동 저장 스레드 안전하게 종료");
	}

}
