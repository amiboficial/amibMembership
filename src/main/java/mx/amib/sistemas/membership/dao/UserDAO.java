package mx.amib.sistemas.membership.dao;

import java.util.List;

import mx.amib.sistemas.membership.model.User;

public interface UserDAO {
	public long count();
	public User get(long id);
	public List<User> getAll();
	public User save(User user);
	public User update(User user);
	public void delete(long id);
}
