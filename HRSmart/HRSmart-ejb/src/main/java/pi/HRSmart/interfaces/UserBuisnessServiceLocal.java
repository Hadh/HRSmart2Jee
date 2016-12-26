package pi.HRSmart.interfaces;

import java.util.List;

import javax.ejb.Local;

import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserBuisness;

@Local
public interface UserBuisnessServiceLocal {

	public void add(UserBuisness userBuisness);

	public void update(UserBuisness userBuisness);

	public void remove(UserBuisness userBuisness);

	public UserBuisness get(int idUser, int idBusiness);

	public List<UserBuisness> getAll();

	public List<UserBuisness> getByUser(int id);

	public List<UserBuisness> getByBuisness(int id);

	public String getRoleByUser(int idUser, int idBusiness);

	public UserBuisness getUserBusinessByUser(User user);

	public User getBestEmployee(int idBuisness);

}
