package mx.amib.sistemas.membership.controller.rest;

import mx.amib.sistemas.membership.model.User;
import mx.amib.sistemas.membership.service.AuthenticationService;
import mx.amib.sistemas.membership.service.exception.BlockedUserException;
import mx.amib.sistemas.membership.service.exception.NoApplicationRolesException;
import mx.amib.sistemas.membership.service.exception.NonApprovedUserException;
import mx.amib.sistemas.membership.service.exception.WrongPasswordAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public ResponseEntity<AuthenticateResponseWrapper> authenticate(@RequestBody AuthenticateRequestWrapper arw){
		
		ResponseEntity<AuthenticateResponseWrapper> responseEntity;
		AuthenticateResponseWrapper responseObj = new AuthenticateResponseWrapper();
		User user = null;
		
		try {
			user = authenticationService.validateUserNameAndPasswordAndApplication(arw.getUserName(), arw.getCleanPassword(), arw.getUuidApplication());
			responseObj.setValid(true);
			responseObj.setGeneratedApiKey( authenticationService.generateApiKey(user) );
			responseEntity = new ResponseEntity<AuthenticateResponseWrapper>( responseObj , HttpStatus.OK );
		} catch (BlockedUserException e) {
			responseObj.setValid(false);
			responseObj.setExceptionName(e.getClass().getSimpleName());
			responseEntity = new ResponseEntity<AuthenticateResponseWrapper>( responseObj , HttpStatus.OK );
		} catch (NonApprovedUserException e) {
			responseObj.setValid(false);
			responseObj.setExceptionName(e.getClass().getSimpleName());
			responseEntity = new ResponseEntity<AuthenticateResponseWrapper>( responseObj , HttpStatus.OK );
		} catch (NoApplicationRolesException e) {
			responseObj.setValid(false);
			responseObj.setExceptionName(e.getClass().getSimpleName());
			responseEntity = new ResponseEntity<AuthenticateResponseWrapper>( responseObj , HttpStatus.OK );
		} catch (WrongPasswordAlgorithm e) {
			responseObj.setValid(false);
			responseObj.setExceptionName(e.getClass().getSimpleName());
			responseEntity = new ResponseEntity<AuthenticateResponseWrapper>( responseObj , HttpStatus.OK );
		}
		return responseEntity;
	}
	
	@RequestMapping(value="/invalidateKey/{apiKey}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> invalidateKey(@PathVariable("apiKey") String apiKey){
		ResponseEntity<Boolean> responseEntity;
		
		responseEntity = new ResponseEntity<Boolean>( authenticationService.deleteApiKey(apiKey) , HttpStatus.OK );
		
		return responseEntity;
	}
}

class AuthenticateRequestWrapper{
	private String userName;
	private String cleanPassword;
	private String uuidApplication;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCleanPassword() {
		return cleanPassword;
	}
	public void setCleanPassword(String cleanPassword) {
		this.cleanPassword = cleanPassword;
	}
	public String getUuidApplication() {
		return uuidApplication;
	}
	public void setUuidApplication(String uuidApplication) {
		this.uuidApplication = uuidApplication;
	}

}

class AuthenticateResponseWrapper{
	private boolean valid;
	private String generatedApiKey;
	private String exceptionName;
	
	public boolean getValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getGeneratedApiKey() {
		return generatedApiKey;
	}
	public void setGeneratedApiKey(String generatedApiKey) {
		this.generatedApiKey = generatedApiKey;
	}
	public String getExceptionName() {
		return exceptionName;
	}
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
}