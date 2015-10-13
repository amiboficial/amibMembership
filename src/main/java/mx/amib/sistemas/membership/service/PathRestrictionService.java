package mx.amib.sistemas.membership.service;

import java.util.List;

import mx.amib.sistemas.membership.model.Path;

public interface PathRestrictionService {
	public void save(long idApplication, long numRole, long numPath);
	public void delete(long idApplication, long numRole, long numPath);
	
	public List<Path> getAllByNumberRole(long idApplication, long numberRole);
	public List<Path> getAllByNumberPath(long idApplication, long numberPath);
}
