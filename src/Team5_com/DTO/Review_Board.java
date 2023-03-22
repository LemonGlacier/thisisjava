package Team5_com.DTO;


import java.util.Date;

import lombok.Data;
@Data
public class Review_Board {
	 private String review_Bno;
	 private String review_Btitle;
	 private String review_Bcontent;
	 private Date review_Date;
	 private int review_Rate;
	 private String user_Id;
	 private String order_Detail_Id;
}
