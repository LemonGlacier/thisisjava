package ch09.sec06.exam03;

public class Button {
	//field
	private ClickListener clickListener;
	//constructor
	//method
	public void setClickListener(ClickListener clickListener) {
		this.clickListener = clickListener;
	}
	public void click() {
		clickListener.onClick();
	}
	//nested class
	//nested interface
	public static interface ClickListener {
		public void onClick();  //추상메소드
	}

}

