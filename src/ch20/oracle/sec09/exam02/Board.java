package ch20.oracle.sec09.exam02;

import java.sql.Blob;
import java.util.Date;

import lombok.Data;

@Data                           //자동 Getter Setter 메소드
public class Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	private String bfilename;
	private Blob bfiledata;
}
