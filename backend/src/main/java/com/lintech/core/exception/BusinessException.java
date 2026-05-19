package com.lintech.core.exception;

public class BusinessException  extends RuntimeException{
	
    protected String excode;
    
    private static final long serialVersionUID = 5426228144648116777L;
    
    public BusinessException(){
        super();
    }
    
    
    public BusinessException(String message,Throwable t){
        super(message,t);
    }
    
    /**
     * @param excode 异常代号
     */
    public BusinessException(String excode) {
        super();
        this.excode = excode;
    }
    
    public String getExcode() {
        return excode;
    }
    
    public void setExcode(String excode) {
        this.excode = excode;
    }
}
