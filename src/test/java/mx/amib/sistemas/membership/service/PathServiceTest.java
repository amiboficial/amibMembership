package mx.amib.sistemas.membership.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.membership.model.Path;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class PathServiceTest {
	@Autowired
	private PathService pathService;
	
	@Ignore
	@Test
	public void saveMultipleFromStringListTest(){
		long idApplication;
		List<String> multiplePaths;
		List<Path> result;
		
		idApplication = 1;
		multiplePaths = new ArrayList<String>();
		
		multiplePaths.add("/x/y/z1");
		multiplePaths.add("/x/y/z2");
		multiplePaths.add("/x/y/z3");
		multiplePaths.add("/x/y/z4");
		multiplePaths.add("/x/y/z5");
		
		result = pathService.saveMultipleFromStringList(idApplication, multiplePaths);
		
		System.out.println(multiplePaths);
		
		Assert.assertEquals(result.size() == 5, true);
	}
	
	@Test
	public void deleteMultipleTest(){
		long idApplication = 43;
		List<Long> numbersPath = new ArrayList<Long>();
		boolean anyOnDB = false;
		
		numbersPath.add(2L);
		numbersPath.add(5L);
		numbersPath.add(7L);
		
		pathService.deleteMultiple(idApplication, numbersPath);
		
		for(Iterator<Long> itr = numbersPath.iterator(); itr.hasNext();){
			long numberPath = itr.next();
			Path p = pathService.get(idApplication, numberPath);
			if(p!=null){
				anyOnDB = true;
				break;
			}
		}
		
		Assert.assertEquals(false, anyOnDB);
	}
	
}
