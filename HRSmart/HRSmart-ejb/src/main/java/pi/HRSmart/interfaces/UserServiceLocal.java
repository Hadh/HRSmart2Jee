package pi.HRSmart.interfaces;



import javax.ejb.Local;

import pi.HRSmart.persistence.User;

@Local
public interface UserServiceLocal {

	public User get(int id);
	public User getFull(int id);
	
}
