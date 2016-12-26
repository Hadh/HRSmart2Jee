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
	public UserBuisness get(int idUser,int idBuisness) {

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
		Query query = em.createQuery("select ub from UserBuisness ub where ub.id.user=" +id);
		return (List<UserBuisness>) query.getResultList();
	}

	@Override
	public List<UserBuisness> getByBuisness(int id) {
		Query query = em.createQuery("select ub from UserBuisness ub where ub.id.buisness=" + id);
		return (List<UserBuisness>) query.getResultList();
	}

	@Override
	public String getRoleByUser(int idUser, int idBusiness) {
		return this.get(idUser,idBusiness).getRole();
	}

	@Override
	public UserBuisness getUserBusinessByUser(User user){ // returns userbusiness that has idUser of that user (HR)
		/*List<UserBuisness> listB= getByUser(user.getId());
		List<UserBuisness> finalList=new ArrayList<>();
		for(UserBuisness ub : listB){
			if(getRoleByUser(user.getId(),ub.getId().getBuisness().getId())=="HR"){ // is gonna return on BS at the end
				finalList.add(ub);
			}
		}
		UserBuisness b = finalList.get(0);
		return b;*/
		return null;
	}

	public String SendMail(User user,String to){
		UserBuisness uBs = getUserBusinessByUser(user); // userbusiness that has idUser of that user (HR)
		int idBs = uBs.getId().getBuisness().getId(); // Id of the business in that user business
		SendEmail.SendEmail(to,"hello","hello"+idBs); //sending the id of the business with the email
		return "here";
	}


}
