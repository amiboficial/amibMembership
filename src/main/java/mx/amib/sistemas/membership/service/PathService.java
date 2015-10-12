package mx.amib.sistemas.membership.service;

import java.util.List;

import mx.amib.sistemas.membership.model.Path;

public interface PathService {
	
	public long count();
	public List<Path> getAll();
	public Path get(long idApplication, long numberPath);
	public Path save(Path path);
	public Path update(Path path);
	public void delete(long idApplication, long numberPath);
	
	public List<Path> getAllByIdApplication(long idApplication);
	public List<Path> getUserInApplicationRestrictedPaths(long idUser, String uuidApplication);

}