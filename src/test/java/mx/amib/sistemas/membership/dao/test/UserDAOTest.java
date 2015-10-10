package mx.amib.sistemas.membership.dao.test;

import java.util.List;

import junit.framework.Assert;
import mx.amib.sistemas.membership.dao.UserDAO;
import mx.amib.sistemas.membership.model.User;

import org.junit.Before;
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
public class UserDAOTest {

	@Autowired
	private UserDAO userDAO;
	
	private final int MAX = 10;
	private final int OFFSET = 0;
	private String sort;
	private String order;
	
	private String userName;
	
	@Before
	public void initParams(){
		this.sort = "id";
		this.order = "asc";
		
		this.userName = "nach";
	}
	
	@Test
	public void findAllByUserNameLikeTest(){
		long count = userDAO.countFindAllByUserNameLike(userName);
		List<User> result = userDAO.findAllByUserNameLike(MAX, OFFSET, sort, order, userName);
		
		System.out.println(count);
		System.out.println(result);
		
		assertEquals(count, 2);
	}
	
	@Test
	public void findAllTest(){
		long count;
		List<User> result;
		
		count = userDAO.countFindAll();
		result = userDAO.findAll(MAX, OFFSET, sort, order);

		System.out.println(count);
		System.out.println(result);
		
		assertTrue(count > 0);
	}
}
