package mx.amib.sistemas.membership.dao;

import java.util.List;

import mx.amib.sistemas.membership.model.Path;

public interface PathDAO {
	public long count();
	public Path get(long idApplication, long numberPath);
	public List<Path> getAll();
	public Path save(Path path);
	public Path update(Path path);
	public void delete(long idApplication, long numberPath);
}
