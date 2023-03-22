package teamproject.comProjectPP.DTO;

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
	private int totalGroupNo;
	
	public Page(int rowsPerPage, int pagesPerGroup, int totalRows, int pageNo) {
		this.rowsPerPage = rowsPerPage;
		this.pagePerGroup = pagesPerGroup;
		this.totalRow = totalRows;
		this.currPage = pageNo;

		totalPage = totalRows / rowsPerPage;
		if(totalRows % rowsPerPage != 0) totalPage++;
		
		totalGroupNo = totalPage / pagesPerGroup;
		if(totalPage % pagesPerGroup != 0) totalGroupNo++;
		
		currGroup = (pageNo - 1) / pagesPerGroup + 1;
		
		startPage = (currGroup-1) * pagesPerGroup + 1;
		
		endPage = startPage + pagesPerGroup - 1;
		if(currGroup == totalGroupNo) endPage = totalPage;
		
		startRow = (pageNo - 1) * rowsPerPage + 1;
		
		endRow = pageNo * rowsPerPage;
		
	}

	public Page() {
		// TODO Auto-generated constructor stub
	}
}
