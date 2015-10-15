package mx.amib.sistemas.membership.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.amib.sistemas.membership.dao.PathRestrictionDAO;
import mx.amib.sistemas.membership.model.*;

@Scope("singleton")
@Service("pathRestrictionService")
public class PathRestrictionServiceImpl implements PathRestrictionService {

	@Autowired
	private PathRestrictionDAO pathRestrictionDAO;
	
	@Override
	public void save(long idApplication, long numRole, long numPath) {
		PathRestriction pathRestriction = new PathRestriction();
		pathRestriction.setIdApplication(idApplication);
		pathRestriction.setNumberRole(numRole);
		pathRestriction.setNumberPath(numPath);
		pathRestrictionDAO.save(pathRestriction);
	}

	@Override
	public void delete(long idApplication, long numRole, long numPath) {
		pathRestrictionDAO.delete(idApplication, numRole, numPath);
	}

	@Override
	public List<Path> getAllByNumberRole(long idApplication, long numberRole) {
		List<Path> paths = new ArrayList<Path>();
		List<PathRestriction> restrictions = null;
		
		restrictions = pathRestrictionDAO.getAllByIdApplicationAndNumberRole(idApplication, numberRole);
		for( PathRestriction x : restrictions ){
			paths.add( x.getPath() );			
		}
		
		return paths;
	}

	@Override
	public List<Path> getAllByNumberPath(long idApplication, long numberPath) {
		List<Path> paths = new ArrayList<Path>();
		List<PathRestriction> restrictions = null;
		
		restrictions = pathRestrictionDAO.getAllByIdApplicationAndNumberRole(idApplication, numberPath);
		for( PathRestriction x : restrictions ){
			paths.add( x.getPath() );			
		}
		
		return paths;
	}

}
