package mx.amib.sistemas.membership.service;

import java.util.List;

import mx.amib.sistemas.membership.model.Role;
import mx.amib.sistemas.membership.service.exception.NonValidDeleteOperationException;
import mx.amib.sistemas.membership.service.exception.NonValidSaveOperationException;
import mx.amib.sistemas.membership.service.exception.NonValidUpdateOperationException;

public interface RoleService {
	public long count();
	public List<Role> getAll();
	public Role get(long idApplication, long numberRole);
	public Role save(Role Role) throws NonValidSaveOperationException;
	public Role update(Role Role) throws NonValidUpdateOperationException;
	public void delete(long idApplication, long numberRole) throws NonValidDeleteOperationException;
	
	public List<Role> getAllByIdApplication(long idApplication);
	public List<Role> getAllByIdUser(long idUser);
	public List<Role> getAllByIdUserAndUuidApplication(long idUser, String uuidApplication);
	public List<Role> getAllByUserName(String userName);
	public long getNextNumberSeq(long idApplication);
}
