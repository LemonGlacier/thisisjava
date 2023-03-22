package ch18.conf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Q10Example {

	public static void main(String[] args) throws Exception{
		Scanner scn = new Scanner(System.in);
		System.out.print("원본 파일 경로: ");
		String original =scn.nextLine();
		System.out.print("복사 파일 경로: ");
		String copy =scn.nextLine();
		
		File of = new File(original);
		if(!of.exists()) {
			System.out.println("원본 파일이 존재하지 않습니다.");
			System.exit(0);
		}
		FileInputStream fis = new FileInputStream(original);
		BufferedInputStream bis = new BufferedInputStream(fis);
		FileOutputStream fos = new FileOutputStream(copy);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		File cf = new File(copy);
		File parentcf = cf.getParentFile();
		if(!parentcf.exists()) {
			parentcf.mkdirs();
		}
		bis.transferTo(bos);
		/*byte[] data = new byte[1024];
		 * while(true) {
		 * int num = bis.read(data);
		 * if(num==-1) break;
		 * bos.write(data,0,num);
		 * }
		 */
		
		System.out.println("복사가 성공하였습니다.");
		
		bis.close();
		bos.close();
	}

}
