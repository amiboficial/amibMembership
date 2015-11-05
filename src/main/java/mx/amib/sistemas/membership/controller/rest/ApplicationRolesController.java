package mx.amib.sistemas.membership.controller.rest;

import java.util.List;

import mx.amib.sistemas.external.membership.RoleTO;
import mx.amib.sistemas.membership.model.Role;
import mx.amib.sistemas.membership.model.convert.RoleTransportConverter;
import mx.amib.sistemas.membership.service.RoleService;
import mx.amib.sistemas.membership.service.exception.NonValidDeleteOperationException;
import mx.amib.sistemas.membership.service.exception.NonValidSaveOperationException;
import mx.amib.sistemas.membership.service.exception.NonValidUpdateOperationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class ApplicationRolesController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/{idApplication}/roles/create", method = RequestMethod.GET)
	public ResponseEntity<RoleTO> create(@PathVariable("idApplication") long idApplication){
		RoleTO roleTO = new RoleTO();
		roleTO.setIdApplication(idApplication);
		return new ResponseEntity<RoleTO>( roleTO , HttpStatus.CREATED ); 
	}
	
	@RequestMapping(value="/{idApplication}/roles/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<RoleTO>> getAll(@PathVariable("idApplication") long idApplication){
		List<RoleTO> res = RoleTransportConverter.convertToTranport(roleService.getAllByIdApplication(idApplication));
		return new ResponseEntity<List<RoleTO>>( res , HttpStatus.OK );
	}
	
	@RequestMapping(value="/{idApplication}/roles/get/{numberRole}", method = RequestMethod.GET)
	public ResponseEntity<RoleTO> get(@PathVariable("idApplication") long idApplication, @PathVariable("numberRole") long numberRole){
		RoleTO roleTO = RoleTransportConverter.convertToTransport(roleService.get(idApplication,numberRole));
		return new ResponseEntity<RoleTO>( roleTO , HttpStatus.OK );
	}
	
	@RequestMapping(value="/{idApplication}/roles/save", method = RequestMethod.POST)
	public ResponseEntity<RoleTO> save(@PathVariable("idApplication") long idApplication, @RequestBody RoleTO roleTO){
		Role role = new Role();
		ResponseEntity<RoleTO> responseEntity = null;
		
		role = RoleTransportConverter.setValuesOnEntity(role, roleTO);
		role.setIdApplication(idApplication);
		try{
			role = roleService.save(role);
			responseEntity = new ResponseEntity<RoleTO>( RoleTransportConverter.convertToTransport(role) , HttpStatus.CREATED );
		} catch (NonValidSaveOperationException nvse) {
			responseEntity = new ResponseEntity<RoleTO>( HttpStatus.NOT_ACCEPTABLE );
		}
		return responseEntity;
	}
	
	@RequestMapping(value="/{idApplication}/roles/update", method = RequestMethod.POST)
	public ResponseEntity<RoleTO> update(@PathVariable("idApplication") long idApplication, @RequestBody RoleTO roleTO){
		Role role = roleService.get(idApplication, roleTO.getNumberRole());
		ResponseEntity<RoleTO> responseEntity = null;
		
		role = RoleTransportConverter.setValuesOnEntity(role, roleTO);
		try{
			role = roleService.update(role);
			responseEntity = new ResponseEntity<RoleTO>( RoleTransportConverter.convertToTransport(role) , HttpStatus.OK );
		} catch (NonValidUpdateOperationException nvse) {
			responseEntity = new ResponseEntity<RoleTO>( HttpStatus.NOT_ACCEPTABLE );
		}
		return responseEntity;
	}
	@RequestMapping(value="/{idApplication}/roles/delete/{numberRole}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("idApplication") long idApplication, @PathVariable("numberRole") long numberRole){
		try{
			roleService.delete(idApplication,numberRole);
			return new ResponseEntity<Boolean>( true , HttpStatus.OK );
		} catch (NonValidDeleteOperationException nvde) {
			return new ResponseEntity<Boolean>( false , HttpStatus.NOT_ACCEPTABLE );
		}
	}
}
