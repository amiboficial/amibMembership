package mx.amib.sistemas.membership.dao;

import java.util.List;

import mx.amib.sistemas.membership.model.User;

public interface UserDAO {
	
	//métodos básicos
	public long count();
	public User get(long id);
	public List<User> getAll();
	public User save(User user);
	public User update(User user);
	public void delete(long id);
	
	//métodos complementarios
	public User getByUserName(String userName);
	public List<User> findAll(int max, int offset, String sort, String order);
	public long countFindAll();
	public List<User> findAllByUserNameLike(int max, int offset, String sort, String order, String userName);
	public long countFindAllByUserNameLike(String userName);
}
