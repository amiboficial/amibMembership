package mx.amib.sistemas.membership.controller.rest;

import java.util.List;

import mx.amib.sistemas.external.membership.PathTO;
import mx.amib.sistemas.external.membership.RoleTO;
import mx.amib.sistemas.external.membership.UserTO;
import mx.amib.sistemas.membership.model.User;
import mx.amib.sistemas.membership.model.convert.*;
import mx.amib.sistemas.membership.service.ApplicationService;
import mx.amib.sistemas.membership.service.PathService;
import mx.amib.sistemas.membership.service.UserService;
import mx.amib.sistemas.membership.service.exception.WrongPasswordAlgorithm;
import mx.amib.sistemas.membership.controller.rest.wrapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private PathService pathService;
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ResponseEntity<UserTO> create(){
		ResponseEntity<UserTO> responseEntity = new ResponseEntity<UserTO>( new UserTO(), HttpStatus.OK );
		return responseEntity;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserTO> get(@PathVariable("id") long id){
		ResponseEntity<UserTO> responseEntity;
		UserTO userTO;
		
		userTO = UserTransportConverter.convertToTransport(userService.get(id));
		responseEntity = new ResponseEntity<UserTO>( userTO, HttpStatus.OK );
		
		return responseEntity;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<UserTO> save(@RequestBody UserTO userTO){
		ResponseEntity<UserTO> responseEntity;
		User user = new User();
		
		user = UserTransportConverter.setValuesOnEntity(user, userTO);
		try {
			userTO =  UserTransportConverter.convertToTransport(userService.save(user));
			responseEntity = new ResponseEntity<UserTO>( userTO, HttpStatus.OK );
		} catch (WrongPasswordAlgorithm e) {
			responseEntity = new ResponseEntity<UserTO>( HttpStatus.INTERNAL_SERVER_ERROR );
		}
		
		return responseEntity;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ResponseEntity<UserTO> update(@RequestBody UserTO userTO){
		ResponseEntity<UserTO> responseEntity;
		User user;
		
		user = userService.get(userTO.getId());
		user = UserTransportConverter.setValuesOnEntity(user, userTO); 
		
		userTO = UserTransportConverter.convertToTransport( userService.update(user) );
		responseEntity = new ResponseEntity<UserTO>( userTO, HttpStatus.OK );
		
		return responseEntity;
	}
	
	@RequestMapping(value="/{id}/password/update", method = RequestMethod.POST)
	public ResponseEntity<Boolean> updatePassword(@PathVariable("id") long id, @RequestBody UpdatePasswordRequestWrapper reqwrp){
		ResponseEntity<Boolean> responseEntity;
		
		try {
			userService.updatePassword(id, reqwrp.getPassword());
			responseEntity = new ResponseEntity<Boolean>( true, HttpStatus.OK );
		} catch (WrongPasswordAlgorithm e) {
			responseEntity = new ResponseEntity<Boolean>( HttpStatus.INTERNAL_SERVER_ERROR );
		}
		
		return responseEntity;
	}
	
	@RequestMapping(value="/{id}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") long id){
		ResponseEntity<Boolean> re = null;
//		try{
			userService.delete(id);
			re = new ResponseEntity<Boolean>( true, HttpStatus.OK );
//		} catch(Exception e){
//			re = new ResponseEntity<Boolean>( false, HttpStatus.INTERNAL_SERVER_ERROR );
//		}
		return re;
	}
	
	@RequestMapping(value="/getByUserName/{userName}", method = RequestMethod.GET)
	public ResponseEntity<UserTO> getByUserName(@PathVariable("userName") String userName){
		ResponseEntity<UserTO> re = null;
		UserTO userTO;
		
		userTO = UserTransportConverter.convertToTransport( userService.getByUserName(userName) );
		re = new ResponseEntity<UserTO>(userTO, HttpStatus.OK);
		
		return re;
	}
	
	@RequestMapping(value="/findAll", method = RequestMethod.POST)
	public ResponseEntity<FindAllResponseWrapper> findAll(@RequestBody FindAllRequestWrapper rw){
		ResponseEntity<FindAllResponseWrapper> re = null;
		FindAllResponseWrapper findAllResponseWrapper = new FindAllResponseWrapper();
		List<UserTO> listUserTO;
		long countTotal = 0;
		
		countTotal = userService.countFindAll();
		listUserTO = UserTransportConverter.convertToTransport( userService.findAll(rw.getMax(), rw.getOffset(), rw.getSort(), rw.getOrder()) );
		findAllResponseWrapper.setCount(countTotal);
		findAllResponseWrapper.setList(listUserTO);
		re = new ResponseEntity<FindAllResponseWrapper>(findAllResponseWrapper, HttpStatus.OK);
		
		return re;
	}
	
	@RequestMapping(value="/findAllByUserNameLike", method = RequestMethod.POST)
	public ResponseEntity<FindAllResponseWrapper> findAllByUserNameLike(@RequestBody FindAllByUserNameLikeRequestWrapper rw){
		ResponseEntity<FindAllResponseWrapper> re = null;
		FindAllResponseWrapper findAllResponseWrapper = new FindAllResponseWrapper();
		List<UserTO> listUserTO;
		long countTotal = 0;
		
		countTotal = userService.countFindAllByUserNameLike(rw.getUserName());
		listUserTO = UserTransportConverter.convertToTransport( userService.findAllByUserNameLike(rw.getMax(), rw.getOffset(), rw.getSort(), rw.getOrder(), rw.getUserName()) );
		findAllResponseWrapper.setCount(countTotal);
		findAllResponseWrapper.setList(listUserTO);
		re = new ResponseEntity<FindAllResponseWrapper>(findAllResponseWrapper, HttpStatus.OK);
		
		return re;
	}
	
	@RequestMapping(value="/{id}/roles/{idAppplication}/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<RoleTO>> getRoles(@PathVariable("id") long id, @PathVariable("idApplication") long idApplication){
		ResponseEntity<List<RoleTO>> re = null;
		List<RoleTO> list = null;
		
		list = RoleTransportConverter.convertToTranport(userService.getRoles(id, idApplication));
		re = new ResponseEntity<List<RoleTO>>(list,HttpStatus.OK);
		
		return re;
	}
	
	@RequestMapping(value="/{id}/paths/{idAppplication}/restricted/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<PathTO>> getRestrictedPaths(@PathVariable("id") long id, @PathVariable("idApplication") long idApplication){
		ResponseEntity<List<PathTO>> re = null;
		List<PathTO> list = null;
		
		list = PathTransportConverter.convertToTransport(userService.getRestrictedPaths(id, idApplication));
		re = new ResponseEntity<List<PathTO>>(list,HttpStatus.OK);
		
		return re;
	}
}
