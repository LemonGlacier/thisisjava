package ch04.sec.conf;

public class Example6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		int j;
		
		/*for(i=1; i<=5; i++) {
			if(i==1) {
			System.out.println("*");
			} else if(i==2) {
				System.out.println("**");
			} else if(i==3) {
				System.out.println("***");
			} else if(i==4) {
				System.out.println("****");
			} else
				System.out.println("*****");
			
		}*/
		for(i=1; i<=5; i++) {
			for(j=1; j<=i; j++) {
				
				System.out.print("*");
			
			}
			System.out.println(" ");
			
		}

	}

}
