package ch12.conf.Q15;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class YearEnd {

	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime end = LocalDateTime.of(2022, 12,31,0,0,0);
		
		long reY = now.until(end, ChronoUnit.YEARS);
		long reM = now.until(end, ChronoUnit.MONTHS);
		long reD = now.until(end, ChronoUnit.DAYS);
		
		System.out.println("남은 기간1: " +reY +"년" +reM + "개월" + reD + "일");

	}

}
