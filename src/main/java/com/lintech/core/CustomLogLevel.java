package com.lintech.core;

import org.apache.log4j.Level;

public class CustomLogLevel extends Level{
    private static final long serialVersionUID = 1L;
    public static final int SALES_INT=INFO_INT-1;
    public static final int PROGRAME_INT=FATAL_INT+1;
    public static final String SALES_STR="SALES";
    public static final String PROGRAME_STR="PROGRAME";
    
    public static final CustomLogLevel SALES=new CustomLogLevel(SALES_INT,SALES_STR,7);
    
    public static final CustomLogLevel PROGRAME=new CustomLogLevel(SALES_INT,PROGRAME_STR,0);
    
    protected CustomLogLevel(int level, String levelStr, int syslogEquivalent) {
        super(level, levelStr, syslogEquivalent);
    }
    
    /**
        Convert the string passed as argument to a level. If the
        conversion fails, then this method returns {@link #SALES}. 
     */
     public
     static
     Level toLevel(String sArg) {
       return (Level) toLevel(sArg, CustomLogLevel.SALES);
     }
    
     /**
       Convert an integer passed as argument to a level. If the
       conversion fails, then this method returns {@link #SALES}.
     */
     public
     static
     Level toLevel(int val) {
       return (Level) toLevel(val, CustomLogLevel.SALES);
     }
    
     /**
       Convert an integer passed as argument to a level. If the
       conversion fails, then this method returns the specified default.
     */
     public
     static
     Level toLevel(int val, Level defaultLevel) {
       switch(val) {
       case SALES_INT: return CustomLogLevel.SALES;
       case PROGRAME_INT: return CustomLogLevel.PROGRAME;
       default: return defaultLevel;
       }
     }
     
     public
     static
     Level toLevel(String sArg, Level defaultLevel) {                  
       if(sArg == null)
          return defaultLevel;
       String s = sArg.toUpperCase();
       if(s.equals("SALES")) return CustomLogLevel.SALES; 
       if(s.equals("PROGRAME")) return CustomLogLevel.PROGRAME; 
       return defaultLevel;
     }
    

}
