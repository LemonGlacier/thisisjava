package ch11.sec06;

public class Account {
	//잔고 필드 선언
	private long balance;
	
	public Account() {}   //컴파일 시 자동 생성
	
	public long getBalance() {
		return balance;
	}
	
	public void deposit(int money) {
		balance += money;
	}
	
	public void withdraw(int money) throws InsufficientException {   //예외 떠넘기기
		if(balance < money) {
			throw new InsufficientException("잔고 부족: " + (money-balance) + " 부족");
		}
		balance -= money;
	}
}
