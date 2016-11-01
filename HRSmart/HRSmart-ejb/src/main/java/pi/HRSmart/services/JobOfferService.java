package pi.HRSmart.services;

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
	}

	@Override
	public void update(JobOffer jobOffer) {
		em.merge(jobOffer);
		
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
	public JobOffer getFull(int id) {
			
		JobOffer jo = em.find(JobOffer.class, id);
		List<Rewards> rs = rewardService.getByJob(id);
		List<JobSkill> js= jobSkillService.getByJob(jo);
		jo.setJobSkills(js);
		jo.setRewards(rs);
		return jo;
		
	}

	@Override
	public float compatibilityJobUser(int user, int job) {
		JobOffer j = this.getFull(job);
		User u = userService.getFull(user);
		
		int counter=0;
		int acheived = 0;
		
		for(JobSkill js : j.getJobSkills())
		{
			counter++;
			for(UserSkill us : u.getUserSkills())
			{
				if(js.getSkill().getId() == us.getSkill().getId()
						&& js.getLevel() <= us.getLevel())
				{
					acheived++;
				}
			}
		}
		return acheived/counter;
	}
	

	@Override
	public List<JobOffer> getAllByBuisness(int idBuisness){
		Query query = em.createQuery("Select j from JobOffer j where j.buisness = " + idBuisness);
		return (List<JobOffer>) query.getResultList();
	}

	private String jobOfferUrl="localhost:18080/rest/job/";
	public String generateEmployeeJobLink(User user, JobOffer job){
		String generatedLink =this.jobOfferUrl+job.getId()+"?userId="+user.getId();
		return generatedLink;
	}

	
	

	

}
