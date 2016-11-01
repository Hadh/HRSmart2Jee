package pi.HRSmart.interfaces;

import java.util.List;
import javax.ejb.Remote;

import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.Stage;

@Remote
public interface StageServiceRemote {
	
	void add(Stage Stage);
  	void update(Stage Stage);
	void remove(Stage Stage);
	Stage get(int id);
  	List<Stage> getAll();
  	List<Stage> getAllByBuisness(int idBuisness);
  	void setBuisnessToStage(Stage stage, Buisness buisness);
}
