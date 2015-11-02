package mx.amib.sistemas.membership.dao;

import java.util.List;

import mx.amib.sistemas.membership.model.Application;

public interface ApplicationDAO {
	//métodos básicos
	public long count();
	public Application get(long id);
	public List<Application> getAll();
	public Application save(Application application);
	public Application update(Application application);
	public void delete(long id);
	
	//métodos complementarios
	public Application getByUuid(String uuid);
	public long countGetByUuid(String uuid);
	public long countGetByNameLowercase(String nameLowercase);
}
