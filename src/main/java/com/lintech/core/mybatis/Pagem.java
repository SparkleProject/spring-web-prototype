package com.lintech.core.mybatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

/**
 * 内存分页
 * @param <T>
 */
public class Pagem<T> extends RowBounds {
    private List<T> list;
    private int total;
    private int offset;
    private int limit;
    
    public Pagem() {
        this.offset = NO_ROW_OFFSET;
        this.limit = NO_ROW_LIMIT;
    }
    
    public  Pagem(List<T> list,int pageNum, int limit) {
    	this.list=list;
    	this.offset = (pageNum-1)*limit;
    	this.limit = limit;
    	this.total=list.size();
    }

    public int getTotal() {
        return total;
    }
    
    public int getTotalPage() {
    	int mod=total%limit;
    	if(mod>0) {
    		return total/limit+1;
    	}else {
    		return total/limit;
    	}
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
    	if(this.offset+this.limit>this.total) {
    		return this.total-this.offset;
    	}
        return limit;
    }

    public void setMeToDefault() {
        this.offset = NO_ROW_OFFSET;
        this.limit = NO_ROW_LIMIT;
    }
    
    public int getSelectCount() {
        return limit + offset;
    } 
    
    public List<T> pagination() {
    	return list.subList(this.getOffset(), this.getOffset()+this.getLimit());
    }
}