package mx.amib.sistemas.membership.dao;

import java.util.List;

import mx.amib.sistemas.membership.model.Application;

public interface ApplicationDAO {
	public long count();
	public Application get(long id);
	public List<Application> getAll();
	public Application save(Application application);
	public Application update(Application application);
	public void delete(long id);
	
	public Application findByUuid(String uuid);
	public long countFindByUuid(String uuid);
}
