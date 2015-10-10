package mx.amib.sistemas.membership.dao.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;

import mx.amib.sistemas.membership.dao.*;
import mx.amib.sistemas.membership.model.Application;
import mx.amib.sistemas.membership.model.Path;
import mx.amib.sistemas.membership.model.Role;

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
public class DAOTest {
		
	@Autowired
	private ApplicationDAO applicationDAO;
	@Autowired
	private RoleDAO roleDAO;
	
	@Ignore 
	public void testApplicationDaoGet(){
		assertTrue(applicationDAO.count() > 0);
	}
	
	@Ignore 
	@Test
	public void testApplicationDaoGetAll(){
		List<Application> applicationList = applicationDAO.getAll();
		
		System.out.println(applicationList);
		assertTrue(applicationList.size() > 0);
	}
	@Ignore 
	@Test
	public void testApplicationDaoSave(){
		Application application = new Application();
		application.setUuid("6301285b-02ce-4760-8b26-d2472e6f4672");
		application.setName("Prueba 1");
		application.setNameLowercase("prueba 1");
		application.setActive(true);
		application = applicationDAO.save(application);
		System.out.println(application);
		assertTrue(application != null);
	}
	
	@Ignore 
	@Test
	public void testApplicationDaoFindByUuid(){
		Application application = applicationDAO.getByUuid("30873f55-c21f-4589-aa66-883f3563xb34");
		assertEquals(application,null);
	}
	
	
	@Ignore 
	@Test
	public void testApplicationDaoSaveWithRolesAndPaths(){
		Application application = new Application();
		Role role1 = new Role();
		Role role2 = new Role();
		Path path1 = new Path();
		
		application.setUuid("6301285b-02ce-4760-8b26-d2472e6f4672");
		application.setName("Prueba 2");
		application.setNameLowercase("prueba 2");
		application.setActive(true);
		application.setRoles(new HashSet<Role>());
		application.setPaths(new HashSet<Path>());
		
		role1.setName("R1");
		role2.setName("R2");
		role1.setDescription("un rol");
		role2.setDescription("otro rol");
		role1.setActive(true);
		role2.setActive(true);
		role1.setNumberRole(1);
		role2.setNumberRole(2);
		role1.setApplication(application);
		role2.setApplication(application);
		application.getRoles().add(role1);
		application.getRoles().add(role2);
		
		path1.setPath("/solo/un/path");
		path1.setPathLowercase("/solo/un/path");
		path1.setNumberPath(1);
		path1.setApplication(application);
		application.getPaths().add(path1);
		
		application = applicationDAO.save(application);
		System.out.println(application);
		assertTrue(application != null);
	}
	
	@Ignore 
	@Test
	public void testRoleDaoGetAllByIdApplication(){
		List<Role> foundRoles = null;
		long idApplication = 1L;
		foundRoles = roleDAO.getAllByIdApplication(idApplication);
		System.out.println(foundRoles);
		assertTrue(foundRoles.size() > 0);
	}
	
	@Test
	public void testRoleDaoGetAllByIdUser(){
		List<Role> foundRoles = null;
		long idUser = 1L;
		foundRoles = roleDAO.getAllByIdUser(idUser);
		System.out.println(foundRoles);
		assertTrue(foundRoles.size() > 0);
	}
}
