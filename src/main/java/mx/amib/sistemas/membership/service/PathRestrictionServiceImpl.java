package mx.amib.sistemas.membership.service;

import java.util.*;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.amib.sistemas.membership.dao.PathDAO;
import mx.amib.sistemas.membership.dao.PathRestrictionDAO;
import mx.amib.sistemas.membership.dao.RoleDAO;
import mx.amib.sistemas.membership.model.*;

@Scope("singleton")
@Service("pathRestrictionService")
public class PathRestrictionServiceImpl implements PathRestrictionService {

	@Autowired
	private PathRestrictionDAO pathRestrictionDAO;
	@Autowired
	private PathDAO pathDAO;
	@Autowired
	private RoleDAO roleDAO;
	
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
	@Transactional
	public List<Path> getAllByNumberRole(long idApplication, long numberRole) {		
		List<Path> restrictedPaths = new ArrayList<Path>();
		
		restrictedPaths = pathRestrictionDAO.getRestrictedPathsByIdApplicationAndNumberPath(idApplication, numberRole);
		
		return restrictedPaths;
	}

	@Override
	@Transactional
	public List<Role> getAllByNumberPath(long idApplication, long numberPath) {
		List<Role> restrictedRoles = new ArrayList<Role>();
		
		restrictedRoles = pathRestrictionDAO.getRestrictedRolesByIdApplicationAndNumberPath(idApplication, numberPath);
		
		return restrictedRoles;
	}

	@Transactional
	public void saveChangesByNumberRole(long idApplication, long numberRole, Set<Long> restrictedNumbersPath) {
		//borra todas las restricciones
		pathRestrictionDAO.deleteAllByNumberRole(idApplication, numberRole);
		//crea nuevas restricciones con base en la lista de numeros de paths indicada 
		for(Long numberPath : restrictedNumbersPath){
			PathRestriction pr = new PathRestriction();
			pr.setIdApplication(idApplication);
			pr.setNumberRole(numberRole);
			pr.setNumberPath(numberPath);

			pathRestrictionDAO.save(pr);
		}
	}

	@Transactional
	public void saveChangesByNumberPath(long idApplication, long numberPath, Set<Long> restrictedNumbersRole) {
		// TODO Auto-generated method stub
		//borra todas las restricciones
		pathRestrictionDAO.deleteAllByNumberPath(idApplication, numberPath);
		//crea nuevas restricciones con base en la lista de numeros de paths indicada 
		for(Long numberRole : restrictedNumbersRole){
			PathRestriction pr = new PathRestriction();
			pr.setIdApplication(idApplication);
			pr.setNumberRole(numberRole);
			pr.setNumberPath(numberPath);

			pathRestrictionDAO.save(pr);
		}
	}

}
