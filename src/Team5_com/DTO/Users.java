package Team5_com.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class Users {
	private String user_Id;
	private String user_Pwd;
	private String user_Name;
	private String user_Phone;
	private String user_Email;
	private String user_Nickname;
	private Date user_Insertdate;
	private String user_Address;
	private int user_Purchase;
	
}
