package teamproject.comProjectPP.client;

import java.io.IOException;

import teamproject.comProjectPP.View.Menu_View;


public class Mainprogram {
	
	public static void main(String[] args) {
		try {
			Client client = new Client();
			client.connect();
			
			// chatClient.receive();
			while (true) {
				System.out.println("메인 메뉴 출력");
				Menu_View menu = new Menu_View(client);
				menu.MainMenu();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
