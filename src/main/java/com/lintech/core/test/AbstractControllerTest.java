package com.lintech.core.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

/**
 * spring mvc 测试基类
 */
@SuppressWarnings("deprecation")
@ContextConfiguration(locations={"classpath*:/**/applicationContext-*.xml","classpath*:spring-mvc.xml"})
public abstract class AbstractControllerTest{
    @Autowired
    protected AnnotationMethodHandlerAdapter annotationHandlerAdapter;
    protected SimpleServletHandlerAdapter simpleHandlerAdapter;
    protected SimpleControllerHandlerAdapter simpleControllerHandlerAdapter;
    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;
 }
