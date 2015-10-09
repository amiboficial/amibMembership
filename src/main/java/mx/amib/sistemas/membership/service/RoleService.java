package mx.amib.sistemas.membership.service;

import java.util.List;

import mx.amib.sistemas.membership.model.Role;
import mx.amib.sistemas.membership.service.exception.NonValidDeleteOperationException;

public interface RoleService {
	public long count();
	public List<Role> getAll();
	public List<Role> getAllByIdApplication(long idApplication);
	public Role get(long id);
	public Role save(Role Role);
	public Role update(Role Role);
	public void delete(long idApplication, long numberRole) throws NonValidDeleteOperationException;
}
