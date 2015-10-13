package mx.amib.sistemas.membership.dao;

import java.util.List;

import mx.amib.sistemas.membership.model.PathRestriction;

public interface PathRestrictionDAO {
	//métodos básicos
	public long count();
	public List<PathRestriction> getAll();
	public PathRestriction get(long idApplication, long numberRole, long numberPath);
	public PathRestriction save(PathRestriction pathRestriction);
	public PathRestriction update(PathRestriction pathRestriction);
	public void delete(long idApplication, long numberRole, long numberPath);
	
	//métodos complementarios
	public List<PathRestriction> getAllByIdApplication(long idApplication);
	public List<PathRestriction> getAllByIdApplicationAndNumberRole(long idApplication, long numberRole);
	public List<PathRestriction> getAllByIdApplicationAndNumberPath(long idApplication, long numberPath);
}
