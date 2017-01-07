package pi.HRSmart.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pi.HRSmart.interfaces.BuisnessServiceLocal;
import pi.HRSmart.interfaces.UserBuisnessServiceLocal;
import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.User;
import pi.HRSmart.persistence.UserBuisness;
import pi.HRSmart.persistence.UserBuisnessPK;
import pi.HRSmart.persistence.UserSkill;
import pi.HRSmart.utilities.SendEmail;

/**
 * @author yesmine
 *
 */
@Stateless

public class UserBuisnessService implements UserBuisnessServiceLocal {

	@EJB(beanName = "UserService")
	UserServiceLocal userServiceLocal;
	@EJB(beanName = "BuisnessService")
	BuisnessServiceLocal buisnessServiceLocal;
	/**
	 * Default constructor.
	 */

	@PersistenceContext(unitName = "HRSmart-ejb")
	EntityManager em;

	public UserBuisnessService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(UserBuisness userBuisness) {
		em.persist(userBuisness);

	}

	@Override
	public void update(UserBuisness userBuisness) {
		em.merge(userBuisness);
	}

	@Override
	public void remove(UserBuisness userBuisness) {
		em.remove(em.merge(userBuisness));
	}

	@Override
	public UserBuisness get(int idUser, int idBuisness) {

		UserBuisnessPK pk = new UserBuisnessPK();
		pk.setBuisness(buisnessServiceLocal.get(idBuisness));
		pk.setUser(userServiceLocal.get(idUser));
		return em.find(UserBuisness.class, pk);
	}

	@Override
	public List<UserBuisness> getAll() {
		Query query = em.createQuery("select ub from UserBuisness ub");
		return (List<UserBuisness>) query.getResultList();
	}

	@Override
	public List<UserBuisness> getByUser(int id) {
		Query query = em.createQuery("select ub from UserBuisness ub where ub.id.user=" + id);
		return (List<UserBuisness>) query.getResultList();
	}

	@Override
	public List<UserBuisness> getByBuisness(int id) {
		Query query = em.createQuery("select ub from UserBuisness ub where ub.id.buisness=" + id);
		return (List<UserBuisness>) query.getResultList();
	}

	@Override
	public String getRoleByUser(int idUser, int idBusiness) {
		return this.get(idUser, idBusiness).getRole();
	}

	@Override
	public UserBuisness getUserBusinessByUser(User user){ // returns userbusiness that has idUser of that user (HR)
		/*List<UserBuisness> listB= getByUser(user.getId());
		List<UserBuisness> finalList=new ArrayList<>();
		for(UserBuisness ub : listB){
			if(getRoleByUser(user.getId(),ub.getId().getBuisness().getId())=="HR"){ // is gonna return on BS at the end

	public UserBuisness getUserBusinessByUser(User user) { // returns
															// userbusiness that
															// has idUser of
															// that user (HR)
		List<UserBuisness> listB = getByUser(user.getId());
		List<UserBuisness> finalList = new ArrayList<>();
		for (UserBuisness ub : listB) {
			if (getRoleByUser(user.getId(), ub.getId().getBuisness().getId()) == "HR") { // is
																							// gonna
																							// return
																							// on
																							// BS
																							// at
																							// the
																							// end
				finalList.add(ub);
			}
		}
		UserBuisness b = finalList.get(0);
		return b;*/
		return null;
	}

	public String SendMail(User user, String to) {
		UserBuisness uBs = getUserBusinessByUser(user); // userbusiness that has
														// idUser of that user
														// (HR)
		int idBs = uBs.getId().getBuisness().getId(); // Id of the business in
														// that user business
		SendEmail.SendEmail(to, "hello", "hello" + idBs); // sending the id of
															// the business with
															// the email
		return "here";
	}

	///// Retourner l'employe le plus competent
	@Override
	public User getBestEmployee(int idBuisness) {
		Query query = em
				.createQuery("select u from UserBuisness ub join ub.id.user u where ub.id.buisness=" + idBuisness);
		List<User> users = (List<User>) query.getResultList();
		for (User u : users) {
			u = userServiceLocal.getFull(u.getId());
		}
		boolean verif = false;
		while (verif == false) {
			verif = true;
			for (int i = 0; i < users.size() - 1; i++) {
				System.out.println("i."+i);
				int counter = 0;
				for (UserSkill us1 : users.get(i ).getUserSkills()) {
					System.out.println("userskill"+us1.getLevel());
					boolean exist = false;
					for (UserSkill us2 : users.get(i + 1).getUserSkills()) {
						if (us1.getSkill().getId() == us2.getSkill().getId()) {
							if (us1.getLevel() < us2.getLevel()) {
								counter--;
							}
							else if (us1.getLevel() > us2.getLevel()) 
							{
								counter++;
							}
							exist = true;
						}
					}
					if (!exist) {
						counter++;
					}
				}
				if (counter < 0) {
					User aux = users.get(i );
					users.set(i, users.get(i + 1));
					users.set(i  + 1, aux);
					verif = false;
				}
			}
		}
		return users.get(0);
	}

}
