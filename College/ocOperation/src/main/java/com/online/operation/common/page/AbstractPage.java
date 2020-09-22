package com.online.operation.common.page;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public abstract class AbstractPage<E> implements Page<E> {

    public static final int DEFAULT_FIRST_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;

    protected int pageSize = DEFAULT_PAGE_SIZE;
    protected int pageNum = DEFAULT_FIRST_PAGE_NUM;
    
    protected int itemsTotalCount = 0;//总记录数
    protected int pageTotalCount = 0;//总页数
    protected List<E> items;
    protected boolean firstPage;//是否是第一页
    protected boolean lastPage;//是否是最后一页
    protected int startIndex;
    
    private String sortField="update_time";//排序
	private String sortDirection = "DESC";//排序方向
    

    public int getFirstPageNum() {
        return DEFAULT_FIRST_PAGE_NUM;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if (pageNum < DEFAULT_FIRST_PAGE_NUM) pageNum = DEFAULT_FIRST_PAGE_NUM;
        this.pageNum = pageNum;
    }

    public List<E> getItems() {
        return items;
    }

    public void setItems(Collection<E> items) {
        if (items == null) items = Collections.emptyList();
        this.items = new ArrayList<E>(items);
        this.lastPage = this.pageNum == this.pageTotalCount;
        this.firstPage = this.pageNum == DEFAULT_FIRST_PAGE_NUM;
    }

    public boolean isFirstPage() {
    	firstPage = (getPageNum() <= getFirstPageNum());
    	return firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public int getPrePageNum() {
        return isFirstPage() ? getFirstPageNum() : getPageNum() - 1;
    }

    public int getNextPageNum() {
        return isLastPage() ? getPageNum() : getPageNum() + 1;
    }

    public Iterator<E> iterator() {
        return this.items.iterator();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

	public void setItemsTotalCount(int itemsTotalCount) {
		this.itemsTotalCount = itemsTotalCount;
		if(itemsTotalCount % this.pageSize == 0){
			this.pageTotalCount = itemsTotalCount/this.pageSize;
		}else{
			this.pageTotalCount = itemsTotalCount/this.pageSize + 1;
		}
		if(this.pageNum > this.pageTotalCount){
			this.pageNum = DEFAULT_FIRST_PAGE_NUM;
		}
		if(this.itemsTotalCount <= this.pageSize){
			this.firstPage = true;
			this.lastPage = true;
		}
	}

	public int getItemsTotalCount() {
		return itemsTotalCount;
	}

	public int getLastPageNum() {
		return itemsTotalCount;
	}
	
	public int getStartIndex() {
		this.startIndex = (this.pageNum - 1) * this.pageSize;
		if(this.startIndex <= 0){
			this.startIndex = 0;
		}
		return this.startIndex;
	}
	
	/**
	 * 按照sortField升序
	 * @param sortField：指java bean中的属性
	 */
	public void ascSortField(String sortField) {
		if(!StringUtils.isEmpty(sortField)){
			this.sortField = sortField;
			this.sortDirection = " ASC ";
		}
	}
	
	/**
	 * 按照sortField降序
	 * @param sortField ：指java bean中的属性
	 */
	public void descSortField(String sortField) {
		if(!StringUtils.isEmpty(sortField)){
			this.sortField = sortField;
			this.sortDirection = " DESC ";
		}
	}
	
	public String getSortDirection() {
		return sortDirection;
	}
	
	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
	
	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	@Override
    public String toString() {
        return "Page[" + this.getPageNum() + "]:" + items.toString();
    }

	
}
