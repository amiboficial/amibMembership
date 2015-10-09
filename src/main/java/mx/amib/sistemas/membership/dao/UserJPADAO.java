package mx.amib.sistemas.membership.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.amib.sistemas.membership.model.User;

@Repository(value="userDAO")
public class UserJPADAO implements UserDAO {

	protected EntityManager entityManager;
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	
	public long count() {
		return entityManager.createQuery("select count(u) from User u", Long.class).getSingleResult().longValue();
	}

	public User get(long id) {
		return entityManager.find(User.class, id);
	}

	public List<User> getAll() {
		return entityManager.createQuery("select u from User u", User.class).getResultList();
	}

	public User save(User user) {
		entityManager.persist(user);
		entityManager.flush();
		return user;
	}

	public User update(User user) {
		entityManager.merge(user);
		entityManager.flush();
		return user;
	}

	public void delete(long id) {
		entityManager.remove(this.get(id));
	}

}
