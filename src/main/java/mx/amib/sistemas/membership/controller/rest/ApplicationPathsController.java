package mx.amib.sistemas.membership.controller.rest;

import java.util.List;

import mx.amib.sistemas.external.membership.*;
import mx.amib.sistemas.membership.model.Path;
import mx.amib.sistemas.membership.model.convert.PathTransportConverter;
import mx.amib.sistemas.membership.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/application")
public class ApplicationPathsController {

	@Autowired
	private PathService pathService;
	
	@RequestMapping(value="/{idApplication}/paths/create", method = RequestMethod.GET)
	public ResponseEntity<PathTO> create(@PathVariable("idApplication") long idApplication){
		PathTO pathTO = new PathTO();
		pathTO.setIdApplication(idApplication);
		
		return new ResponseEntity<PathTO>( pathTO , HttpStatus.OK ); 
	}
	
	@RequestMapping(value="/{idApplication}/paths/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<PathTO>> getAll(@PathVariable("idApplication") long idApplication){
		List<PathTO> res = PathTransportConverter.convertToTransport(pathService.getAllByIdApplication(idApplication));
		return new ResponseEntity<List<PathTO>>(res,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{idApplication}/paths/get/{numberPath}", method = RequestMethod.GET)
	public ResponseEntity<PathTO> get(@PathVariable("idApplication") long idApplication, @PathVariable("numberPath") long numberPath){
		PathTO pathTO = PathTransportConverter.convertToTransport(pathService.get(idApplication,numberPath));
		return new ResponseEntity<PathTO>( pathTO , HttpStatus.OK );
	}
	
	@RequestMapping(value="/{idApplication}/paths/save", method = RequestMethod.POST)
	public ResponseEntity<PathTO> save(@PathVariable("idApplication") long idApplication, @RequestBody PathTO pathTO){
		Path path = new Path();
		ResponseEntity<PathTO> responseEntity = null;
		
		path = PathTransportConverter.setValuesOnEntity(path, pathTO);
		path.setIdApplication(idApplication);

		path = pathService.save(path);
		responseEntity = new ResponseEntity<PathTO>( PathTransportConverter.convertToTransport(path) ,HttpStatus.CREATED);
			
		return responseEntity;
	}
	
	@RequestMapping(value="/{idApplication}/paths/update", method = RequestMethod.POST)
	public ResponseEntity<PathTO> update(@PathVariable("idApplication") long idApplication, @RequestBody PathTO pathTO){
		Path path = pathService.get(idApplication, pathTO.getNumberPath());
		ResponseEntity<PathTO> responseEntity = null;
		
		path = PathTransportConverter.setValuesOnEntity(path, pathTO);
		path = pathService.update(path);
		
		responseEntity = new ResponseEntity<PathTO>( PathTransportConverter.convertToTransport(path) ,HttpStatus.OK);
		
		return responseEntity;
	}
	
	@RequestMapping(value="/{idApplication}/paths/delete/{numberPath}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("idApplication") long idApplication, @PathVariable("numberPath") long numberPath){
//		try{
			pathService.delete(idApplication,numberPath);
			return new ResponseEntity<Boolean>( true , HttpStatus.OK );
//		} catch (NonValidDeleteOperationException nvde) {
//			return new ResponseEntity<Boolean>( false , HttpStatus.NOT_ACCEPTABLE );
//		}
	}
}
