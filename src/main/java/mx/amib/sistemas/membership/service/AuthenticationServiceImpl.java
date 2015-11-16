package mx.amib.sistemas.membership.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.membership.dao.UserDAO;
import mx.amib.sistemas.membership.model.Role;
import mx.amib.sistemas.membership.model.User;
import mx.amib.sistemas.membership.service.exception.BlockedUserException;
import mx.amib.sistemas.membership.service.exception.NoApplicationRolesException;
import mx.amib.sistemas.membership.service.exception.NonApprovedUserException;

@Scope("singleton")
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final int MAX_ATTEMPTS = 10;
	private static final long API_KEY_EXPIRE_TIME_MILIS = 7200000L; //120 minutos
	private static final String UUID_MEMBERSHIP_APP = "79449d33-cdb5-4bb1-82a3-4b7c95eabc32";
	private static final String ADMIN_ROLE_NAME = "administrator";
	//Map que contiene llaves de API generadas, con un timestamp de caducidad
	private Map<String,Long> generatedApiKeys = new HashMap<String,Long>();
	
	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	public User validateUserNameAndPassword(String userName, String password) throws BlockedUserException, NonApprovedUserException, NoSuchAlgorithmException {
		
		boolean valid = false;
		User user = null;
		
		String hashSaltedPwd;
		
		try{
			user = userDAO.getByUserName(userName);
			hashSaltedPwd = user.getPasswordSalt() + password;
			hashSaltedPwd = Hex.encodeHexString(MessageDigest.getInstance(user.getPasswordFormat()).digest( hashSaltedPwd.getBytes() ));
			
			if( user.isLockedOut() ){
				throw new BlockedUserException();
			}
			if( user.getPassword().compareTo(hashSaltedPwd) == 0 ){
				if(!user.isApproved()){
					throw new NonApprovedUserException();
				}
				valid = true;
				this.notifySuccessfulValidation(user);
			}
			else{
				this.notifyFailedValidation(user);
			}
			
		}
		catch(NoResultException nre){
			valid = false;
		}
		
		if(!valid)
			user = null;
		
		return user;
	}

	@Transactional
	public User validateUserNameAndPasswordAndApplication(String userName,
			String password, String uuidApplication) throws BlockedUserException, NonApprovedUserException, NoApplicationRolesException, NoSuchAlgorithmException {
		
		boolean valid = false;
		User user = null;
		
		String hashSaltedPwd;
		
		try{
			user = userDAO.getByUserName(userName);
			hashSaltedPwd = user.getPasswordSalt() + password;
			hashSaltedPwd = Hex.encodeHexString(MessageDigest.getInstance(user.getPasswordFormat()).digest( hashSaltedPwd.getBytes() ));
			
			if( user.isLockedOut() ){
				throw new BlockedUserException();
			}
			if( user.getPassword().compareTo(hashSaltedPwd) == 0 ){
				if(!user.isApproved()){
					throw new NonApprovedUserException();
				}
				
				//Verifica que haya por lo menos un rol de la correspondiente aplicación
				for(Role r : user.getRoles()){
					if(r.getApplication().getUuid().compareToIgnoreCase(uuidApplication) == 0){
						valid = true;
						break;
					}
				}
				if(valid)
					this.notifySuccessfulValidation(user);
				else
					throw new NoApplicationRolesException();
				
			}
			else{
				this.notifyFailedValidation(user);
			}
			
		}
		catch(NoResultException nre){
			valid = false;
		}
		
		if(!valid)
			user = null;
		
		return user;
	}
	
	@Transactional
	public void notifyFailedValidation(User user) {
		user.setFailedAttempts( user.getFailedAttempts() + 1 );
		user.setLastActivity( Calendar.getInstance().getTime() );
		if(user.getFailedAttempts() >= MAX_ATTEMPTS){
			user.setLockedOut(true);
			user.setLastLockedOut( Calendar.getInstance().getTime() );
		}
		userDAO.update(user);
	}

	@Transactional
	public void notifySuccessfulValidation(User user) {
		user.setFailedAttempts( 0 );
		user.setLastActivity( Calendar.getInstance().getTime() );
		user.setLastLogin( Calendar.getInstance().getTime() );
		userDAO.update(user);
	}
	
	/***
	 * 
	 * Genera una llave de API si es que el usuario es el administrador, 
	 * con esta llave, se pueden llamar a los métodos de controlador CRUD
	 * de los usuarios, roles y aplicativos.
	 * 
	 * @param user
	 * @return llave de api, si es que la aplicación corresponde a "Consola de usuarios"
	 * y el usuario tiene el rol de "administrator"
	 */
	public String generateApiKey(User user){
		Set<Role> roles = user.getRoles();
		String generatedKey = "";
		
		for( Role x : roles ){
			if(x.getApplication().getUuid().compareToIgnoreCase(UUID_MEMBERSHIP_APP) == 0 && x.getName() == ADMIN_ROLE_NAME){
				generatedKey = UUID.randomUUID().toString().replace("-", "");
				generatedApiKeys.put(generatedKey, Calendar.getInstance().getTimeInMillis() + API_KEY_EXPIRE_TIME_MILIS);
				break;
			}
		}
		
		return generatedKey;
	}

	/**
	 * Revisa si la llave de API es válida
	 * 
	 * @param apiKey
	 * @return true si la llave de API es válida
	 */
	public boolean checkApiKey(String apiKey){
		boolean valid = false;
		long expDt;
		
		//revisa que este contenida en el Map
		if(this.generatedApiKeys.containsKey(apiKey)){
			expDt = this.generatedApiKeys.get(apiKey);
			//revisa que no haya expirado ya
			if((expDt - Calendar.getInstance().getTimeInMillis()) > 0){
				valid = true;
			}
			else
				this.generatedApiKeys.remove(apiKey); //la borra del map si es que ya ha expirado
		}
		
		return valid;
	}
	
	/***
	 * Borra la llave de API 
	 * 
	 * @param apiKey
	 * @return true si es que la llave estaba almaceda y fue borrada
	 */
	public boolean deleteApiKey(String apiKey) {
		boolean contains = this.generatedApiKeys.containsKey(apiKey);
		
		if(contains)
			this.generatedApiKeys.remove(apiKey);
		
		return contains;
	}
	
}
