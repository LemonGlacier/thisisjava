package ch04.sec.conf;

public class Example3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum = 0;
		int i;
		
		for(i=1; i*3<=100; i++) {
			sum += 3*i;
			
		}
		System.out.println("1~100 3의 배수 합 : " +sum);

		
		//
		int ssm=0;
		for(i=1; i<=100; i++) {
			if (i%3 == 0) {
				ssm += i ;
			}
		}
		System.out.println("1~100 3의 배수 합2 : " +ssm);

	}

}
