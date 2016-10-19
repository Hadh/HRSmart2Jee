package pi.HRSmart.interfaces;



import javax.ejb.Local;

import pi.HRSmart.persistence.User;

@Local
public interface UserServiceLocal {

	public User get(int id);
	public User getFull(int id);
	public User authenticate(String Login,String password);
	public boolean checkConnectedUser(User userToVerify);
	public User getUserByLogin(String login);
	public String addUser(User user);
	
}
