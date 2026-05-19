package com.lintech.core.exception;

public class ServiceException  extends RuntimeException{
	
    private static final long serialVersionUID = 5426228144648116777L;
    
	private int code;
    
    public ServiceException(){
        super();
    }
    
    public ServiceException(Throwable t){
    	super(t);
    }
    
    public ServiceException(String message){
        super(message);
    }
    
    public ServiceException(String message,int code){
    	super(message);
    	this.code=code;
    }
    
    public ServiceException(String message,Throwable t){
    	super(message,t);
    }
    
    
    public ServiceException(String message,int code,Throwable t){
    	super(message,t);
    	this.code=code;
    }


	public int getCode() {
		return this.code;
	}

}
