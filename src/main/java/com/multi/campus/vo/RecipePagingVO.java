package com.multi.campus.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipePagingVO {

    private int nowPage = 1;
    private int onePageRecord = 12;

    private int totalRecord;
    private int totalPage;

    private int startPage = 1;
    private int onePageCount = 5;

    private int offsetPoint = (nowPage-1) * onePageRecord;

    private String userid;
    
    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;

        offsetPoint = (nowPage-1) * onePageRecord;

        startPage = (nowPage-1) / onePageCount * onePageCount+1;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;

        totalPage = (int)Math.ceil(totalRecord /(double)onePageRecord);
    }

}
