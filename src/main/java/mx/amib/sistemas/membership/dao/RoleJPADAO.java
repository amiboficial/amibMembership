package mx.amib.sistemas.membership.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.amib.sistemas.membership.model.Role;

@Repository(value="roleDAO")
public class RoleJPADAO implements RoleDAO {

	protected EntityManager entityManager;
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	
	public long count() {
		return entityManager.createQuery("select count(r) from Role r", Long.class).getSingleResult().longValue();
	}

	public Role get(long id) {
		return entityManager.find(Role.class, id);
	}

	public List<Role> getAll() {
		return entityManager.createQuery("select r from Role r", Role.class).getResultList();
	}

	public Role save(Role role) {
		entityManager.persist(role);
		entityManager.flush();
		return role;
	}

	public Role update(Role role) {
		entityManager.merge(role);
		entityManager.flush();
		return role;
	}

	public void delete(long id) {
		entityManager.remove(this.get(id));
	}
	
	
	
	public List<Role> getAllByIdApplication(long idApplication) {
		return entityManager.createQuery("select r from Role r where r.idApplication = :idApplication", Role.class)
				.setParameter("idApplication", idApplication)
				.getResultList();
	}
	
	public List<Role> getAllByIdUser(long idUser) {
		return entityManager.createQuery("select r from User u inner join u.roles r where u.id = :idUser", Role.class)
				.setParameter("idUser", idUser)
				.getResultList();
	}
	
	public List<Role> getAllByUserName(String userName) {
		return entityManager.createQuery("select r from User u inner join u.roles r where u.userName = :userName", Role.class)
				.setParameter("userName", userName)
				.getResultList();
	}

	public long getNextRoleNumberSequence(long idApplication) {
		return entityManager.createQuery("select max(r.numberRole) from Role r where r.idApplication = :idApplication", Long.class)
				.setParameter("idApplication", idApplication)
				.getSingleResult() + 1;
	}
	

}
