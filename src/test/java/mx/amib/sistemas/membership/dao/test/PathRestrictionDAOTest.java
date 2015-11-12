package mx.amib.sistemas.membership.dao.test;

import org.junit.Test;

import java.util.List;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.membership.dao.PathRestrictionDAO;
import mx.amib.sistemas.membership.model.Path;
import mx.amib.sistemas.membership.model.PathRestriction;
import mx.amib.sistemas.membership.model.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class PathRestrictionDAOTest {

	@Autowired
	private PathRestrictionDAO pathRestrictionDAO;
	
	@Test
	public void getRestrictedPathsByIdApplicationAndNumberPathTest(){
		List<Path> result = null;
		long idApplication = 1L;
		long numberRole = 8L;
		
		result = pathRestrictionDAO.getRestrictedPathsByIdApplicationAndNumberPath(idApplication, numberRole);
		
		//System.out.println(result);
		
		Assert.assertEquals(result != null && result.size() > 0, true);
	}
	
	@Test
	public void getRestrictedRolesByIdApplicationAndNumberPath(){
		List<Role> result = null;
		long idApplication = 1L;
		long numberPath = 2L;
		
		result = pathRestrictionDAO.getRestrictedRolesByIdApplicationAndNumberPath(idApplication, numberPath);
		
		//System.out.println(result);
		
		Assert.assertEquals(result != null && result.size() > 0, true);
	}
}
