package com.lintech.core.mybatis;

import org.apache.ibatis.session.RowBounds;

/**
 * 物理分页
 */
public class Page extends RowBounds {
    private int total;
    private int offset;
    private int limit;
    
    public Page() {
        this.offset = NO_ROW_OFFSET;
        this.limit = NO_ROW_LIMIT;
    }

    public Page(int pageNum, int limit) {
    	this.offset = (pageNum-1)*limit;
    	this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setMeToDefault() {
        this.offset = NO_ROW_OFFSET;
        this.limit = NO_ROW_LIMIT;
    }
    
    public int getSelectCount() {
        return limit + offset;
    }

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
    
    
    
}