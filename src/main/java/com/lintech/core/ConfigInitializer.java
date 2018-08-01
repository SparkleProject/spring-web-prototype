package com.lintech.core;

import java.io.IOException;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(
urlPatterns="/plugin/config",
name = "ServletContextInitializer",
loadOnStartup = 3,
initParams ={
	@WebInitParam(name = "fileName", value = "config.properties"),
	@WebInitParam(name = "reloadable",value = "true")
}
)
public class ConfigInitializer extends HttpServlet{
	private Logger logger=LoggerFactory.getLogger(getClass());
    private static final long serialVersionUID = -6517837241755564982L;
    public static String fileName;
	public static final String DEFAULT_FILENAME="config.properties";
	public Date lastTime=new Date();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().write("servlet context config refresh last time: "+lastTime);
	}

	@Override
	public void init() throws ServletException {
		fileName = getInitParameter("fileName");
		String reloadable = getInitParameter("reloadable");
		if(fileName==null){
			fileName=DEFAULT_FILENAME;
		}
		loadProp();
		if(reloadable==null){
		    reloadable="false";
		}
		if(Boolean.parseBoolean(reloadable)){
			Timer timer=new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					logger.debug("servlet context config reload.");
					lastTime=new Date();
					loadProp();
				}
			}, 5000, 5000);
		}
	}
	
	private void loadProp(){    
		try {
		    Properties prop=new Properties();
			prop.load(getClass().getClassLoader().getResourceAsStream(fileName));
			Set<Entry<Object, Object>> entrySet = prop.entrySet();
	        ServletContext servletContext = getServletContext();
	        for(Entry<Object, Object> entry:entrySet){
	            servletContext.setAttribute(entry.getKey().toString(), entry.getValue());
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
