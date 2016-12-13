package pi.HRSmart.interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.User;

/**
 * @author Khaled Romdhane
 *
 */

@Local
public interface JobOfferServiceLocal {
	void add(JobOffer jobOffer);

	JobOffer update(JobOffer jobOffer);

	void remove(JobOffer jobOffer);

	JobOffer get(int id);

	JobOffer getFull(int id);

	List<JobOffer> getAll();

	List<JobOffer> getAllFull();

	float compatibilityJobUser(User user, JobOffer job);

	List<JobOffer> getAllByBuisness(int id);

	float getJobSalary(JobOffer job);

	JobOffer refresh(JobOffer job);

	void flush();

	void addFull(JobOffer jobOffer);

}
