package ch12.conf.Q11;

import java.util.StringTokenizer;

public class StringTokenizerExample {

	public static void main(String[] args) {
		String str = "아이디,이름,패스워드";
		StringTokenizer st = new StringTokenizer(str,",");
		while(st.hasMoreTokens()) {
			String print = st.nextToken();
			System.out.println(print);
		}

	}

}
