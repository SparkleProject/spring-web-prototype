package com.lintech.core.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(locations={"classpath*:/**/applicationContext-*.xml","classpath*:spring-mvc.xml"})
public abstract class AbstractControllerTest {
    @Autowired
    protected WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;
}
