package pi.HRSmart.interfaces;

import java.util.List;
import javax.ejb.Remote;
import pi.HRSmart.persistence.Buisness;

@Remote
public interface BuisnessServiceRemote {
	Buisness add(Buisness Buisness);
  	void update(Buisness Buisness);
	void remove(Buisness Buisness);
	Buisness get(int id);
  	List<Buisness> getAll();
  	List<Buisness> getAllBuisness();
}
