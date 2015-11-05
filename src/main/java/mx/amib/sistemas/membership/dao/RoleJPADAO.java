package mx.amib.sistemas.membership.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.amib.sistemas.membership.model.Role;
import mx.amib.sistemas.membership.model.RoleId;

@Repository(value="roleDAO")
public class RoleJPADAO implements RoleDAO {

	private static final String COUNT_JPQL = "select count(r) from Role r";
	private static final String GET_ALL_JPQL = "select r from Role r";
	private static final String GET_BY_IDAPPLICATION_AND_NUMBERROLE_JPQL = 
			"select r from Role r where r.idApplication = :idApplication and r.numberRole = :numberRole";
	private static final String GET_ALL_BY_IDAPPLICATION_JPQL = 
			"select r from Role r where r.idApplication = :idApplication";
	private static final String GET_ALL_BY_IDUSER_JPQL = 
			"select r from User u inner join u.roles r where u.id = :idUser";
	private static final String GET_ALL_BY_IDUSER_AND_UUIDAPPLICATION_JPQL = 
			"select r from User u inner join u.roles r where u.id = :idUser and r.application.uuid = :uuidApplication";
	private static final String GET_ALL_BY_IDUSER_AND_IDAPPLICATION_JPQL =
			"select r from User u inner join u.roles r where u.id = :idUser and r.application.id = :idApplication";
	private static final String GET_ALL_BY_USER_NAME_JQPL = 
			"select r from User u inner join u.roles r where u.userName = :userName";
	private static final String GET_NEXT_ROLE_NUMBER_SEQUENCE_JQPL = 
			"select max(r.numberRole) from Role r where r.idApplication = :idApplication";
	
	protected EntityManager entityManager;
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	
	public long count() {
		return entityManager.createQuery(COUNT_JPQL, Long.class).getSingleResult().longValue();
	}

	public Role get(long idApplication, long numberRole) {
		RoleId roleId = new RoleId();
		roleId.setIdApplication(idApplication);
		roleId.setNumberRole(numberRole);
		return entityManager.find(Role.class, roleId);
	}

	public List<Role> getAll() {
		return entityManager.createQuery(GET_ALL_JPQL, Role.class).getResultList();
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

	public void delete(long idApplication, long numberRole) {
		entityManager.remove(this.get(idApplication,numberRole));
	}
	
	public Role getByIdApplicationAndNumberRole(long idApplication,
			long numberRole) {
		return entityManager.createQuery(GET_BY_IDAPPLICATION_AND_NUMBERROLE_JPQL, Role.class)
				.setParameter("idApplication", idApplication).setParameter("numberRole", numberRole)
				.getSingleResult();
	}
	
	public List<Role> getAllByIdApplication(long idApplication) {
		return entityManager.createQuery(GET_ALL_BY_IDAPPLICATION_JPQL, Role.class)
				.setParameter("idApplication", idApplication)
				.getResultList();
	}
	
	public List<Role> getAllByIdUser(long idUser) {
		return entityManager.createQuery(GET_ALL_BY_IDUSER_JPQL, Role.class)
				.setParameter("idUser", idUser)
				.getResultList();
	}
	
	public List<Role> getAllByIdUserAndUuidApplication(long idUser,
			String uuidApplication) {
		return entityManager.createQuery(GET_ALL_BY_IDUSER_AND_UUIDAPPLICATION_JPQL, Role.class)
				.setParameter("idUser", idUser)
				.setParameter("uuidApplication", uuidApplication)
				.getResultList();
	}

	public List<Role> getAllByIdUserAndIdApplication(long idUser, long idApplication){
		return entityManager.createQuery(GET_ALL_BY_IDUSER_AND_IDAPPLICATION_JPQL, Role.class)
				.setParameter("idUser", idUser)
				.setParameter("idApplication", idApplication)
				.getResultList();
	}
	
	public List<Role> getAllByUserName(String userName) {
		return entityManager.createQuery(GET_ALL_BY_USER_NAME_JQPL, Role.class)
				.setParameter("userName", userName)
				.getResultList();
	}

	public long getNextNumberSeq(long idApplication) {
		Long nextNumberSeq = entityManager.createQuery(GET_NEXT_ROLE_NUMBER_SEQUENCE_JQPL, Long.class)
								.setParameter("idApplication", idApplication)
								.getSingleResult();
		
		if(nextNumberSeq == null)
			nextNumberSeq = 0L;
		
		return nextNumberSeq + 1;
	}


}
