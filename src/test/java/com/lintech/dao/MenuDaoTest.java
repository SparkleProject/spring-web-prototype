package com.lintech.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.lintech.TestApplication;
import com.lintech.core.easyui.Menu;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = TestApplication.class)
@ActiveProfiles("test")
public class MenuDaoTest {

	@Autowired
	private MenuRepository menuRepository;

	@Test
	public void findByPage() {
		Page<Menu> result = menuRepository.findAll(PageRequest.of(0, 10));
		System.out.println(result.getContent().size());
	}

	@Test
	public void findAll() {
		List<Menu> list = menuRepository.findAll();
		for (Menu menu : list) {
			System.out.println(menu);
		}
	}
}
