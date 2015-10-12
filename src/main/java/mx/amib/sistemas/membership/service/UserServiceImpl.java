package mx.amib.sistemas.membership.service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.security.*;

import javax.persistence.NoResultException;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.membership.dao.UserDAO;
import mx.amib.sistemas.membership.model.Role;
import mx.amib.sistemas.membership.model.User;
import mx.amib.sistemas.membership.service.exception.*;

@Scope("singleton")
@Service("userService")
public class UserServiceImpl implements UserService{

	private static final int MAX_ATTEMPTS = 10;
	private static final String PASSWORD_FORMAT = "MD5";
			
	@Autowired
	private UserDAO userDAO;
	
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
	public User save(User user) throws WrongPasswordAlgorithm {
		//el campo password se codificará
		String hashSaltedPwd;
		
		user.setPasswordFormat(PASSWORD_FORMAT);
		user.setUuid( UUID.randomUUID().toString() );
		user.setPasswordSalt( UUID.randomUUID().toString().replace("-", "") );
		
		hashSaltedPwd = user.getPasswordSalt() + user.getPassword();
		
		try {
			user.setPassword( Hex.encodeHexString(MessageDigest.getInstance(PASSWORD_FORMAT).digest(hashSaltedPwd.getBytes())) );
		} catch (NoSuchAlgorithmException e) {
			throw new WrongPasswordAlgorithm();
		}
		
		return userDAO.save(user);
	}

	@Transactional
	public User update(User user) {
		//el campo password NO se codificará, debe llamarse a otro método del service que se encarge de eso
		return userDAO.update(user);
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

	@Transactional
	public boolean validateUserNameAndPassword(String userName, String password) throws BlockedUserException, NonApprovedUserException, WrongPasswordAlgorithm {
		
		boolean valid = false;
		User user;
		
		String hashSaltedPwd;
		
		try{
			user = this.getByUserName(userName);
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
			user = null;
		}
		catch(NoSuchAlgorithmException e) {
			throw new WrongPasswordAlgorithm();
		}
		
		return valid;
	}

	@Transactional
	public boolean validateUserNameAndPasswordAndApplication(String userName,
			String password, String uuidApplication) throws BlockedUserException, NonApprovedUserException, NoApplicationRolesException, WrongPasswordAlgorithm {
		
		boolean valid = false;
		User user;
		
		String hashSaltedPwd;
		
		try{
			user = this.getByUserName(userName);
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
			user = null;
		}
		catch(NoSuchAlgorithmException e) {
			throw new WrongPasswordAlgorithm();
		}
		
		return valid;
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

}
