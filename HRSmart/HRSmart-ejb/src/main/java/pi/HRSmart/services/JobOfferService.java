package pi.HRSmart.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pi.HRSmart.interfaces.JobOfferServiceLocal;
import pi.HRSmart.interfaces.JobSkillServiceLocal;
import pi.HRSmart.interfaces.RewardServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.*;

/**
 * Session Bean implementation class JobOfferService
 *
 * @author Khaled Romdhane
 *
 */

@Stateless
public class JobOfferService implements JobOfferServiceLocal {

	@PersistenceContext(unitName = "HRSmart-ejb")
	EntityManager em;

	@EJB(beanName = "RewardService")
	RewardServiceLocal rewardService;

	@EJB(beanName = "UserService")
	UserServiceLocal userService;

	@EJB(beanName = "JobSkillService")
	JobSkillServiceLocal jobSkillService;

	/**
	 * Default constructor.
	 */
	public JobOfferService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(JobOffer jobOffer) { 
		em.persist(jobOffer);
		em.refresh(jobOffer);
	}

	@Override
	public void addFull(JobOffer jobOffer) {
		List<Rewards> listR = jobOffer.getRewards();
		List<JobSkill> listJS = jobOffer.getJobSkills();
		jobOffer.setCreationDate(new Date());
		em.persist(jobOffer);
		em.flush();
		em.refresh(jobOffer);
		if (listR != null) {
			for (Rewards r : listR) {
				RewardsPk pk = new RewardsPk();
				pk.setStage(r.getStage());
				pk.setJobOffer(jobOffer);
				r.setId(pk);
				em.persist(r);
			}
		}
		if (listJS != null) {
			for (JobSkill js : listJS) {
				JobSkillPk pk = new JobSkillPk();
				pk.setSkill(js.getSkill());
				pk.setJobOffer(jobOffer);
				js.setId(pk);
				em.persist(js);
			}
		}
	}

	@Override
	public JobOffer update(JobOffer jobOffer) {
		List<Rewards> listR = jobOffer.getRewards();
		List<JobSkill> listJS = jobOffer.getJobSkills();
		jobOffer.setCreationDate(new Date());
		em.merge(jobOffer);
		if (listR != null) {
			for (Rewards r : listR) {
				RewardsPk pk = new RewardsPk();
				pk.setStage(r.getStage());
				pk.setJobOffer(jobOffer);
				r.setId(pk);
				em.merge(r);
			}
		}
		if (listJS != null) {
			for (JobSkill js : listJS) {
				JobSkillPk pk = new JobSkillPk();
				pk.setSkill(js.getSkill());
				pk.setJobOffer(jobOffer);
				js.setId(pk);
				em.merge(js);
			}
		}
		return jobOffer;

	}

	@Override
	public void flush() {
		em.flush();
	}

	@Override
	public void remove(JobOffer jobOffer) {
		em.remove(em.merge(jobOffer));

	}

	@Override
	public JobOffer get(int id) {

		return em.find(JobOffer.class, id);
	}

	@Override
	public List<JobOffer> getAll() {

		Query query = em.createQuery("SELECT j FROM JobOffer j");
		return (List<JobOffer>) query.getResultList();

	}

	@Override
	public List<JobOffer> getAllFull() {

		Query query = em.createQuery("SELECT j FROM JobOffer j");
		List<JobOffer> list = (List<JobOffer>) query.getResultList();
		for (JobOffer j : list) {

			j = this.getFull(j.getId());
		}
		return list;

	}

	@Override
	public JobOffer getFull(int id) {

		JobOffer jo = em.find(JobOffer.class, id);
		List<Rewards> rs = rewardService.getByJob(id);
		List<JobSkill> js = jobSkillService.getByJob(jo);
		jo.setJobSkills(js);
		jo.setRewards(rs);
		return jo;

	}

	@Override
	public float compatibilityJobUser(User u, JobOffer j) {
		float counter = 0;
		float acheived = 0;

		for (JobSkill js : j.getJobSkills()) {
			counter++;
			for (UserSkill us : u.getUserSkills()) {
				if (js.getSkill().getId() == us.getSkill().getId()) {
					if (js.getLevel() <= us.getLevel()) {
						acheived++;
						break;
					}
				}
			}
			for (Certificat c : js.getCertificats()) {
				counter++;
				for (UserSkill us : u.getUserSkills()) {
					for (Certificat cu : us.getCertificats()) {
						if (cu.getId() == c.getId()) {
							acheived++;
							break;
						}
					}
				}
			}
		}
		if (counter != 0) {
			return acheived / counter;
		} else {
			return 1;
		}
	}

	@Override
	public List<JobOffer> getAllByBuisness(int idBuisness) {
		Query query = em.createQuery("Select j from JobOffer j where j.buisness = " + idBuisness);
		return (List<JobOffer>) query.getResultList();
	}

	private String jobOfferUrl = "localhost:18080/rest/job/";

	public String generateEmployeeJobLink(User user, JobOffer job) {
		String generatedLink = this.jobOfferUrl + job.getId() + "?userId=" + user.getId();
		return generatedLink;
	}

	@Override
	public float getJobSalary(JobOffer job) {
		float count = 0;
		float sum = 0;
		for (JobSkill js : job.getJobSkills()) {
			count++;
			sum += jobSkillService.getSkillAverageSalary(js.getSkill());
		}

		if (count != 0) {
			return sum / count;
		}
		return 0;
	}

	@Override
	public JobOffer refresh(JobOffer job) {
		em.merge(job);
		return job;
	}
}
