package com.lintech.core.util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class Http{
	    private Logger logger = LoggerFactory.getLogger(Http.class);
	    private PoolingHttpClientConnectionManager clientConnectionManager=null;
	    private CloseableHttpClient httpClient=null;
	    private CookieStore cookieStore = new BasicCookieStore();
	    private HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
	    private Map<String,String> header=null;
	    private  RequestConfig config = RequestConfig.custom()
//	    		.setProxy(proxy)
	    		.setCookieSpec(CookieSpecs.STANDARD_STRICT)
	    		.setRedirectsEnabled(false)
	    		.build();
	    {
	    	//取消所有SSL认证
	    	TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {
			}

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}}};
		    SSLContext sslContext=null;
			try {
				sslContext = SSLContext.getInstance("SSL");
				sslContext.init(null, trustAllCerts, new SecureRandom());
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (KeyManagementException e) {
				e.printStackTrace();
			}
			
			ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
			SSLConnectionSocketFactory sslsf=new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
	        		.register("http", plainsf)
	                .register("https", sslsf)
	                .build();
	        clientConnectionManager =new PoolingHttpClientConnectionManager(socketFactoryRegistry);
	        clientConnectionManager.setMaxTotal(100);
	        clientConnectionManager.setDefaultMaxPerRoute(80);
	    }
	    
	    private Http() {
	    	
	    } 
	    
	    public static Http newInstance() {
	    	Http http = new Http();
	    	http.initHttpClient();
	    	return http;
	    }

	    private  void initHttpClient(){
	    	httpClient =HttpClients.custom()
            		.setConnectionManager(clientConnectionManager)
            		.setDefaultCookieStore(cookieStore)
            		.setDefaultRequestConfig(config)
            		.setRetryHandler(new HttpRequestRetryHandler() {
            			@Override
            			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            				logger.debug(context.toString());
            				logger.debug("retry request count:{}",executionCount);
            				if (executionCount >= 3) {// 如果已经重试了5次，就放弃                    
            			        return false;
            			    }
            			    if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试                    
            			        return true;
            			    }
            			    if (exception instanceof java.net.SocketException) {//连接超时
            			    	return true;
            			    }
            			    if (exception instanceof ConnectTimeoutException) {//连接超时
            			    	return false;
            			    }
            			    if (exception instanceof SocketTimeoutException) {//连接超时
            			    	return false;
            			    }
            			    if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常                    
            			        return false;
            			    }                
            			    if (exception instanceof InterruptedIOException) {// 超时                    
            			        return false;
            			    }
            			    if (exception instanceof UnknownHostException) {// 目标服务器不可达                    
            			        return false;
            			    }
            			    if (exception instanceof ConnectTimeoutException) {// 连接被拒绝                    
            			        return false;
            			    }
            			    if (exception instanceof SSLException) {// ssl握手异常                    
            			        return false;
            			    }
            			    HttpClientContext clientContext = HttpClientContext.adapt(context);
            			    HttpRequest request = clientContext.getRequest();
            			    // 如果请求是幂等的，就再次尝试
            			    if (!(request instanceof HttpEntityEnclosingRequest)) {                    
            			        return true;
            			    }
            			    return false;
            			}
					})
            		.build();
	    }

	    /**
	     * get请求
	     * @param url
	     * @param headers
	     * @return
	     */
	    public  String get(String url){
	    	String result = get(url, getDefaultHeader(),new BasicResponseHandler());
	        return unicodeDecode(result);
	    }
	    
	    /**
	     * get请求
	     * @param url
	     * @param headers
	     * @param data
	     * @return
	     */
	    public String get(String url,Map<String,String> header){
	      return get(url, header,new BasicResponseHandler());
	    }
	    
	    /**
	     * get请求
	     * @param url
	     * @param headers
	     * @param data
	     * @return
	     */
	    public  <T> T get(String url,Map<String,String> header,ResponseHandler<T> handler){
	        HttpRequest request = new HttpGet(url);
	        wrapRequest(request,header);
	        logger.debug(request.getRequestLine().toString());
	        try {
	            HttpGet httpGet = (HttpGet) request;
	            return httpClient.execute(httpGet,handler);
	        } catch (IOException e){
	        	logger.error("Https get exception: {}",e.getClass().getName());
	        }
	        return null;
	    }
	    

		public  String post(String url,Map<String,Object> data){
	    	return post(url,getDefaultHeader(),data);
	    }
		
  
	    /**
	     * post请求
	     * @param url
	     * @param header
	     * @param data
	     * @return
	     */
	    public  String post(String url,Map<String,String> header,Map<String,Object> data){
	    	String result = post(url, header, data, new BasicResponseHandler());
	       return unicodeDecode(result);
	    }
	    
	    
	    /**
	     *  post请求
	     * @param url
	     * @param header
	     * @param data
	     * @return
	     */
	    public  <T> T post(String url,Map<String,String> header,Map<String,Object> data,ResponseHandler<T> handler){
	        HttpRequest request = new HttpPost(url);
	        wrapRequest(request,header);
	        if(data==null||data.isEmpty()){
	        	logger.debug(request.getRequestLine().toString());
	        }else{
	        	logger.debug(request.getRequestLine().toString()+", params: "+data);
	        }
	        UrlEncodedFormEntity uefEntity;
	        try {
	            HttpPost httpPost = (HttpPost) request;
	            List<BasicNameValuePair> dataForm = getParamsList(data);
	            uefEntity = new UrlEncodedFormEntity(dataForm,"UTF-8");
	            httpPost.setEntity(uefEntity);
	            return httpClient.execute(httpPost,handler);
	        } catch (IOException e){
	            logger.error("Https post exception: {}",e.getClass().getName());
	        }
	        return null;
	    }
	    
	    /**
	     *  post请求
	     * @param url
	     * @param headers
	     * @param data
	     * @return
	     */
	    public String rest(String url,String json){
	        HttpRequest request = new HttpPost(url);
	        if(header==null) {
	        	throw new IllegalArgumentException("header must be specified.");
	        }
	        wrapRequest(request,header);
	        HttpPost httpPost = (HttpPost) request;
	    	try {
	    		httpPost.setEntity(new StringEntity(json));
				CloseableHttpResponse response = httpClient.execute(httpPost);
		    	HttpEntity entity = response.getEntity();
		    	String responseContent = EntityUtils.toString(entity, "UTF-8"); 
		    	logger.debug(responseContent);
		    	return responseContent;
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return null;
	    }
	    
	    /**
	     *  post请求
	     * @param url
	     * @param headers
	     * @param data
	     * @return
	     */
	    public String rest(String url,Map<String,String> header){
	        HttpRequest request = new HttpPost(url);
	        wrapRequest(request,header);
	        HttpPost httpPost = (HttpPost) request;
	    	try {
				CloseableHttpResponse response = httpClient.execute(httpPost);
		    	HttpEntity entity = response.getEntity();
		    	String responseContent = EntityUtils.toString(entity, "UTF-8"); 
		    	logger.debug(responseContent);
		    	return responseContent;
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return null;
	    }
	    
	    /**
	     *  post请求
	     * @param url
	     * @param headers
	     * @param data
	     * @return
	     */
	    public String rest(String url,String json,Map<String,String> header){
	        HttpRequest request = new HttpPost(url);
	        wrapRequest(request,header);
	        HttpPost httpPost = (HttpPost) request;
	    	try {
	    		httpPost.setEntity(new StringEntity(json));
				CloseableHttpResponse response = httpClient.execute(httpPost);
		    	HttpEntity entity = response.getEntity();
		    	String responseContent = EntityUtils.toString(entity, "UTF-8"); 
		    	logger.debug(responseContent);
		    	return responseContent;
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return null;
	    }
	    
		private void wrapRequest(HttpRequest request,Map<String,String> header) {
	    	Set<Entry<String, String>> entrySet = header.entrySet();
	    	for(Entry<String, String> entry:entrySet) {
	    		request.addHeader(entry.getKey(), entry.getValue());
	    	}
		}
	    
	    
	    
	    public void addCookie(String cookieName,String cookieVlaue,String domain) {
	    	addCookie(cookieName, cookieVlaue, domain,"/");
	    }
	    
	    public void addCookie(String cookieName,String cookieVlaue,String domain,String path) {
	    	BasicClientCookie cookie = new BasicClientCookie(cookieName, cookieVlaue); 
	    	cookie.setVersion(0);  
	    	cookie.setDomain(domain);
	    	cookie.setPath(path); 
	    	cookieStore.addCookie(cookie);
	    	List<Cookie> cookies = cookieStore.getCookies();
	    	logger.debug(Arrays.toString(cookies.toArray()));
	    }
	    


		/**
	     * 默认header
	     * @return
	     */
	    public  Map<String,String> getDefaultHeader() {
	        Map<String,String> header=new HashMap<String,String>();
	        header.put("Content-Type", "application/x-www-form-urlencoded");
	        header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
	        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
	        header.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
	        return header;
	    }
	    
	    


	    
	    /**
	     * 获取Cookie
	     * @param url
	     * @return
	     */
	    public  Map<String,String> getCookie(String url){
	        HttpRequest httpGet = new HttpGet(url);
	        CloseableHttpResponse response = null;
	        try{
	            response =httpClient.execute((HttpGet)httpGet);
	            Header[] headers = response.getAllHeaders();
	            Map<String,String> cookies=new HashMap<String, String>();
	            for(Header header:headers){
	                cookies.put(header.getName(),header.getValue());
	            }
	            return cookies;
	        }catch (Exception e){
	            e.printStackTrace();

	        }
	        return null;
	    }
	    
	    /**
		 * 将传入的键/值对参数转换为NameValuePair参数集
		 * @param params
		 * 参数集, 键/值对
		 * @return NameValuePair参数集
		 */
		private  List<BasicNameValuePair> getParamsList(Map<String,Object> params) {
			if (params == null || params.size() == 0) {
				return Lists.newArrayList();
			}
			List<BasicNameValuePair> list = Lists.newArrayList();
			for (Map.Entry<String,Object> map : params.entrySet()) {
				list.add(new BasicNameValuePair(map.getKey(), String.valueOf(map.getValue())));
			}
			return list;
		}
		
		
		/**
		 * 关闭response
		 * @param response
		 */
		public  void close(HttpResponse response){
			HttpEntity entity = response.getEntity();
			try {
				EntityUtils.consume(entity);
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				try {
					if(response instanceof CloseableHttpResponse){
						CloseableHttpResponse c=(CloseableHttpResponse)response;
						c.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		/**
		 * unicode转码
		 * @param str
		 * @return
		 */
		public  String unicodeDecode(String str) {
			if(StringUtils.isBlank(str)){
				return str;
			}
			Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
			Matcher matcher = pattern.matcher(str);
			char ch;
			while (matcher.find()) {
				ch = (char) Integer.parseInt(matcher.group(2), 16);
				str = str.replace(matcher.group(1), ch + "");
			}
			return str;
		}

		public void setHeader(Map<String, String> header) {
			this.header = header;
		}

}
