package ch20.oracle.sec12;

import java.util.Date;
import lombok.Data;

@Data
public class Board {     //DTO 클래스
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
    //private String bfilename;
	//private Blob bfiledata;
}
