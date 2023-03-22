package ch15.sec06.exam02;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {

	public static void main(String[] args) {
		// Queue 컬렉션 생성
		Queue <Message> messageQueue = new LinkedList<>();
		
		//메시지 넣기
		messageQueue.offer(new Message("send Mail", "홍길동"));
		messageQueue.offer(new Message("send SMS", "신용권"));
		messageQueue.offer(new Message("send Kakaotalk", "감자바"));
		
		//메시지를 하나씩 꺼내 처리
		while(!messageQueue.isEmpty()) {
			Message message = messageQueue.poll();
			switch(message.command) {
			case "send Mail":
				System.out.println(message.to + "님에게 메일을 보냅니다.");
				break;
			case "send SMS":
				System.out.println(message.to + "님에게 SMS를 보냅니다.");
				break;
			case "send Kakaotalk":
				System.out.println(message.to + "님에게 카카오톡을 보냅니다.");
				break;
			}
		}
	}

}
