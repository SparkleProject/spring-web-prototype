package com.lintech.core;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.lintech.core.exception.BusinessException;

/**
 * 控制层基础类
 */
public class BaseController {
    protected Logger logger=LoggerFactory.getLogger(getClass());
    
    @Autowired
    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * 统一异常处理
     * ajax请求（有@ResponseBody的Controller）发生错误，输出JSON。<br>
     * <p>
     * json格式如：{"msg":"exception msg","success":"false"}
     * </p>
     * 页面请求（无@ResponseBody的Controller）发生错误，输出错误页面。<br>
     * <p>
     * 页面输出的内容格式：exception msg
     * <p>
     * 
     * 开发步骤<br>
     * 确定spring-mvc.xml是否包含以下配置：<br>
     * <p>
     * <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"/><br>
     * <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"/><br>
     * <bean id="handlerExceptionResolver" class="cn.lintech.exception.AnnotationHandlerMethodExceptionResolver"><br>
     * <property name="defaultErrorView" value="error.jsp" /><br>
     * <property name="messageConverters" ref="messageConverters" /><br>
     * <util:list id="messageConverters"><br>
     * <bean class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter"><br>
     * <property name="supportedMediaTypes"><br>
     * <list><br>
     * <value>application/x-javascript</value><br>
     * <value>application/json</value><br>
     * <value>application/javascript</value><br>
     * <value>text/json</value><br>
     * <value>text/javascript</value><br>
     * </list><br>
     * </property><br>
     * </bean><br>
     * </util:list><br>
     * </bean><br>
     * </p>
     * 
     * @param ex
     * @param request
     * @return
     * @throws Exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleException(Exception ex, HttpServletRequest request) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        String exceptionMessage = getExceptionMessage(ex, request);
        model.put("success", "false");
        model.put("msg", exceptionMessage);
        logger.info("Exception Message:"+exceptionMessage);
        if(logger.isDebugEnabled()){
            ex.printStackTrace();
        }
        return new ModelAndView("errors/error", model);
    }

    private String getExceptionMessage(Exception ex, HttpServletRequest request) {
        if (ex instanceof BusinessException) {
        	BusinessException e = (BusinessException) ex;
            String excode = e.getExcode();
            if (excode == null || excode.isEmpty()) {
                return e.getMessage();
            } else {
                Locale locale = RequestContextUtils.getLocale(request);
                return messageSource.getMessage(excode, null, e.getMessage(), locale);
            }
        } else {
        	ex.printStackTrace();
            return "Unknown error";
        }
    }
}
