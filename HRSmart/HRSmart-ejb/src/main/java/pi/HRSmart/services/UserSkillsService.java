package pi.HRSmart.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pi.HRSmart.interfaces.RewardServiceLocal;
import pi.HRSmart.interfaces.SkillServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.interfaces.UserSkillsServiceLocal;
import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserSkill;
import pi.HRSmart.persistence.UserSkillPk;

/**
 * @author yesmine
 *
 */
@Stateless


public class UserSkillsService implements UserSkillsServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName = "HRSmart-ejb")
	EntityManager em;
	
	@EJB(beanName = "UserService") 
	UserServiceLocal userService;
	
	@EJB(beanName = "SkillService") 
	SkillServiceLocal skillService;
	
    public UserSkillsService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void add(UserSkill userSkill) {
		em.persist(userSkill);
		
	}

	@Override
	public void update(UserSkill userSkill) {
		em.merge(userSkill);
		
	}

	@Override
	public void remove(UserSkill userSkill) {
		em.remove(em.merge(userSkill));
		
	}

	@Override
	public UserSkill get(int user,int skill) {
		UserSkillPk pk = new UserSkillPk();
		userService.get(user);
		skillService.get(skill);
		return em.find(UserSkill.class, pk);
	}

	@Override
	public List<UserSkill> getAll() {
		Query query = em.createQuery("select us from UserSkill us");
		List<UserSkill>list = (List<UserSkill>) query.getResultList();
		for(UserSkill us : list){
			us.setSkill(skillService.get(us.getSkill().getId()));
		}
		return list;
	}

	@Override
	public List<UserSkill> getByUser(int id) {
		Query query = em.createQuery("SELECT us FROM UserSkill us join us.id.user u where u=" + id);
		List<UserSkill>list = (List<UserSkill>) query.getResultList();
		for(UserSkill us : list){
			us.setSkill(skillService.get(us.getSkill().getId()));
		}
		return list;
	}

	
}
