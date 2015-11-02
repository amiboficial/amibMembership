package mx.amib.sistemas.membership.service;

import java.util.List;

import mx.amib.sistemas.membership.model.Application;
import mx.amib.sistemas.membership.service.exception.*;

public interface ApplicationService {
	public long count();
	public List<Application> getAll();
	public Application get(long id);
	public Application save(Application application) throws UuidNonUniqueException, UsernameNonUniqueException;
	public Application update(Application application) throws UuidNonUniqueException, UsernameNonUniqueException;
	public void delete(long id) throws NonValidDeleteOperationException;
}