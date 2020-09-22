package com.online.operation.common.page;

import java.util.List;

/**
 * 分页和排序的
 */
public class TailPage<E> {
    public static int DEFAULT_FIRST_PAGE=1;
    public static int DEFAULT_PAGE_SIZE=10;

    private String sortField="update_time";   //排序的字段
    private String sortDirection="DESC";      //升序or降序
    private int pageNum;      //         查询的页数码
    private List<E> courseList;  //      查询的课程
    private int itemSum;      //         总的记录数
    private int pageSum;      //         总的页数
    private int pageSize=DEFAULT_PAGE_SIZE;        //      分页的大小
    private int pageItemSize;    //      每次查询的数据大小，在最后一次不满10的时候
    private int startIndex;      //      查询数据的起始

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
       if(sortField!=null) this.sortField=sortField;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<E> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<E> courseList) {
        this.courseList = courseList;
    }

    public int getItemSum() {
        return itemSum;
    }

    public void setItemSum(int itemSum) {
        System.out.println("总的记录数"+itemSum);
        //计算出所有的分页
        int pageSum_=itemSum/this.getPageSize();
        if(itemSum%this.getPageSize()!=0){
            pageSum_+=1;
        }
        this.setPageSum(pageSum_);
        System.out.println("总的分页"+pageSum_);
        //计算出这次要查询的数据
        int pageStart_=(this.getPageSum()-1)*this.getPageSize();
        if(pageStart_+10>itemSum){
            this.setPageItemSize(itemSum-pageStart_);
        }else{
            this.setPageItemSize(this.getPageSize());
        }
        System.out.println("分页开始"+pageStart_+"分页数量"+this.getPageItemSize());
        this.setStartIndex(pageStart_);
        this.itemSum = itemSum;
    }

    public int getPageSum() {
        return pageSum;
    }

    public void setPageSum(int pageSum) {
        this.pageSum = pageSum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageItemSize() {
        return pageItemSize;
    }

    public void setPageItemSize(int pageItemSize) {
        this.pageItemSize = pageItemSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
