package pi.HRSmart.services;

import javax.ejb.EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

	@Override
	public User authenticate(String Login, String password) {
		try {
			TypedQuery<User> query =
					em.createQuery("select e from User e where e.login=:login and e.password=:password", User.class);

			query.setParameter("login", "login");
			query.setParameter("password", "password");
			return query.getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean checkConnectedUser(User userToVerify) {
		if (userToVerify == authenticate(userToVerify.getLogin(), userToVerify.getPassword()))
			return true;
		return false;
	}

	@Override
	public User getUserByLogin(String login) {
		try {
			TypedQuery<User> Myquery =
					em.createQuery("select e from User e where e.login=:login", User.class);

			Myquery.setParameter("login", "login");
			return Myquery.getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String addUser(User user) {
		try {
			em.persist(user);
			return "Log: Add Done !";
		}
		catch(Exception e) {
			return "Log : Add Failed";
		}
	}


}

