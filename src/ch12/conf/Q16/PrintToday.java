package ch12.conf.Q16;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintToday {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일 hh시 mm분");
		System.out.println(sdf.format(new Date()));

	}

}
