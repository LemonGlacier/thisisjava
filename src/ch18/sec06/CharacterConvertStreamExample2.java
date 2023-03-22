package ch18.sec06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class CharacterConvertStreamExample2 {

	public static void main(String[] args) {
		try {
			write("문자 변환 스트림을 사용합니다.");
			String str = read();
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void write(String str) throws IOException{
		OutputStream os = new FileOutputStream("C:/Temp/test.txt");
		Writer writer= new OutputStreamWriter(os,"UTF-8");
		writer.write(str);	
		writer.flush();
		writer.close();		
	}
	
	public static String read() throws IOException {
		InputStream is =new FileInputStream("C:/Temp/test.txt");
        Reader reader = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        String str = br.readLine();
        return str;
	}

}
