package com.lintech.core.easyui;

import java.util.List;

 /**
  * UI DataGrid辅助类
  * @param <T>
  */
public class DataGrid<T> {
    /**
     * 总数据
     */
	public int total;
	/**
	 *行数据
	 */
	public List<T> rows;
	
	public DataGrid(List<T> voList,int total) {
		super();
		this.total = total;
		this.rows = voList;
	}
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
