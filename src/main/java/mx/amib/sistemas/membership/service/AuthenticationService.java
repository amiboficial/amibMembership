package mx.amib.sistemas.membership.service;

import java.security.NoSuchAlgorithmException;
import mx.amib.sistemas.membership.model.User;
import mx.amib.sistemas.membership.service.exception.BlockedUserException;
import mx.amib.sistemas.membership.service.exception.NoApplicationRolesException;
import mx.amib.sistemas.membership.service.exception.NonApprovedUserException;

public interface AuthenticationService {
	public User validateUserNameAndPassword(String userName, String password) throws BlockedUserException, NonApprovedUserException, NoSuchAlgorithmException;
	public User validateUserNameAndPasswordAndApplication( String userName, String password, String uuidApplication ) throws BlockedUserException, NonApprovedUserException, NoApplicationRolesException, NoSuchAlgorithmException;
	public void notifyFailedValidation(User user);
	public void notifySuccessfulValidation(User user);
	public String generateApiKey(User user);
	public boolean checkApiKey(String apiKey);
	public boolean deleteApiKey(String apiKey);
}
