package mx.amib.sistemas.membership.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.membership.dao.ApplicationDAO;
import mx.amib.sistemas.membership.dao.RoleDAO;
import mx.amib.sistemas.membership.model.Application;
import mx.amib.sistemas.membership.model.Role;
import mx.amib.sistemas.membership.service.exception.*;

@Scope("singleton")
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	public static final String MEMBERSHIP_APP_UUID = "79449d33-cdb5-4bb1-82a3-4b7c95eabc32";
	
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private ApplicationDAO applicationDAO;
	
	public long count() {
		return roleDAO.count();
	}

	public List<Role> getAll() {
		return roleDAO.getAll();
	}

	public Role get(long idApplication, long numberRole) {
		return roleDAO.get(idApplication, numberRole);
	}

	@Transactional
	public Role save(Role role) throws NonValidSaveOperationException{
		Application application;
		
		application = applicationDAO.get(role.getIdApplication());
		role.setNumberRole( roleDAO.getNextNumberSeq(application.getId()) );
		//si es un rol de la aplicación "membership", no permite guardarlo
		if(application.getUuid().compareToIgnoreCase(MEMBERSHIP_APP_UUID) == 0){
			throw new NonValidSaveOperationException();
		}
		return roleDAO.save(role);
	}

	@Transactional
	public Role update(Role role) throws NonValidUpdateOperationException {
		Application application;
		
		application = applicationDAO.get(role.getIdApplication());
		//si es un rol de la aplicación "membership", no permite modificarlo
		if(application.getUuid().compareToIgnoreCase(MEMBERSHIP_APP_UUID) == 0){
			throw new NonValidUpdateOperationException();
		}
		return roleDAO.update(role);
	}

	@Transactional
	public void delete(long idApplication, long numberRole) throws NonValidDeleteOperationException {
		Application application;
		
		application = applicationDAO.get(idApplication);
		//si es un rol de la aplicación "membership", no permite el borrado
		if(application.getUuid().compareToIgnoreCase(MEMBERSHIP_APP_UUID) == 0){
			throw new NonValidDeleteOperationException();
		}
		roleDAO.delete(idApplication, numberRole);
	}
	
	public List<Role> getAllByIdApplication(long idApplication) {
		return roleDAO.getAllByIdApplication(idApplication);
	}

	public List<Role> getAllByIdUser(long idUser) {
		return roleDAO.getAllByIdUser(idUser);
	}

	public List<Role> getAllByIdUserAndUuidApplication(long idUser,
			String uuidApplication) {
		return roleDAO.getAllByIdUserAndUuidApplication(idUser, uuidApplication);
	}

	public List<Role> getAllByUserName(String userName) {
		return roleDAO.getAllByUserName(userName);
	}

	public long getNextNumberSeq(long idApplication) {
		return roleDAO.getNextNumberSeq(idApplication);
	}
}
