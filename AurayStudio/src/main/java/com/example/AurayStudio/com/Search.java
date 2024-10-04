package com.example.AurayStudio.com;

public class Search {
	/***** 페이징 영역 *****/
	private int page;				// 현재 페이지
	private int recordSize;			// 페이지당 보여줄 레코드 
	private int pageSize;			// 한번에 보여줄 페이지 수
	private int totDataCnt;			// 전체 데이터 수
	private int totPageCnt;			// 전체 페이지 수
	private boolean existPrevPage;	// 이전 페이지 존재 여부
	private boolean existNextPage;	// 페이지 리스트 존재 여부
	private int startPage;			// 페이지 리스트 시작 번호
	private int endPage;			// 페이지 리스트 끝 번호
	/***** 검색 영역 *****/
	private String keyword;			// 검색 키워드
	private String searchType;		// 검색 방법
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecordSize() {
		return recordSize;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotDataCnt() {
		return totDataCnt;
	}

	public void setTotDataCnt(int totDataCnt) {
		this.totDataCnt = totDataCnt;
	}

	public int getTotPageCnt() {
		return totPageCnt;
	}

	public void setTotPageCnt(int totPageCnt) {
		this.totPageCnt = totPageCnt;
	}

	public boolean isExistPrevPage() {
		return existPrevPage;
	}

	public void setExistPrevPage(boolean existPrevPage) {
		this.existPrevPage = existPrevPage;
	}

	public boolean isExistNextPage() {
		return existNextPage;
	}

	public void setExistNextPage(boolean existNextPage) {
		this.existNextPage = existNextPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public Search(){
		
	}
	
	public Search(int recordSize, int pageSize) {
		this.page = 1;
		this.recordSize = recordSize;
		this.pageSize = pageSize;
	}
	
	public int getOffset() {
		return (page - 1) * recordSize;
	}
	
	public void calcPage(int totDataCnt) {
		this.totDataCnt = totDataCnt;
		// 전체 페이지 수 계산
		totPageCnt = (int) Math.ceil((double) totDataCnt / recordSize);
		// 페이지 리스트의 시작과 끝 페이지 계산
		startPage = ((page - 1) / pageSize) * pageSize + 1;
		endPage = startPage + pageSize - 1;
		// 끝 페이지가 전체 페이지 수를 넘지 않도록 조정
		if(endPage > totPageCnt) endPage = totPageCnt;
		// 이전 및 다음 페이지 존재 여부 계산
		existPrevPage = startPage != 1;
		existNextPage = endPage != totPageCnt;
		
	}
	
}
