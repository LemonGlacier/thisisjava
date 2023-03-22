package ch09.sec06.exam03;

public class ButtonExample {

	public static void main(String[] args) {
		//Button 객체 생성
		Button buttonOk = new Button();
		Button buttonCancel = new Button();
		
		//Button Click Event 처리 클래스(Listener 객체) 정의
		class OkListener implements Button.ClickListener {

			@Override
			public void onClick() {
				System.out.println("Ok 버튼 클릭");
				
			}
			
		}
		class CancelListener implements Button.ClickListener {

		@Override
		public void onClick() {
			System.out.println("Cancel 버튼 클릭");
		}
			
		}
		//Button 객체에 Click Event 처리 객체 설정
		buttonOk.setClickListener(new OkListener());
		buttonCancel.setClickListener(new CancelListener());
		
		//Button이 클릭되었을때
		buttonOk.click();
		buttonCancel.click();

	}

}
