package mx.amib.sistemas.membership.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.membership.dao.ApplicationDAO;
import mx.amib.sistemas.membership.model.Application;
import mx.amib.sistemas.membership.service.exception.*;

@Scope("singleton")
@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

	public static final String MEMBERSHIP_APP_UUID = "79449d33-cdb5-4bb1-82a3-4b7c95eabc32";
	
	@Autowired
	private ApplicationDAO applicationDAO;
	
	public long count(){
		return applicationDAO.count();
	}
	
	public List<Application> getAll() {
		return applicationDAO.getAll();
	}

	public Application get(long id) {
		return applicationDAO.get(id);
	}

	@Transactional
	public Application save(Application application) throws UuidNonUniqueException, UsernameNonUniqueException {
		if(application.getUuid() != null && applicationDAO.countGetByUuid(application.getUuid()) != 0)
			throw new UuidNonUniqueException();
		else if(applicationDAO.countGetByNameLowercase(application.getName().toLowerCase()) != 0)	
			throw new UsernameNonUniqueException();
		else {
			application.setUuid(UUID.randomUUID().toString());
			application.setNameLowercase(application.getName().toLowerCase());
			application = applicationDAO.save(application);
		}
		return application;
	}

	@Transactional
	public Application update(Application application) throws UuidNonUniqueException, UsernameNonUniqueException {
		return applicationDAO.update(application);
	}

	@Transactional
	public void delete(long id) throws NonValidDeleteOperationException {
		Application appToDelete = this.get(id);
		
		if(appToDelete.getUuid().compareToIgnoreCase(MEMBERSHIP_APP_UUID) == 0)
			throw new NonValidDeleteOperationException();
			
		applicationDAO.delete(id);
	}

}

