package mx.amib.sistemas.membership.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import mx.amib.sistemas.membership.model.Path;
import mx.amib.sistemas.membership.model.PathRestriction;
import mx.amib.sistemas.membership.model.PathRestrictionId;
import mx.amib.sistemas.membership.model.Role;

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

	public List<Path> getRestrictedPathsByIdApplicationAndNumberPath(long idApplication, long numberRole){
		return entityManager.createQuery("select p from PathRestriction pr inner join pr.path p where pr.idApplication = :idApplication and pr.numberRole = :numberRole", Path.class)
				.setParameter("idApplication", idApplication)
				.setParameter("numberRole", numberRole)
				.getResultList();
	}
	
	public List<PathRestriction> getAllByIdApplicationAndNumberPath(
			long idApplication, long numberPath) {
		return entityManager.createQuery("select pr from PathRestriction pr where pr.idApplication = :idApplication and pr.numberPath = :numberPath", PathRestriction.class)
				.setParameter("idApplication", idApplication)
				.setParameter("numberPath", numberPath)
				.getResultList();
	}
	
	public List<Role> getRestrictedRolesByIdApplicationAndNumberPath(long idApplication, long numberPath) {
		return entityManager.createQuery("select r from PathRestriction pr inner join pr.role r where pr.idApplication = :idApplication and pr.numberPath = :numberPath", Role.class)
				.setParameter("idApplication", idApplication)
				.setParameter("numberPath", numberPath)
				.getResultList();
	}

	public void deleteAllByNumberRole(long idApplication, long numberRole) {
		Query q = entityManager.createQuery("delete from PathRestriction pr where pr.idApplication = :idApplication and pr.numberRole = :numberRole");
		q.setParameter("idApplication", idApplication).setParameter("numberRole", numberRole);
		q.executeUpdate();
	}

	public void deleteAllByNumberPath(long idApplication, long numberPath) {
		Query q = entityManager.createQuery("delete from PathRestriction pr where pr.idApplication = :idApplication and pr.numberPath = :numberPath");
		q.setParameter("idApplication", idApplication).setParameter("numberPath", numberPath);
		q.executeUpdate();
	}

}
