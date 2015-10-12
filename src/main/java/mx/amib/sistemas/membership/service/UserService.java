package mx.amib.sistemas.membership.service;

import java.util.List;

import mx.amib.sistemas.membership.model.User;
import mx.amib.sistemas.membership.service.exception.BlockedUserException;
import mx.amib.sistemas.membership.service.exception.NoApplicationRolesException;
import mx.amib.sistemas.membership.service.exception.NonApprovedUserException;
import mx.amib.sistemas.membership.service.exception.WrongPasswordAlgorithm;

public interface UserService {
	public long count();
	public User get(long id);
	public List<User> getAll();
	public User save(User user) throws WrongPasswordAlgorithm;
	public User update(User user);
	public void delete(long id);

	public User getByUserName(String userName);
	public List<User> findAll(int max, int offset, String sort, String order);
	public long countFindAll();
	public List<User> findAllByUserNameLike(int max, int offset, String sort, String order, String userName);
	public long countFindAllByUserNameLike(String userName);
	
	public boolean validateUserNameAndPassword(String userName, String password) throws BlockedUserException, NonApprovedUserException, WrongPasswordAlgorithm;
	public boolean validateUserNameAndPasswordAndApplication( String userName, String password, String uuidApplication ) throws BlockedUserException, NonApprovedUserException, NoApplicationRolesException, WrongPasswordAlgorithm;
	public void notifyFailedValidation(User user);
	public void notifySuccessfulValidation(User user);
}
