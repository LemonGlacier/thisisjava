package ch14.sec06.exam01;

public class Calculator {     //공유 객체
	private int memory;

	public int getMemory() {
		return memory;
	}
	
	public synchronized void setMemory1(int memory) {      //동기화 메소드
		this.memory = memory;
		try {
			Thread.sleep(2000);
		} catch(Exception e) {}
			System.out.println(Thread.currentThread().getName() + ": " + this.memory);
		
	}

	public void setMemory2(int memory) {
		synchronized(this) {             //동기화 블록
			this.memory = memory;
			try {
				Thread.sleep(2000);
			} catch(Exception e) {}
				System.out.println(Thread.currentThread().getName() + ": " + this.memory);
		}
	}
	
}
