package pi.HRSmart.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pi.HRSmart.interfaces.JobOfferServiceLocal;
import pi.HRSmart.interfaces.JobSkillServiceLocal;
import pi.HRSmart.interfaces.SkillServiceLocal;
import pi.HRSmart.persistence.JobOffer;
import pi.HRSmart.persistence.JobSkill;
import pi.HRSmart.persistence.JobSkillPk;
import pi.HRSmart.persistence.Skill;

/**
 * Session Bean implementation class JobSkillService
 *
 * @author Khaled Romdhane
 *
 */

@Stateless
public class JobSkillService implements JobSkillServiceLocal {

	@PersistenceContext(unitName = "HRSmart-ejb")
	EntityManager em;
	
	@EJB(beanName="JobOfferService")
	JobOfferServiceLocal jobService;
	
	@EJB(beanName="SkillService")
	SkillServiceLocal skillService;
	/**
	 * Default constructor.
	 */
	public JobSkillService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(JobSkill jobSkill) {
		em.persist(jobSkill);

	}

	@Override
	public void update(JobSkill jobSkill) {
		em.merge(jobSkill);

	}

	@Override
	public void remove(JobSkill jobSkill) {
		em.remove(em.merge(jobSkill));

	}

	@Override
	public JobSkill get(int idJob,int idSkill) {
		JobSkillPk pk = new JobSkillPk();
		pk.setJobOffer(jobService.get(idJob));
		pk.setSkill(skillService.get(idSkill));
		return em.find(JobSkill.class, pk);
	}

	@Override
	public List<JobSkill> getAll() {
		Query query = em.createQuery("Select r from JobSkill r");
		return (List<JobSkill>) query.getResultList();
	}

	@Override
	public List<JobSkill> getByJob(JobOffer jobOffer) {

		Query query = em.createQuery("Select j from JobSkill j where j.id.jobOffer = " + jobOffer.getId());

		return (List<JobSkill>) query.getResultList();

	}

	@Override
	public List<JobSkill> getBySkill(Skill skill) {

		Query query = em.createQuery("Select j from JobSkill j where j.id.skill = " + skill.getId());

		return (List<JobSkill>) query.getResultList();

	}
	@Override
	public float getSkillAverageSalary(Skill skill)
	{
		List<JobSkill> list = this.getBySkill(skill);
		float salary = 0;
		float count = 0;
		for(JobSkill js : list)
		{
			count++;
			salary +=js.getJobOffer().getSalary();
		}
		return salary/count;
	}
}
