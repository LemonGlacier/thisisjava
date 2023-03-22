package ch09.sec07.exam03;

import ch09.sec07.exam03.Button;

public class ButtonExample {

	public static void main(String[] args) {
		//Button 객체 생성
		Button buttonOk = new Button();
		Button buttonCancel = new Button();
		
		
		//Button 객체에 Click Event 처리 객체 설정
		buttonOk.setClickListener(new Button.ClickListener() {
			
			@Override
			public void onClick() {
				System.out.println("OK버튼 클릭");
				
			}
		});
		buttonCancel.setClickListener(new Button.ClickListener() {
			
			@Override
			public void onClick() {
				System.out.println("Cancel버튼 클릭");
				
			}
		});
		
		//Button이 클릭되었을때
		buttonOk.click();
		buttonCancel.click();

	}

}
