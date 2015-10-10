package mx.amib.sistemas.membership.service;

import java.util.List;

import mx.amib.sistemas.external.membership.RoleTO;
import mx.amib.sistemas.membership.model.Role;
import mx.amib.sistemas.membership.model.convert.RoleTransportConverter;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class RoleServiceTest {

	@Autowired
	private RoleService roleService;
	
	@Ignore
	@Test
	public void getAllByIdApplicationTest(){
		List<Role> list = roleService.getAllByIdApplication(1);

		assertTrue(list.size() > 0);
	}
	
	@Ignore
	@Test
	public void getAllByIdApplicationTestWithTO(){
		List<Role> list = roleService.getAllByIdApplication(1);
		List<RoleTO> listT = RoleTransportConverter.convertToTranport(list);
		
		assertTrue(listT.size() > 0);
	}
	
	@Test 
	public void getTest(){
		Role r;
		long idApplication = 1;
		long numberRole = 1;
		
		r = roleService.get(idApplication, numberRole);
		
		System.out.println(r);
		
		assertTrue(r.getIdApplication() == 1L && r.getNumberRole() == 1L);
	}
	
	
}
