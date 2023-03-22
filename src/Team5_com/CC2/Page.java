package Team5_com.CC2;

import lombok.Data;

@Data
public class Page {
	private int rowsPerPage = 5;
	private int pagePerGroup = 5;
	private int totalRow ;
	private int totalPage;
	private int startRow;
	private int endRow;
	private int startPage;	
	private int endPage;
	private int currPage;
	private int currGroup;
	private int lastGroup;
}
