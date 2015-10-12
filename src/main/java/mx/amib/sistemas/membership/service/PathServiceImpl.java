package mx.amib.sistemas.membership.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.membership.dao.*;
import mx.amib.sistemas.membership.model.*;

@Scope("singleton")
@Service("pathService")
public class PathServiceImpl implements PathService {

	@Autowired
	private PathDAO pathDAO;
	@Autowired
	private PathRestrictionDAO pathRestricionDAO;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private ApplicationDAO applicationDAO;
	
	public long count() {
		return pathDAO.count();
	}

	public List<Path> getAll() {
		// TODO Auto-generated method stub
		return pathDAO.getAll();
	}

	public Path get(long idApplication, long numberPath) {
		return pathDAO.get(idApplication, numberPath);
	}

	@Transactional
	public Path save(Path path) {
		return pathDAO.save(path);
	}

	@Transactional
	public Path update(Path path) {
		return pathDAO.update(path);
	}

	@Transactional
	public void delete(long idApplication, long numberPath) {
		pathDAO.delete(idApplication, numberPath);
	}

	public List<Path> getAllByIdApplication(long idApplication){
		return pathDAO.getAllByIdApplication(idApplication);
	}
	
	public List<Path> getUserInApplicationRestrictedPaths(long idUser,
			String uuidApplication) {
		
		List<Role> userRolesInApplication;
		List<PathRestriction> pathRestrictionsInApplication;
		Map<PathRestrictionId,Integer> pathRestrictionCounting = new HashMap<PathRestrictionId,Integer>();
		int totalPathRestrictionsInApplication = 0;
		long idApplication = 0;
		List<Path> effectivePathRestrictions = new ArrayList<Path>();
		
		idApplication = applicationDAO.getByUuid(uuidApplication).getId();
		pathRestrictionsInApplication = pathRestricionDAO.getAllByIdApplication(idApplication);
		totalPathRestrictionsInApplication = pathRestrictionsInApplication.size();
		
		//setea el conteo de cada una de las restricciones 0
		for(PathRestriction x : pathRestrictionsInApplication){
			PathRestrictionId prid = new PathRestrictionId(x.getIdApplication(),x.getNumberRole(),x.getNumberPath());
			pathRestrictionCounting.put(prid, 0);
		}
		
		//obtiene los roles de usuario en la aplicación
		userRolesInApplication = roleDAO.getAllByIdUserAndIdApplication(idUser, idApplication);
		
		//cuenta cada una de las restricciones del usuario contenidas por cada rol asignado
		for(Role r : userRolesInApplication){
			List<PathRestriction> pathRestrictionsInRol = pathRestricionDAO.getAllByIdApplicationAndNumberRole(idApplication, r.getNumberRole());
			for(PathRestriction x : pathRestrictionsInRol){
				PathRestrictionId prid = new PathRestrictionId(x.getIdApplication(),x.getNumberRole(),x.getNumberPath());
				pathRestrictionCounting.put( prid, pathRestrictionCounting.get(prid) + 1 );
				
				//si el conteo alcanzo el "numero maximo"
				//es decir, todos los roles de usuario restrigen el mismo path
				//un solo rol que no contenga dicha restricción, no lo incluría en la cuenta
				if(pathRestrictionCounting.get(prid) == totalPathRestrictionsInApplication){
					effectivePathRestrictions.add(x.getPath());
				}
				
			}
		}
		
		return effectivePathRestrictions;
	}

}
