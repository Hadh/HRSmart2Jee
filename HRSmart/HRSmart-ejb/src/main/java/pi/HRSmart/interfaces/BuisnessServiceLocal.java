package pi.HRSmart.interfaces;

import java.util.List;
import javax.ejb.Local;
import pi.HRSmart.persistence.*;

@Local
public interface BuisnessServiceLocal {

	Buisness add(Buisness Buisness);
  	void update(Buisness Buisness);
	void remove(Buisness Buisness);
	Buisness get(int id);
  	List<Buisness> getAll();
  	List<Buisness> getAllBuisness();

}
