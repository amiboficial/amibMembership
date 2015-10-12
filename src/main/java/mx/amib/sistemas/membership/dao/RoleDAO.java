package mx.amib.sistemas.membership.dao;

import java.util.List;

import mx.amib.sistemas.membership.model.Role;

public interface RoleDAO {
	//métodos básicos
	public long count();
	public List<Role> getAll();
	public Role get(long idApplication, long numberRole);
	public Role save(Role role);
	public Role update(Role role);
	public void delete(long idApplication, long numberRole);
	
	//métodos complementarios
	public List<Role> getAllByIdApplication(long idApplication);
	public List<Role> getAllByIdUser(long idUser);
	public List<Role> getAllByIdUserAndUuidApplication(long idUser, String uuidApplication);
	public List<Role> getAllByIdUserAndIdApplication(long idUser, long idApplication);
	public List<Role> getAllByUserName(String userName);
	public long getNextNumberSeq(long idApplication);
	
}
