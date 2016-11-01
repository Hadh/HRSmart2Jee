package pi.HRSmart.services;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.ExemptionMechanismException;
import javax.ejb.EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pi.HRSmart.interfaces.UserBuisnessServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.interfaces.UserSkillsServiceLocal;
import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserBuisness;
import pi.HRSmart.utilities.Jwt;
import pi.HRSmart.utilities.SendEmail;
import pi.HRSmart.utilities.SendWelcomeMail;
import pi.HRSmart.utilities.getMD5Hash;

/**
 * Session Bean implementation class UserService
 */
@Stateless

public class UserService implements UserServiceLocal {

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
	public List<User> getAll() {

		Query query = em.createQuery("SELECT u FROM User u");
		return (List<User>) query.getResultList();

	}

	@Override
	public User getFull(int id) {
		User user = em.find(User.class, id);
		user.setUserSkills(userSkillServiceLocal.getByUser(id));
		user.setUserBuisness(userBuisnessServiceLocal.getByUser(id));
		return user;
	}

	@Override
	public void update(User user) {
		em.merge(user);
	}

	@Override
	public void delete(User user) {
		em.remove(em.merge(user));
	}

	@Override
	public String authenticate(String Login, String password) {
User user=null;

			Query query =
					em.createQuery("select new User(e.id,e.firstName,e.lastName,e.login,e.password,e.adresse,e.numTel,e.age) " +
							"from User e where e.login=:login and e.password=:password");
			query.setParameter("login", Login);
			query.setParameter("password", password);
			user=(User)query.getSingleResult();
			return Jwt.SignJWT("user",user);
	}

	@Override
	public boolean checkConnectedUser(User userToVerify) {


		return false;
	}

	@Override
	public User getUserByEmail(String login) {
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

			String beforeHash =  user.getPassword();
			user.setPassword(getMD5Hash.getMD5Hash(beforeHash));
			em.persist(user);
			SendWelcomeMail.SendEmail(user.getLogin(),"Welcome Email","This is a welcome mail!");

			return "done";

	}

	@Override
	public List<User> getUserByBuisness(int idBuisness) {
		List<UserBuisness> result = userBuisnessServiceLocal.getByBuisness(idBuisness);
		List<User> usersByBuisness = new ArrayList<>();
		for (UserBuisness userBuisness : result) {
			usersByBuisness.add(userBuisness.getUser());
		}
		return usersByBuisness;


	}

	//public void inviteUser (User userEmailToAdd,)




}


