package ch04.sec03;

public class SwitchValueExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String grade = "B";
		
		//자바 이전문법
		int score1 = 0;
		switch(grade) {
		
		  case "A":
			 score1 = 100;
			break;
		  case "B":
			  int result =100 - 20;
			  score1= result;
			  break;
		  default:
			  score1= 60;
			  
		}
		System.out.println("score1: " + score1);
		
		//자바12부터 가능
		int score2 = switch(grade) {
		case "A" -> 100;
		case "B" -> {
			int result = 100 -20;
			
			//자바 13부터 가능
			yield result;
			
		}
		default -> 60;
		};
		System.out.println("score2: " + score2);
		

	}

}
