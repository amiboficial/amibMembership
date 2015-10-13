package mx.amib.sistemas.membership.service;

import mx.amib.sistemas.membership.model.User;
import mx.amib.sistemas.membership.service.exception.BlockedUserException;
import mx.amib.sistemas.membership.service.exception.NoApplicationRolesException;
import mx.amib.sistemas.membership.service.exception.NonApprovedUserException;
import mx.amib.sistemas.membership.service.exception.WrongPasswordAlgorithm;

public interface AuthenticationService {
	public User validateUserNameAndPassword(String userName, String password) throws BlockedUserException, NonApprovedUserException, WrongPasswordAlgorithm;
	public User validateUserNameAndPasswordAndApplication( String userName, String password, String uuidApplication ) throws BlockedUserException, NonApprovedUserException, NoApplicationRolesException, WrongPasswordAlgorithm;
	public void notifyFailedValidation(User user);
	public void notifySuccessfulValidation(User user);
	public String generateApiKey(User user);
}
