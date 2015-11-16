package mx.amib.sistemas.membership.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.security.*;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.membership.dao.ApplicationDAO;
import mx.amib.sistemas.membership.dao.PathRestrictionDAO;
import mx.amib.sistemas.membership.dao.RoleDAO;
import mx.amib.sistemas.membership.dao.UserDAO;
import mx.amib.sistemas.membership.model.Path;
import mx.amib.sistemas.membership.model.PathRestriction;
import mx.amib.sistemas.membership.model.PathRestrictionId;
import mx.amib.sistemas.membership.model.Role;
import mx.amib.sistemas.membership.model.User;
import mx.amib.sistemas.membership.service.exception.*;

@Scope("singleton")
@Service("userService")
public class UserServiceImpl implements UserService{

	private static final String PASSWORD_FORMAT = "MD5";
			
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private PathRestrictionDAO pathRestricionDAO;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private ApplicationDAO applicationDAO;
	
	public long count() {
		return userDAO.count();
	}

	public User get(long id) {
		
		return userDAO.get(id);
	}

	public List<User> getAll() {
		return userDAO.getAll();
	}

	@Transactional
	public User save(User user) throws UsernameNonUniqueException, NoSuchAlgorithmException {
		//el campo password se codificará
		String hashSaltedPwd;
		
		user.setUserNameLowercase(user.getUserName().toLowerCase());
		user.setEmailLowercase(user.getEmail().toLowerCase());
		user.setPasswordFormat(PASSWORD_FORMAT);
		user.setUuid( UUID.randomUUID().toString() );
		user.setPasswordSalt( UUID.randomUUID().toString().replace("-", "") );
		hashSaltedPwd = user.getPasswordSalt() + user.getPassword();
		user.setPassword( Hex.encodeHexString(MessageDigest.getInstance(PASSWORD_FORMAT).digest(hashSaltedPwd.getBytes())) );
		
		if(this.getByUserName(user.getUserName())!=null){
			throw new UsernameNonUniqueException();
		}
		
		return userDAO.save(user);
	}

	@Transactional
	public User update(User user) {
		//el campo password NO se codificará, debe llamarse a otro método del service que se encarge de eso
		return userDAO.update(user);
	}

	public void updatePassword(long id, String cleanPassword) throws NoSuchAlgorithmException{
		User user = null;
		
		user = this.get(id);
		
		user.setUserNameLowercase(user.getUserName().toLowerCase());
		user.setEmailLowercase(user.getEmail().toLowerCase());
		user.setPasswordFormat(PASSWORD_FORMAT);
		user.setPasswordSalt( UUID.randomUUID().toString().replace("-", "") );
		cleanPassword = user.getPasswordSalt() + cleanPassword;
		user.setPassword( Hex.encodeHexString(MessageDigest.getInstance(PASSWORD_FORMAT).digest(cleanPassword.getBytes())) );
		
		userDAO.save(user);
	}
	
	@Transactional
	public void delete(long id) {
		userDAO.delete(id);
	}

	public User getByUserName(String userName) {
		return userDAO.getByUserName(userName);
	}

	public List<User> findAll(int max, int offset, String sort, String order) {
		return userDAO.findAll(max, offset, sort, order);
	}

	public long countFindAll() {
		return userDAO.countFindAll();
	}

	public List<User> findAllByUserNameLike(int max, int offset, String sort,
			String order, String userName) {
		return userDAO.findAllByUserNameLike(max, offset, sort, order, userName);
	}

	public long countFindAllByUserNameLike(String userName) {
		return userDAO.countFindAllByUserNameLike(userName);
	}

	public List<Role> getRoles(long id, long idApplication) {
		return roleDAO.getAllByIdUserAndIdApplication(id, idApplication);
	}

	public List<Path> getRestrictedPaths(long id, long idApplication) {
		List<Role> userRolesInApplication;
		List<PathRestriction> pathRestrictionsInApplication;
		Map<PathRestrictionId,Integer> pathRestrictionCounting = new HashMap<PathRestrictionId,Integer>();
		int totalPathRestrictionsInApplication = 0;
		//long idApplication = 0;
		List<Path> effectivePathRestrictions = new ArrayList<Path>();
		
		//idApplication = applicationDAO.getByUuid(uuidApplication).getId();
		pathRestrictionsInApplication = pathRestricionDAO.getAllByIdApplication(idApplication);
		totalPathRestrictionsInApplication = pathRestrictionsInApplication.size();
		
		//setea el conteo de cada una de las restricciones 0
		for(PathRestriction x : pathRestrictionsInApplication){
			PathRestrictionId prid = new PathRestrictionId(x.getIdApplication(),x.getNumberRole(),x.getNumberPath());
			pathRestrictionCounting.put(prid, 0);
		}
		
		//obtiene los roles de usuario en la aplicación
		userRolesInApplication = roleDAO.getAllByIdUserAndIdApplication(id, idApplication);
		
		//cuenta cada una de las restricciones del usuario contenidas por cada rol asignado
		for(Role r : userRolesInApplication){
			List<PathRestriction> pathRestrictionsInRol = pathRestricionDAO.getAllByIdApplicationAndNumberRole(idApplication, r.getNumberRole());
			for(PathRestriction x : pathRestrictionsInRol){
				PathRestrictionId prid = new PathRestrictionId(x.getIdApplication(),x.getNumberRole(),x.getNumberPath());
				pathRestrictionCounting.put( prid, pathRestrictionCounting.get(prid) + 1 );
				
				//si el conteo alcanzo el "numero maximo"
				//es decir, todos los roles de usuario restrigen el mismo path
				//un solo rol que no contenga dicha restricción, no lo incluría en la cuenta
				if(pathRestrictionCounting.get(prid) == totalPathRestrictionsInApplication){
					effectivePathRestrictions.add(x.getPath());
				}
				
			}
		}
		
		return effectivePathRestrictions;
	}

}
