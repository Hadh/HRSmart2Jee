package pi.HRSmart.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pi.HRSmart.interfaces.JobSkillServiceLocal;
import pi.HRSmart.persistence.JobSkill;
import pi.HRSmart.persistence.Rewards;

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
	public JobSkill get(int id) {
		return em.find(JobSkill.class, id);
	}

	@Override
	public List<JobSkill> getAll() {
		Query query = em.createQuery("Select r from JobSkill r");
		return (List<JobSkill>) query.getResultList();
	}

}
