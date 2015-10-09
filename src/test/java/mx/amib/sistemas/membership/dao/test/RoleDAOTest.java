package mx.amib.sistemas.membership.dao.test;

import mx.amib.sistemas.membership.dao.RoleDAO;

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
public class RoleDAOTest {

	@Autowired
	private RoleDAO roleDAO;
	
	@Test
	public void getNextRoleNumberSequence(){
		long next = roleDAO.getNextRoleNumberSequence(1);
		System.out.println("the next value is " + next);
		assertTrue(next > 0);
	}
	
}
