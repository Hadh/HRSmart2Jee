package pi.HRSmart.interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import pi.HRSmart.persistence.Rewards;
import pi.HRSmart.persistence.Skill;

@Local
public interface RewardServiceLocal {
	
	 void add(Rewards reward);
	 void update(Rewards reward);
	 void remove(Rewards reward);
	 Rewards get(int id);
	 List<Rewards> getAll();

	public List<Rewards> getByJob(int jobId);

}