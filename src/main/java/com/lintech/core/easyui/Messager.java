package com.lintech.core.easyui;

/**
 * UI简单信息载体
 */
public class Messager{
    boolean success;
    String msg;
    int code;
    public final static Messager SUCCESS=new Messager(true);
    public final static Messager FAILURE=new Messager(false);
    /**
     * @param success
     */
    public Messager(boolean success) {
        super();
        this.success = success;
    }
    /**
     * @param success
     * @param msg
     */
    public Messager(boolean success, String msg) {
        super();
        this.success = success;
        this.msg = msg;
    }

    public Messager(boolean success, String msg, int code) {
		super();
		this.success = success;
		this.msg = msg;
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
