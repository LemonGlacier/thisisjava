package teamproject.comProjectPP.client;

import teamproject.comProjectPP.DTO.Page;

interface Paging {
	default void printPaging(Page page) {
		
		System.out.print("[처음]");
		if (page.getCurrGroup() != 1) {
			System.out.print("[이전]");
		}
		for (int i = page.getStartPage(); i <= page.getEndPage(); i++) {
			if(page.getCurrPage()==i) {
				System.out.print("("+i+")");
			}else {
				System.out.print(i);
			}
			
			if (i != page.getEndPage()) {
				System.out.print(",");
			}
		}

		if (page.getCurrGroup() != page.getLastGroup()) {
			System.out.print("[다음]");
		}
		System.out.println("[맨끝]");

	}
//호출하기전 전체 행수,현재 페이지 저장이 이루어진 상태여야한다.
	default void setPageInfo(Page page) {
		//page.setRowsPerPage(rpp); //페이지당 행수
		//page.setPagePerGroup(ppg); //그룹당 페이지수
		//현재 그룹
		page.setCurrGroup((int) Math.ceil((double) page.getCurrPage() / page.getPagePerGroup()));
		//전체 페이지수
		page.setTotalPage((int) Math.ceil((double) page.getTotalRow() / page.getRowsPerPage()));
		//현재그룹에서 시작페이지
		page.setStartPage((page.getCurrGroup() - 1) * (page.getPagePerGroup())+1);
		//마지막그룹
		page.setLastGroup((int) Math.ceil((double) page.getTotalPage() / page.getPagePerGroup()));
		//현재 그룹에서 마지막 페이지와 현재페이지에서 마지막 행
		if (page.getCurrGroup() != page.getLastGroup()) {
			page.setEndPage(page.getCurrGroup() * page.getPagePerGroup());
			page.setEndRow(page.getCurrPage() * page.getRowsPerPage());
		} else {
			page.setEndPage(page.getTotalPage());
			if (page.getCurrPage() == page.getEndPage()) {
				page.setEndRow(page.getTotalRow());
			}else {
				page.setEndRow(page.getCurrPage()*page.getRowsPerPage());
			}
		}
		//현재 페이지에서 시작행
		page.setStartRow((page.getCurrPage() - 1)*page.getRowsPerPage()+1);

	}
}

