package pi.HRSmart.services;

import javax.ejb.EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pi.HRSmart.interfaces.UserBuisnessServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.interfaces.UserSkillsServiceLocal;
import pi.HRSmart.persistence.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless

public class UserService implements UserServiceLocal {

	/**
	 * @author yesmine
	 *
	 */
	@PersistenceContext(unitName = "HRSmart-ejb")
	EntityManager em;
	@EJB(beanName = "UserSkillsService")
	UserSkillsServiceLocal userSkillServiceLocal;

	@EJB(beanName = "UserBuisnessService")
	UserBuisnessServiceLocal userBuisnessServiceLocal;

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User get(int id) {

		return em.find(User.class, id);
	}

	@Override
	public User getFull(int id) {
		User user = em.find(User.class, id);
		user.setUserSkills(userSkillServiceLocal.getByUser(id));
		user.setUserBuisness(userBuisnessServiceLocal.getByUser(user));
		return user;
	}

}
