package com.lintech.core.util;

/**
 * percent indicator util
 */
public class PercentIndicatorUtil {
	final static String IMAGE_ENDSWITH="_percent_indicator.gif";

	/**
	 * Get the name of percent indicator image
	 * @param percent ends with %,such as "20%"
	 * @return
	 */
	public static String getPercentIndicatorImageName(String percent){
		if(percent.endsWith("%")){
			percent= percent.replace("%","");
			double pd = Double.parseDouble(percent);
			int pi=(int)(pd);
			if(pd>=0&&pd<100){
				int m=pi%10;
				if(m==0){
					return pi+IMAGE_ENDSWITH;
				}else if(m<=5){
					pi=pi-(pi%10)+5;
				}else{
					pi=pi-(pi%10)+10;
				}
				return pi+IMAGE_ENDSWITH;
			}else if(pd==100){
				return "100"+IMAGE_ENDSWITH;
			}else{
				throw new IllegalArgumentException("The argument 'percent' out of range[0%,100%]");
			}
		}else{
			throw new IllegalArgumentException("The argument 'percent' must be ends with '%'");
		}
	}
	/**
	 * Get the name of percent indicator image
	 * @param percent
	 * @return
	 */
	public static String getPercentIndicatorImageName(double pd){
			int pi=(int)(pd);
			if(pd>=0&&pd<100){
				int m=pi%10;
				if(m==0){
					return pi+IMAGE_ENDSWITH;
				}else if(m<=5){
					pi=pi-(pi%10)+5;
				}else{
					pi=pi-(pi%10)+10;
				}
				return pi+IMAGE_ENDSWITH;
			}else if(pd==100){
				return "100"+IMAGE_ENDSWITH;
			}else{
				throw new IllegalArgumentException("The argument 'percent' out of range[0,100]");
			}
	}
}
