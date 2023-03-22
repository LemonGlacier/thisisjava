package ch06.confAgain;

public class Account {
	public static final int MIN_BALANCE = 0;
	public static final int MAX_BALANCE= 100;
	private int num;
	private String owner;
	private int firstBal;
	
	private int balance;
	
	/*public int getBalance() {
		return balance;
	}
	public void setBalance(int bal) {
		if(bal<MIN_BALANCE || bal>MAX_BALANCE) {            //return; 하고 else 안 쓸 수 있음
		} else {
			balance=bal;
		}
	}*/
	
	public Account(int num, String owner, int firstBal) {
		this.num = num;
		this.owner =owner;
		this.firstBal = firstBal;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getOwner() { return owner;}
	public void setOwner(String owner) { this.owner = owner;}
	public int getBal() {return firstBal;}
	public void setBal(int firstBal) {this.firstBal=firstBal;}
	

}
