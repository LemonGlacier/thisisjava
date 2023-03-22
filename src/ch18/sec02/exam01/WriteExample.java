package ch18.sec02.exam01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteExample {

	public static void main(String[] args) {
		/*OutputStream os=null;              //초기화를 안 했더니 close()가 안됨
		try {
		os = new FileOutputStream("C:/Temp/test1.db");
		
		byte a =10;
		
		os.write(a);
		os.flush();
		System.out.println("저장 성공");
		
		} catch(FileNotFoundException e) {    //IOException
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				os.close();
			} catch (IOException e) {
			}
		}*/
		
		try(OutputStream os = new FileOutputStream("C:/Temp/test1.db")) {  //AutoCloseable
			
			byte a =10;
			byte b =20;
			byte c =30;
			
			os.write(a);
			os.write(b);
			os.write(c);
			os.flush();
			System.out.println("저장 성공");
			
			} catch(Exception e) {    //IOException
				e.printStackTrace();
			} 
	}

}
