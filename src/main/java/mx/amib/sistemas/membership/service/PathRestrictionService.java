package mx.amib.sistemas.membership.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import mx.amib.sistemas.membership.model.Path;
import mx.amib.sistemas.membership.model.PathRestrictionId;
import mx.amib.sistemas.membership.model.Role;

public interface PathRestrictionService {
	public void save(long idApplication, long numRole, long numPath);
	public void delete(long idApplication, long numRole, long numPath);
	
	public List<Path> getAllByNumberRole(long idApplication, long numberRole);
	public List<Role> getAllByNumberPath(long idApplication, long numberPath);
	public void saveChangesByNumberRole(long idApplication, long numberRole, Set<Long> restrictedNumbersPath);
	public void saveChangesByNumberPath(long idApplication, long numberPath, Set<Long> restrictedNumbersRole);
}
