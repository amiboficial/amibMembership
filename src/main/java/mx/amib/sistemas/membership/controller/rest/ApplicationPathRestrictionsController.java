package mx.amib.sistemas.membership.controller.rest;

import java.util.List;
import java.util.Set;

import mx.amib.sistemas.external.membership.PathTO;
import mx.amib.sistemas.external.membership.RoleTO;
import mx.amib.sistemas.membership.service.PathRestrictionService;
import mx.amib.sistemas.membership.model.convert.*;

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
public class ApplicationPathRestrictionsController {
	
	@Autowired
	private PathRestrictionService pathRestrictionService;
	
	@RequestMapping(value="/{idApplication}/roles/{numRol}/pathRestrictions/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<PathTO>> getAllByNumberRole(@PathVariable("idApplication") long idApplication, @PathVariable("numRol") long numRol){
		ResponseEntity<List<PathTO>> responseEntity;
		List<PathTO> restrictedPathsTO;
		
		restrictedPathsTO = PathTransportConverter.convertToTransport(pathRestrictionService.getAllByNumberRole(idApplication, numRol));
		responseEntity = new ResponseEntity<List<PathTO>>(restrictedPathsTO, HttpStatus.OK);
		
		return responseEntity;
	}
	
	@RequestMapping(value="/{idApplication}/paths/{numPath}/pathRestrictions/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<RoleTO>> getAllByNumberPath(@PathVariable("idApplication") long idApplication, @PathVariable("numPath") long numPath){
		ResponseEntity<List<RoleTO>> responseEntity;
		List<RoleTO> restrictedPathsTO;
		
		//restrictedPathsTO = RoleTransportConverter.convertToTransport(pathRestrictionService.getAllByNumberPath(idApplication, numPath));
		restrictedPathsTO = RoleTransportConverter.convertToTranport( pathRestrictionService.getAllByNumberPath(idApplication, numPath) );
		responseEntity = new ResponseEntity<List<RoleTO>>(restrictedPathsTO, HttpStatus.OK);
		
		return responseEntity;
	}
	
	@RequestMapping(value="/{idApplication}/pathRestrictions/save/{numRol}/{numPath}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> save(@PathVariable("idApplication") long idApplication, @PathVariable("numRole") long numRole, @PathVariable("numPath") long numPath){
		ResponseEntity<Boolean> responseEntity;
		
		pathRestrictionService.save(idApplication, numRole, numPath);
		responseEntity = new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@RequestMapping(value="/{idApplication}/pathRestrictions/saveChangesByNumberRole/{numRole}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> saveChangesByNumberRole(@PathVariable("idApplication") long idApplication, 
			@PathVariable("numRole") long numberRole, @RequestBody Set<Long> restrictedNumbersPath){
		ResponseEntity<Boolean> responseEntity;
		
		pathRestrictionService.saveChangesByNumberRole(idApplication, numberRole, restrictedNumbersPath);
		responseEntity = new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@RequestMapping(value="/{idApplication}/pathRestrictions/saveChangesByNumberPath/{numPath}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> saveChangesByNumberPath(@PathVariable("idApplication") long idApplication, 
			@PathVariable("numPath") long numberPath, @RequestBody Set<Long> restrictedNumbersRole){
		ResponseEntity<Boolean> responseEntity;
		
		pathRestrictionService.saveChangesByNumberPath(idApplication, numberPath, restrictedNumbersRole);
		responseEntity = new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@RequestMapping(value="/{idApplication}/pathRestrictions/delete/{numRole}/{numPath}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("idAppliation") long idApplication, @PathVariable("numRole") long numRole, @PathVariable("numPath") long numPath){
		ResponseEntity<Boolean> responseEntity;
		
		pathRestrictionService.delete(idApplication, numRole, numPath);
		responseEntity = new ResponseEntity<Boolean>(true,HttpStatus.OK);
		
		return responseEntity;
	}
}
