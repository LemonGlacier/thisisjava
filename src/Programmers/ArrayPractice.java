package Programmers;

import java.util.Arrays;
import java.util.Collections;

public class ArrayPractice {

	    public static  String solution(String[] participant, String[] completion) {
	        String answer = "";
	        Arrays.sort(completion);  //오름차순
	        Arrays.sort(participant, Collections.reverseOrder());   //내림차순
	        Arrays.sort(participant, 0, 2); //인덱스0~인덱스2 부분정렬
	        String[] copy = Arrays.copyOf(participant,3);  //3개항목(인덱스2) 복사
	        String[] rangeCopy = Arrays.copyOfRange(participant,1,3);  //1~3번째항목(인덱스0~2) 복사
	        int find = Arrays.binarySearch(copy, 7);  //copy에서 데이터값 7(인덱스6)이 위치한 인덱스를 find에 저장
	        Arrays.toString(participant);    //배열 데이터 문자열 표현
	        System.out.println(Arrays.toString(copy));
	        
	        return answer;
	    }

	public static void main(String[] args) {
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = {"stanko", "ana", "mislav"};
		System.out.print(solution(participant, completion));
	}

}
