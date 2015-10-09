package mx.amib.sistemas.membership.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.amib.sistemas.membership.model.Path;

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

	public Path get(long id) {
		return entityManager.find(Path.class, id);
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

	public void delete(long id) {
		entityManager.remove(this.get(id));
	}

}
