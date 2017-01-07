package pi.HRSmart.interfaces;

import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.Postulation;
import pi.HRSmart.persistence.Skill;
import pi.HRSmart.persistence.User;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

/**
 * Created by BoB on 10/18/2016.
 */
@Local
public interface ProfilingServiceLocal {


   Map<User,Integer> Profile(int id);
   Map<User,Integer> Score(Map<User,Integer> scored , List<Skill> skillset);
}
