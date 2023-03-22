package ch05.sec.conf;

public class Example8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] array = {
				{95, 86},
				{83, 92, 96},
				{78, 83, 93, 87, 88}
		};
		int tA = 0;
		int tS = 0;
		for( int i=0; i<array.length; i++) {
			tA += array[i].length;
			for(int k=0; k<array[i].length; k++) {
					tS += array[i][k];
				}
				
			}
		double avg = (double) tS / tA;
		System.out.println("전체 합: " + tS);
		System.out.println("평균: " + avg);
		}
		

	}

