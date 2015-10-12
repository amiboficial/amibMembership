package mx.amib.sistemas.membership.controller.rest;

import mx.amib.sistemas.membership.service.UserService;
import mx.amib.sistemas.membership.service.exception.BlockedUserException;
import mx.amib.sistemas.membership.service.exception.NoApplicationRolesException;
import mx.amib.sistemas.membership.service.exception.NonApprovedUserException;
import mx.amib.sistemas.membership.service.exception.WrongPasswordAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membership")
public class MembershipController {
	
	private static final String UUID_MEMBERSHIP_APP = "79449d33-cdb5-4bb1-82a3-4b7c95eabc32";
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public ResponseEntity<AuthenticateResponseWrapper> authenticate(@RequestBody AuthenticateRequestWrapper arw){
		
		ResponseEntity<AuthenticateResponseWrapper> responseEntity;
		AuthenticateResponseWrapper responseObj = new AuthenticateResponseWrapper();
		boolean valid = false;
		
		try {
			valid = userService.validateUserNameAndPasswordAndApplication(arw.getUserName(), arw.getCleanPassword(), UUID_MEMBERSHIP_APP);
			responseObj.setValid(valid);
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
	
}

class AuthenticateRequestWrapper{
	private String userName;
	private String cleanPassword;
	
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

}

class AuthenticateResponseWrapper{
	private boolean valid;
	private String exceptionName;
	
	public boolean getValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getExceptionName() {
		return exceptionName;
	}
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
	
}