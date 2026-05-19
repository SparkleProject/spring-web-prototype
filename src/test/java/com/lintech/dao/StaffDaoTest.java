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
import com.lintech.entity.Staff;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = TestApplication.class)
@ActiveProfiles("test")
public class StaffDaoTest {

	@Autowired
	private StaffRepository staffRepository;

	@Test
	public void testFindByPage() {
		Page<Staff> result = staffRepository.findAll(PageRequest.of(0, 10));
		System.out.println(result.getContent().size());
	}

	@Test
	public void findAll() {
		List<Staff> list = staffRepository.findAll();
		for (Staff staff : list) {
			System.out.println(staff);
		}
	}
}
