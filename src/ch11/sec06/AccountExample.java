package ch11.sec06;

public class AccountExample {

	public static void main(String[] args) {
		Account account = new Account();
		
		account.deposit(10000);
		System.out.println("예금액: " + account.getBalance());
		
		try {
		account.withdraw(13000);	
		} catch(InsufficientException e) {
			String message = e.getMessage();
			System.out.println(message);
		}
		System.out.println("잔고: " + account.getBalance());
	}

}
