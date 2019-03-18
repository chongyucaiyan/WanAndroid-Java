package com.github.cyc.wanandroid.http.model;

import com.github.cyc.wanandroid.app.NoProguard;

import java.util.List;

public class ArticleList implements NoProguard {

    private Integer curPage;
    private Integer offset;
    private Boolean over;
    private Integer pageCount;
    private Integer size;
    private Integer total;
    private List<Article> datas;

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Boolean isOver() {
        return over;
    }

    public void setOver(Boolean over) {
        this.over = over;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Article> getDatas() {
        return datas;
    }

    public void setDatas(List<Article> datas) {
        this.datas = datas;
    }
}
