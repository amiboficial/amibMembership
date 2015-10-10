package mx.amib.sistemas.membership.dao;

import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import mx.amib.sistemas.membership.model.Application;

@Repository(value="applicationDAO")
public class ApplicationJPADAO implements ApplicationDAO {

	protected EntityManager entityManager;
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	
	public long count() {
		return entityManager.createQuery("select count(a) from Application a", Long.class).getSingleResult().longValue();
	}

	public Application get(long id) {
		return entityManager.find(Application.class, id);
	}

	public List<Application> getAll() {
		return entityManager.createQuery("select a from Application a", Application.class).getResultList();
	}

	public Application save(Application application) {
		entityManager.persist(application);
		entityManager.flush();
		return application;
	}

	public Application update(Application application) {
		entityManager.merge(application);
		entityManager.flush();
		return application;
	}
	
	public void delete(long id) {
		entityManager.remove(this.get(id));
	}

	public Application getByUuid(String uuid) {
		return entityManager.createQuery("select a from Application a where a.uuid = :uuid", Application.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
	}
	
	public long countGetByUuid(String uuid) {
		return entityManager.createQuery("select count(a) from Application a where a.uuid = :uuid", Long.class)
				.getSingleResult()
				.longValue();
	}

}
