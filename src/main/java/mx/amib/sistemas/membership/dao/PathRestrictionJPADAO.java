package mx.amib.sistemas.membership.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.amib.sistemas.membership.model.PathRestriction;
import mx.amib.sistemas.membership.model.PathRestrictionId;

@Repository(value="pathRestrictionDAO")
public class PathRestrictionJPADAO implements PathRestrictionDAO {

	protected EntityManager entityManager;
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	
	public long count() {
		return entityManager.createQuery("select count(pr) from PathRestriction pr", Long.class).getSingleResult().longValue();
	}

	public List<PathRestriction> getAll() {
		return entityManager.createQuery("select pr from PathRestriction pr", PathRestriction.class).getResultList();
	}

	public PathRestriction get(long idApplication, long numberRole,
			long numberPath) {
		PathRestrictionId prId = new PathRestrictionId();
		prId.setIdApplication(idApplication);
		prId.setNumberRole(numberRole);
		prId.setNumberPath(numberPath);
		return entityManager.find(PathRestriction.class, prId);
	}
	
	public PathRestriction save(PathRestriction pathRestriction) {
		entityManager.persist(pathRestriction);
		entityManager.flush();
		return pathRestriction;
	}
	
	public PathRestriction update(PathRestriction pathRestriction) {
		entityManager.merge(pathRestriction);
		entityManager.flush();
		return pathRestriction;
	}

	public void delete(long idApplication, long numberRole, long numberPath) {
		entityManager.remove(this.get(idApplication, numberRole, numberPath));
	}

	public List<PathRestriction> getAllByIdApplication(long idApplication) {
		return entityManager.createQuery("select pr from PathRestriction pr where pr.idApplication = :idApplication", PathRestriction.class)
				.setParameter("idApplication", idApplication)
				.getResultList();
	}
	
	public List<PathRestriction> getAllByIdApplicationAndNumberRole(
			long idApplication, long numberRole) {
		return entityManager.createQuery("select pr from PathRestriction pr where pr.idApplication = :idApplication and pr.numberRole = :numberRole", PathRestriction.class)
				.setParameter("idApplication", idApplication)
				.setParameter("numberRole", numberRole)
				.getResultList();
	}

	@Override
	public List<PathRestriction> getAllByIdApplicationAndNumberPath(
			long idApplication, long numberPath) {
		return entityManager.createQuery("select pr from PathRestriction pr where pr.idApplication = :idApplication and pr.numberPath = :numberPath", PathRestriction.class)
				.setParameter("idApplication", idApplication)
				.setParameter("numberPath", numberPath)
				.getResultList();
	}

	

}
