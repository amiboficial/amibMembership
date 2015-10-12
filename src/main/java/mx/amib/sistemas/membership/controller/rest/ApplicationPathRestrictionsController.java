package mx.amib.sistemas.membership.controller.rest;

import mx.amib.sistemas.external.membership.PathTO;
import mx.amib.sistemas.membership.service.PathService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class ApplicationPathRestrictionsController {
	
	@Autowired
	private PathService pathService;
	
	@RequestMapping(value="/{idApplication}/roles/{numRol}/pathRestrictions/getAll", method = RequestMethod.GET)
	public void getAllByNumberRole(@PathVariable("idApplication") long idApplication, @PathVariable("numRol") long numRol){
		
	}
	
	@RequestMapping(value="/{idApplication}/roles/{numPath}/pathRestrictions/getAll", method = RequestMethod.GET)
	public void getAllByNumberPath(@PathVariable("idApplication") long idApplication, @PathVariable("numRol") long numRol){
		
	}
	
	@RequestMapping(value="/{idApplication}/pathRestrictions/save/{numRol}/{numPath}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> save(){
		
	}
	
	@RequestMapping(value="/{idApplication}/pathRestrictions/delete/{numRol}/{numPath}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> delete(){
		
	}
}
