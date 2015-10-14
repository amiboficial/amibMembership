package mx.amib.sistemas.membership.model.convert;

import java.util.ArrayList;
import java.util.List;

import mx.amib.sistemas.external.membership.UserTO;
import mx.amib.sistemas.membership.model.User;

public class UserTransportConverter {
	public static List<UserTO> convertToTransport(List<User> objList){
		List<UserTO> tobjList = new ArrayList<UserTO>();
		for( User x : objList ){
			tobjList.add( UserTransportConverter.convertToTransport(x) );
		}
		return tobjList;
	}
	public static UserTO convertToTransport(User obj){
		UserTO tobj = new UserTO();
		
		tobj.setId(obj.getId());
		tobj.setUuid(obj.getUuid());

		tobj.setUserName(obj.getUserName());
		tobj.setUserNameLowercase(obj.getUserNameLowercase());
		tobj.setLastActivity(obj.getLastActivity());
		tobj.setPassword(obj.getPassword());
		tobj.setPasswordFormat(obj.getPasswordFormat());
		tobj.setPasswordSalt(obj.getPasswordSalt());

		tobj.setEmail(obj.getEmail());
		tobj.setEmailLowercase(obj.getEmailLowercase());

		tobj.setPasswordQuestion(obj.getPasswordQuestion());
		tobj.setPasswordAnswer(obj.getPasswordAnswer());

		tobj.setApproved(obj.isApproved());
		tobj.setLastLogin(obj.getLastLogin());
		tobj.setLastPasswordChange(obj.getLastPasswordChange());

		tobj.setLockedOut(obj.isLockedOut());
		tobj.setLastLockedOut(obj.getLastLockedOut());

		tobj.setFailedAttempts(obj.getFailedAttempts());
		tobj.setFailedAnswerAttempts(obj.getFailedAnswerAttempts());
		
		return tobj;
	}
	
	public static User setValuesOnEntity(User obj, UserTO tobj){
		obj.setUserName(tobj.getUserName());
		obj.setUserNameLowercase(tobj.getUserNameLowercase());
		obj.setLastActivity(tobj.getLastActivity());

		obj.setEmail(tobj.getEmail());
		obj.setEmailLowercase(tobj.getEmailLowercase());

		obj.setPasswordQuestion(tobj.getPasswordQuestion());
		obj.setPasswordAnswer(tobj.getPasswordAnswer());

		obj.setApproved(tobj.isApproved());
		obj.setLastLogin(tobj.getLastLogin());
		obj.setLastPasswordChange(tobj.getLastPasswordChange());

		obj.setLockedOut(tobj.isLockedOut());
		obj.setLastLockedOut(tobj.getLastLockedOut());

		obj.setFailedAttempts(tobj.getFailedAttempts());
		obj.setFailedAnswerAttempts(tobj.getFailedAnswerAttempts());
		return obj;
	}
}
