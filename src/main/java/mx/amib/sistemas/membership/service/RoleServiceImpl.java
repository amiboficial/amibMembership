package mx.amib.sistemas.membership.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.amib.sistemas.membership.dao.RoleDAO;
import mx.amib.sistemas.membership.model.Role;
import mx.amib.sistemas.membership.service.exception.NonValidDeleteOperationException;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;
	
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Role> getAllByIdApplication(long idApplication) {
		return roleDAO.getAllByIdApplication(idApplication);
	}
	
	public Role get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Role save(Role Role) {
		// TODO Auto-generated method stub
		return null;
	}

	public Role update(Role Role) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(long idApplication, long numberRole) throws NonValidDeleteOperationException {
		// TODO Auto-generated method stub		
	}
	

}
