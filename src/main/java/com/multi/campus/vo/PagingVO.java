package com.multi.campus.vo;

import lombok.ToString;

//페이지정보,	 검색어
@ToString
public class PagingVO {
	private int nowPage = 1;//현재페이지
	private int onePageRecord = 5; //한번에 표시할 레코드 수
	
	private int totalRecord; //총레코드 수
	private int totalPage; //총페이지 수

	//페이지 넘버링
	private int startPage = 1; //시작페이지
	private int onePageCount = 5;
	
	//검색어
	private String searchKey;
	private String searchWord;
	
	private int offsetPoint = (nowPage-1) * onePageRecord; //0 레코드 선택시 시작위치

	
	//getter, setter
	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
		
		//해당페이지 위치를 계산
		offsetPoint = (nowPage-1) * onePageRecord;
		
		//시작페이지 : 현재페이지 번호가 바뀔때
		startPage = (nowPage-1) / onePageCount * onePageCount+1;
	}

	public int getOnePageRecord() {
		return onePageRecord;
	}

	public void setOnePageRecord(int onePageRecord) {
		this.onePageRecord = onePageRecord;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		
		//총페이지수
		//	7	/	5	=	2
		totalPage = (int)Math.ceil(totalRecord /(double)onePageRecord);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getOffsetPoint() {
		return offsetPoint;
	}

	public void setOffsetPoint(int offsetPoint) {
		this.offsetPoint = offsetPoint;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getOnePageCount() {
		return onePageCount;
	}

	public void setOnePageCount(int onePageCount) {
		this.onePageCount = onePageCount;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

}
