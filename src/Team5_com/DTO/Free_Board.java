package Team5_com.DTO;


import java.util.Date;

import lombok.Data;
@Data
public class Free_Board {
	 private String free_Bno;
	 private String free_Btitle;
	 private String free_Bcontent;
	 
	 private Date free_Date;
	 private String user_Id;
	 private int free_Comment_Num;
}
