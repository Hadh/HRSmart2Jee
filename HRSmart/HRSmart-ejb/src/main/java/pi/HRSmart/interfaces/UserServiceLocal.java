package pi.HRSmart.interfaces;



import java.util.List;

import javax.ejb.Local;

import pi.HRSmart.persistence.User;

@Local
public interface UserServiceLocal {

	public User get(int id);
	public User getFull(int id);
	public void update(User user);
	public void delete(User user);
	public String authenticate(String Login,String password);
	public boolean checkConnectedUser(User userToVerify);
	public User getUserByEmail(String login);
	public String addUser(User user);
	public List<User> getUserByBuisness(int idBuisness);
	public List<User> getAll() ;
	public User TokenToUser(String token);
}
