package com.lintech.core.test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
/**
 * 测试数据库操作基类
 */
@ContextConfiguration(locations = "classpath*:/**/applicationContext-database.xml") 
public abstract class AbstractDaoTest extends AbstractJUnit4SpringContextTests {
	
}
