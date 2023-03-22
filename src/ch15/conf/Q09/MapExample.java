package ch15.conf.Q09;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapExample {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("blue", 96);
		map.put("hong", 86);
		map.put("white", 92);
		
		String name1 = null;
		int maxScore1 = 0;
		int totalScore1 = 0;
		
		//값으로 계산
		Collection<Integer> coll = map.values();
		Iterator<Integer> keyIterator = coll.iterator();
		while(keyIterator.hasNext()) {
			int x=keyIterator.next();             //x 말고 그대로 쓰면 next()때문에 안 되는 듯
			totalScore1+=x;
			if(maxScore1<x) {
				maxScore1=x;
				//name= 값으로 키를 못 얻니
			}
		}
		System.out.println(maxScore1);
		System.out.println(totalScore1);
		System.out.println();
		
		//키로 계산
		String name2 = null;
		int maxScore2 = 0;
		int totalScore2 = 0;
		
		Set<String> ks=map.keySet();
		Iterator<String> ki = ks.iterator();
		while(ki.hasNext()) {
			String y =ki.next();
			int x=map.get(y);
			totalScore2+=x;            
			if(maxScore2<x) {
				maxScore2=x;
				name2=y;
			}		
		}
		System.out.println(name2);
		System.out.println(maxScore2);
		System.out.println(totalScore2);
		System.out.println();
		
		//엔트리로 계산
		String name3 = null;
		int maxScore3 = 0;
		int totalScore3 = 0;
		Set<Entry<String,Integer>> es=map.entrySet();
		Iterator<Entry<String,Integer>> ei = es.iterator();
		while(ei.hasNext()) {
			Entry<String,Integer> entry= ei.next();
			totalScore3+=entry.getValue();
			if(maxScore3<entry.getValue()) {
				maxScore3=entry.getValue();
				name3=entry.getKey();
			}
		}
		System.out.println(name3);
		System.out.println(maxScore3);
		System.out.println(totalScore3);
		
		//엔트리로 계산: 변수를 줄이고 체이닝 사용
			String name4 = null;
			int maxScore4 = 0;
			int totalScore4 = 0;
			while(map.entrySet()
					.iterator()
					.hasNext()) {
				Entry<String,Integer> entry= map.entrySet()
						.iterator().next();
				totalScore4+=entry.getValue();
				if(maxScore4<entry.getValue()) {
					maxScore4=entry.getValue();
					name4=entry.getKey();
				}
			}
			System.out.println(name4);
			System.out.println(maxScore4);
			System.out.println(totalScore4);

	}

}
