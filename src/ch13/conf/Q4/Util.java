package ch13.conf.Q4;

public class Util {
	public static <P extends Pair<K,V>,K,V> V getValue(Pair<K,V> pair, K key) {
		if(pair.getKey() == key) {
			return pair.getValue();
		}else {
			return null;
		}
	}
	//how1
	/*public static <K, V> V getValue(Pair<K, V> p, K k) {
	 if(p.getKey() = = k) {
	 return p.getValue();
	 } else {
	 return null;
	 }
	}
	*/
	
	//how2
	/*public static <P extends Pair<K, V>, K, V> V getValue(P p, K k) {       //P extends Pair<K, V>랑 K랑 V 타입을 쓰겠다(매개변수,리턴타입 등등) 리턴 타입은 V, 첫 번째 매개값이 P 타입인 건 P가 Pair의 자식 객체라 괜찮
	 if(p.getKey() = = k) {
	 return p.getValue();       //V 타입
	 } else {
	 return null;
	 }
	}*/
	

}
