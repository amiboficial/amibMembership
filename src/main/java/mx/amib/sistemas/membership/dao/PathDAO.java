package mx.amib.sistemas.membership.dao;

import java.util.List;

import mx.amib.sistemas.membership.model.Path;

public interface PathDAO {
	//métodos básicos
	public long count();
	public List<Path> getAll();
	public Path get(long idApplication, long numberPath);
	public Path save(Path path);
	public Path update(Path path);
	public void delete(long idApplication, long numberPath);
	
	//métodos complementarios
	public List<Path> getAllByIdApplication(long idApplication);
	public long getNextNumberSeq(long idApplication);
}