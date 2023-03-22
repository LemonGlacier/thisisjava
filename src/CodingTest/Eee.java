package CodingTest;

public class Eee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Num = "12341234";
		/*char charV = Num.charAt(3);
		
		System.out.println(charV);*/
		
		String[] arr = Num.split("");
		int count = 0;
		for(int j=0;j<10;j++) {
			for(int i=0;i<arr.length;i++) {
				if (arr[i].equals(String.valueOf(j))) {
					count++;
				}
			/*result = arr[i].contains("j");
			if (result == true) {
				count++;
			}*/
			}
			System.out.println(count);
			count=0;
		}
		
		

	}

}
