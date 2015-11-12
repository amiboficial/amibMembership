package mx.amib.sistemas.membership.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.membership.model.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class PathRestrictionServiceTest {
	
	@Autowired
	private PathRestrictionService pathRestrictionService;
	
	@Ignore
	@Test
	public void saveChangesByNumberRoleTest(){
		long idApplication;
		long numberRole;
		Set<Long> restrictedNumbersPath;
		
		idApplication = 1;
		numberRole = 8;
		restrictedNumbersPath = new HashSet<Long>();
		restrictedNumbersPath.add(2L);
		
		pathRestrictionService.saveChangesByNumberRole(idApplication, numberRole, restrictedNumbersPath);
	}
	/*
	@Test
	public void */
}
