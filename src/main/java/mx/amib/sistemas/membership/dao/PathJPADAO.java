package mx.amib.sistemas.membership.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.amib.sistemas.membership.model.Path;
import mx.amib.sistemas.membership.model.PathId;

@Repository(value="pathDAO")
public class PathJPADAO implements PathDAO {

	protected EntityManager entityManager;
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	
	public long count() {
		return entityManager.createQuery("select count(p) from Path p", Long.class).getSingleResult().longValue();
	}

	public Path get(long idApplication, long numberPath) {
		PathId pathId = new PathId();
		pathId.setIdApplication(idApplication);
		pathId.setNumberPath(numberPath);
		return entityManager.find(Path.class, pathId);
	}

	public List<Path> getAll() {
		return entityManager.createQuery("select p from Path p", Path.class).getResultList();
	}

	public Path save(Path path) {
		entityManager.persist(path);
		entityManager.flush();
		return path;
	}

	public Path update(Path path) {
		entityManager.merge(path);
		entityManager.flush();
		return path;
	}

	public void delete(long idApplication, long numberPath) {
		entityManager.remove(this.get(idApplication,numberPath));
	}

	public List<Path> getAllByIdApplication(long idApplication) {
		return entityManager.createQuery("select p from Path p where p.idApplication = :idApplication", Path.class)
				.setParameter("idApplication", idApplication)
				.getResultList();
	}
	
	public long getNextNumberSeq(long idApplication){
		Long nextNumberSeq = entityManager.createQuery("select max(p.numberPath) from Path p where p.idApplication = :idApplication", Long.class)
								.setParameter("idApplication", idApplication)
								.getSingleResult();
		
		if(nextNumberSeq == null)
			nextNumberSeq = 0L;
		
		return nextNumberSeq + 1;
	}

	
}
