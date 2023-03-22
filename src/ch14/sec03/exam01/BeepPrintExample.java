package ch14.sec03.exam01;

import java.awt.Toolkit;

public class BeepPrintExample {
	public static void main(String[] args) {
		//작업1
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		for(int i =0; i<5; i++) {
			toolkit.beep();
			try {
				Thread.sleep(1000);   //millis
			} catch (InterruptedException e) {
			} 
		}
	
	//작업2
		for(int i =0; i<5; i++) {
			System.out.println("띵");
			toolkit.beep();
			try {
				Thread.sleep(1000);   //millis
			} catch (InterruptedException e) {
			} 
		}
	}	
}
