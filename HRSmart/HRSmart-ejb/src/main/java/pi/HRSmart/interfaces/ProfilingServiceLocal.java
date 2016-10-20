package pi.HRSmart.interfaces;

import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.Postulation;

import javax.ejb.Local;

/**
 * Created by BoB on 10/18/2016.
 */
@Local
public interface ProfilingServiceLocal {


   void Profile(JobOffer jobOffer);
}
