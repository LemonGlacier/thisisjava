package ch05.sec.conf;

public class Example7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1, 5, 3, 8, 2 };
		int m =0;
		
		for( int i =0; i<array.length ; i++) {
			if(m<array[i]) {
				m = array[i];
			}
		}
		System.out.println(m);

	}

}
