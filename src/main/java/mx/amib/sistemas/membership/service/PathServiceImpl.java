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

}
