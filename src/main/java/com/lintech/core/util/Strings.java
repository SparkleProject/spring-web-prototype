package com.lintech.core.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 其他功能请使用：@{link org.apache.commons.lang3.StringUtils}
 */
public class Strings {
    
    /**
     * 获取定长字符串,默认后缀“...”
     * @param origin
     * @param len
     * @return
     */
    public static String getSubString(String origin,int len){
        return getSubString(origin, len, "...");
    }
    
    
    /**
     * 获取定长字符串
     * @param origin
     * @param len
     * @return
     */
	public static String getSubString(String origin,int len,String subffix){
	    if(origin.length()<=len)
            return origin;
        else
            return origin.substring(0,len-2)+subffix;
	}
	
	
	
	/**
	 * 清楚字符串中的空格、回车、换行符、制表符
	 * @param str
	 * @return
	 */
	public static String cleanBlank(String str) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		return m.replaceAll("");
	}
	

	
	/**
	 * 过滤String空值(NULL)
	 * @param str
	 * @return
	 */
	public static String null2blank(String str){
		if(null==str){
			return "";
		}else{
			return str.trim();
		}
	}
	
	/**
	 * 转义Html标记
	 * @param html
	 * @return
	 */
	public static String encodeHtmlTag(String html) {
		html=html.replace("<", "&lt;")
    		.replace(">", "&gt;")
    		.replace(" ", "&nbsp;")
    		.replace("\"", "&quot;")
    		.replace("\'", "&apos;")
    		.replace("\n", "<br/>");
		return html;
	}
	
	/**
	 * 清除html标签
	 * @param htmlStr
	 * @return
	 */
	 public static String cleanHtmlTag(String htmlStr){ 
	     if (htmlStr == null || "".equals(htmlStr))
	            return "";
		String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; 
		String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>";  
		String regEx_html="<[^>]+>"; 
		Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
		Matcher m_script=p_script.matcher(htmlStr); 
		htmlStr=m_script.replaceAll(""); 
		Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
		Matcher m_style=p_style.matcher(htmlStr); 
		htmlStr=m_style.replaceAll(""); 
		Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
		Matcher m_html=p_html.matcher(htmlStr); 
		htmlStr=m_html.replaceAll(""); 
		return htmlStr.trim();
	} 
	 
	 /**
	  * 生成随机指定位数的数字字符串
	  * @param len
	  * @return
	  */
	 public static String randomString(int len){
		 if(len>16){
			 throw new IllegalArgumentException("len must be less than 17");
		 }
		 Random r = new Random(); 
		Double d = r.nextDouble(); 
		String s = d + ""; 
		s=s.substring(2,2+len); 
		return s;
	 }
	 
	
	 /**
	  * 是否包含中文(待修正)
	  * XXX
	  * @param str
	  * @return
	  */
	 public static boolean isContainCN(String str){  
		 String tmp=str;
        if(tmp.getBytes().length == tmp.length()){  
        	return false;
        }else{  
        	return true;
        }  
	}  
	 
	
	/**
	 * 获取指定 编码的字符串
	 * @param src
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getString(String src,String charset) throws UnsupportedEncodingException{
         return new String(src.getBytes("ISO-8859-1"),charset);
	}
	
	
	/**
	 * 字符串转List
	 * eg. a,b,c => List<String> a b c
	 * @param src
	 * @param separator
	 * @return
	 */
	public static List<String> strToList(String src,String separator){
        if(src.startsWith(separator))
            src=src.substring(1);
        if(src.endsWith(separator))
            src=src.substring(0, src.length()-1);
        String[] arr=src.split(separator);
        List<String> result=new ArrayList<String>();
        for(String s:arr){
            result.add(s);
        }
        return result;
	}
	
	
	/**
	 * 数组转化为连接字符串，前后无连接符
	 * @see cn.jonathan.commons.lang.StringUtils.join(String[], String, boolean)
	 * For example:
	 * {a,b,c} join "/" => "a/b/c"
	 * @param arr
	 * @param separator 连接符
	 * @return
	 */
	public static String join(String[] arr,String separator){
	   return join(arr, separator, false);
	}
	
	/**
	 * 数组转化为连接字符串
	 * For example:
	 * {a,b,c} join "/" => "a/b/c" or "/a/b/c/"
	 * @param arr
	 * @param separator 连接符
	 * @return
	 */
	public static String join(String[] arr,String separator,boolean separatorWrap){
	    StringBuffer buf=new StringBuffer();
	    String result=null;
	    if(arr==null)
	        return null;
	    for(String s:arr){
	        if(isNotEmpty(s)){
	            buf.append(s).append(separator);
	        }
	    }
	    result=buf.toString();
	    if(isNotEmpty(result)){
	        result=result.substring(0,result.length()-1);
	    }else{
	        result="";
	    }
	    if(separatorWrap&&isNotEmpty(result)){
	        result=separator+result+separator;
	    }
	    return result;
	}
	
   /**
     * 获取一个随机数组，此数组的元素在指定范围内且没有重复值
     * <br>注意：3个参数应该都大于等于0，且end > begin，且count <= end-begin
     * 
     * @param begin 最小值（包含）
     * @param end 最大值（不包含）
     * @param count 数组的元素个数
     * @return 随机数组
     */
    public int[] getRandomArray(int begin, int end, int count) {
        int length = end - begin;
        if(count > length){
            throw new IllegalArgumentException("'count' should not greater than (end - begin)");
        }
        int[] ori = new int[length];
        for (int i = 0; i < length; i++) {
            ori[i] = i + begin;
        }
        
        int[] array = new int[count];
        int index, temp;
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            index = random.nextInt(length);
            array[i] = ori[index];
            temp = ori[index];
            ori[index] = ori[length-1];
            ori[length-1] = temp;
            length--;
        }

        return array;
    }
    
    /**
     * 获取UUID(无连连接符的32位UUID)
     * @return
     */
    public static String getUUID() {
        String uid = UUID.randomUUID().toString();
        uid = uid.replace("-", "");
        return uid.toUpperCase();
    }
    
    private static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    private static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }
    

    /**
     * 定长字符串,不够用字符填充
     * @param str 原字符串
     * @param c 填充符
     * @param len 字符串长度
     * @return
     */
    public static String padding(String str,char c,int len){
     StringBuffer sbf = new StringBuffer("");
     if(str.length()>len){
    	 throw new IllegalArgumentException("'str' length must less than 'len' value");
     }else{
    	 int append_len=len-str.length();
    	 for(int i=0;i<append_len;i++){
    		 sbf.append(c);
    	 }
    	 return sbf.append(str).toString();
     }
    }
    
    public static String fromat(String format,Object... args){
		String[] sa = format.split("\\{\\}");
		StringBuffer sb=new StringBuffer();
		int len=sa.length;
		if(sa.length==args.length){
			for(int i=0;i<len;i++){
				sb.append(sa[i]).append(args[i]);
			}
		}else if(sa.length-args.length==1){
			for(int i=0;i<len-1;i++){
				sb.append(sa[i]).append(args[i]);
			}
			sb.append(sa[sa.length-1]);
		}else{
			throw new IllegalArgumentException("args length mimatch.");
		}
		String result = sb.toString();
		return result;
	}
    
    public static void main(String[] args) {
    	System.out.println(fromat("a:{},b:{},c:{}",1,2,3));
    	System.out.println(fromat("a:{}",1));
    	System.out.println(fromat("error:{} b c",1));
//    	String api="http://xueqiu.com/stock/quote_order.json?page=1&size=3000&order=desc&exchange=CN&stockType={}&order=asc&orderby=symbol&column=symbol%2Cname%2Chasexist&orderBy=percent&_=1448808184670";
//    	System.out.println(fromat(api,"zxb"));
	}
}
