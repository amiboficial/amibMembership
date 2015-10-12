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

	public User getByUserName(String userName) {
		return entityManager.createQuery("select u from User u where u.userName = :userName", User.class)
				.setParameter("userName", userName)
				.getSingleResult();
	}
	
	public List<User> findAll(int max, int offset, String sort, String order) {
		
		List<User> resultList = null;
		
		max = this.filterMaxParam(max);
		offset = this.filterOffsetParam(offset);
		sort = this.filterSortParam(sort);
		order = this.filterOrderParam(order);
		
		resultList = entityManager.createQuery(String.format("select u from User u order by u.%s %s", sort, order), User.class)
				.setMaxResults(max).setFirstResult(offset)
				.getResultList();
		
		return resultList;
	}

	public long countFindAll() {
		return entityManager.createQuery("select count(u) from User u", Long.class).getSingleResult().longValue();
	}

	public List<User> findAllByUserNameLike(int max, int offset, String sort,
			String order, String userName) {
		
		List<User> resultList = null;
		
		max = this.filterMaxParam(max);
		offset = this.filterOffsetParam(offset);
		sort = this.filterSortParam(sort);
		order = this.filterOrderParam(order);
		
		userName = this.filterUserNameParam(userName);
		
		resultList = entityManager.createQuery(String.format("select u from User u where u.userName like :userNameLike order by u.%s %s", sort, order), User.class)
				.setParameter("userNameLike", userName + "%")
				.setMaxResults(max).setFirstResult(offset)
				.getResultList();
		
		return resultList;
	}

	public long countFindAllByUserNameLike(String userName) {
		userName = this.filterUserNameParam(userName);
		
		return entityManager.createQuery("select count(u) from User u where u.userName like :userNameLike", Long.class)
				.setParameter("userNameLike", userName + "%")
				.getSingleResult().longValue();
	}
	
	private int filterMaxParam(int max){
		if(max <= 0){
			max = 10;
		}
		return max;
	}
	private int filterOffsetParam(int offset){
		if(offset < 0){
			offset = 0;
		}
		return offset;
	}
	private String filterSortParam(String sort){
		if(sort != null){
			sort = sort.toLowerCase().trim();
			try{
				User.class.getDeclaredField(sort);
			}
			catch(NoSuchFieldException nsfe){
				sort = "id";
			}
		}
		else{
			sort = "id";
		}
		return sort;
	}
	private String filterOrderParam(String order){
		if(order != null){
			order = order.toLowerCase().trim();
			if(order != "asc" && order != "desc"){
				order = "asc";
			}
		}
		else{
			order = "asc";
		}
		return order;
	}
	
	private String filterUserNameParam(String userName){
		if(userName != null){
			userName = "";
		}
		return userName;
	}

	

}
