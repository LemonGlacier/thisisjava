package teamproject.comProjectPP.DTO;

public interface Paging {
	default void printPaging(Pager page) {
		
		System.out.print("[처음]");
		if (page.getGroupNo() != 1) {
			System.out.print("[이전]");
		}
		for (int i = page.getStartPageNo(); i <= page.getEndPageNo(); i++) {
			if(page.getPageNo()==i) {
				System.out.print("("+i+")");
			}else {
				System.out.print(i);
			}
			
			if (i != page.getEndPageNo()) {
				System.out.print(",");
			}
		}

		if (page.getGroupNo() != (int) (Math.ceil((double) page.getTotalPageNo() / page.getPagesPerGroup()))) {
			System.out.print("[다음]");
		}
		System.out.println("[맨끝]");

	}
//호출하기전 전체 행수,현재 페이지 저장이 이루어진 상태여야한다.
	
}
