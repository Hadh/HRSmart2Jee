package pi.HRSmart.interfaces;

import java.util.List;
import javax.ejb.Local;
import pi.HRSmart.persistence.Buisness;

@Local
public interface BuisnessServiceLocal {

	void add(Buisness Buisness);
  	void update(Buisness Buisness);
	void remove(Buisness Buisness);
	Buisness getById(int id);
	Buisness get(int id);
  	List<Buisness> getAll();
  	List<Buisness> getAllBuisness();

}
