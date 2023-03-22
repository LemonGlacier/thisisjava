package ch17.sec02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ParallelStreamExample {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();      //Vector: 스레드 동시 진행
		/*list.add("홍길동");
		list.add("동길동");
		list.add("남길동");
		list.add("서길동");
		list.add("북길동");*/
		
		for(int i =0; i<1000; i++) {
			list.add("홍" + i);
		}
		
		Stream<String> stream = list.parallelStream();
		stream.forEach((t)->
		System.out.println(t+": " + Thread.currentThread().getName()));

	}

}
