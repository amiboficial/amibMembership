package mx.amib.sistemas.membership.controller.rest;

import java.util.List;

import mx.amib.sistemas.external.membership.ApplicationTO;
import mx.amib.sistemas.membership.model.Application;
import mx.amib.sistemas.membership.model.convert.ApplicationTransportConverter;
import mx.amib.sistemas.membership.service.ApplicationService;
import mx.amib.sistemas.membership.service.exception.*;

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
public class ApplicationRestfulController {

	@Autowired
	private ApplicationService applicationService;
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ResponseEntity<ApplicationTO> create(){
		return new ResponseEntity<ApplicationTO>( new ApplicationTO() , HttpStatus.OK );
	}
	
	@RequestMapping(value="/count", method = RequestMethod.GET)
	public ResponseEntity<Long> count(){
		return new ResponseEntity<Long>( applicationService.count() , HttpStatus.OK );
	}
	
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<ApplicationTO>> getAll(){
		List<ApplicationTO> ares = ApplicationTransportConverter.convertToTransport(applicationService.getAll());
		return new ResponseEntity<List<ApplicationTO>>( ares , HttpStatus.OK );
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<ApplicationTO> save(@RequestBody ApplicationTO applicationTO){
		Application entToSave = ApplicationTransportConverter.setValuesOnEntity( applicationService.get(applicationTO.getId()), applicationTO );
		try {
			entToSave = applicationService.save(entToSave);
		} catch (UuidNonUniqueException e) {
			return new ResponseEntity<ApplicationTO>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<ApplicationTO>( ApplicationTransportConverter.convertToTransport(entToSave) , HttpStatus.CREATED );
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ResponseEntity<ApplicationTO> update(@RequestBody ApplicationTO applicationTO){
		Application entToUpd = ApplicationTransportConverter.setValuesOnEntity( applicationService.get(applicationTO.getId()), applicationTO );
		try {
			entToUpd = applicationService.update(entToUpd);
		} catch (UuidNonUniqueException e) {
			return new ResponseEntity<ApplicationTO>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<ApplicationTO>( ApplicationTransportConverter.convertToTransport(entToUpd) , HttpStatus.OK );
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		try{
			applicationService.delete(id);
			return new ResponseEntity<Boolean>( true , HttpStatus.OK );
		} catch (NonValidDeleteOperationException nvde) {
			return new ResponseEntity<Boolean>( false , HttpStatus.NOT_ACCEPTABLE );
		}
	}
}
