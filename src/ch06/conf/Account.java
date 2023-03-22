package ch06.conf;

public class Account {
	
	public static final int MIN_BALANCE = 0;
	public static final int MAX_BALANCE = 1000000;
	
	private int balance;
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		if(balance<Account.MIN_BALANCE || balance>Account.MAX_BALANCE) {
			return;
		}
		this.balance = balance;
	}
	//20번문제
	private String accountN;
	private String owner;
	private int firstD;
	
	public Account(String accountN, String owner, int firstD) {
		this.accountN = accountN;
		this.owner = owner;
		this.firstD = firstD;
	}
	public String getAccountN() {return accountN;}
	public void setAccountN(String accountN) {this.accountN = accountN;}
	public String getOwner() {return owner;}
	public void setOwner(String owner) {this.owner = owner;}
	public int getFirstD() {return firstD;}
	public void setFirstD(int firstD) {this.firstD = firstD;}
	

}
