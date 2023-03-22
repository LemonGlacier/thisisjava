package ch18.sec06;

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

public class CharacterConvertStreamExample {

	public static void main(String[] args) {
		try {
			OutputStream os = new FileOutputStream("C:/Temp/test.txt");
			Writer writer= new OutputStreamWriter(os,"UTF-8");
			String str = "문자 변환 스트림을 사용합니다.";
			//byte[] data =str.getBytes("UTF-8");
			//os.write(data);
			//os.flush();
			//os.close();
			writer.write(str);
			
			writer.flush();
			writer.close();
			
			InputStream is =new FileInputStream("C:/Temp/test.txt");
			               //new FileReader("C:/Temp/test.txt");
			/*byte[] data = new byte[100];
			int num = is.read(data);
			String str2 = new String(data, "UTF-8");
			System.out.println(str2);*/
			Reader reader = new InputStreamReader(is, "UTF-8");
			char[] data = new char[20];
			int num = reader.read(data);
			String str2 = new String(data, 0, num);
			System.out.println(str2);
			
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
        char[] data = new char[20];
        int num = reader.read(data);
        reader.close();
        String str = new String(data, 0, num);
        return str;
	}

}
