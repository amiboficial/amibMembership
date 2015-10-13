package mx.amib.sistemas.membership.service;

import java.util.List;

import mx.amib.sistemas.membership.model.Path;
import mx.amib.sistemas.membership.model.Role;
import mx.amib.sistemas.membership.model.User;
import mx.amib.sistemas.membership.service.exception.WrongPasswordAlgorithm;

public interface UserService {
	public long count();
	public User get(long id);
	public List<User> getAll();
	public User save(User user) throws WrongPasswordAlgorithm;
	public User update(User user);
	public void delete(long id);

	public User getByUserName(String userName);
	public List<User> findAll(int max, int offset, String sort, String order);
	public long countFindAll();
	public List<User> findAllByUserNameLike(int max, int offset, String sort, String order, String userName);
	public long countFindAllByUserNameLike(String userName);
	
	public List<Role> getRoles(long id, long idApplication);
	public List<Path> getRestrictedPaths(long id, long idApplication);
}
