package pi.HRSmart.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pi.HRSmart.interfaces.AddressServiceLocal;
import pi.HRSmart.interfaces.BuisnessServiceLocal;
import pi.HRSmart.interfaces.BuisnessServiceRemote;
import pi.HRSmart.interfaces.JobOfferServiceLocal;
import pi.HRSmart.interfaces.StageServiceLocal;
import pi.HRSmart.interfaces.UserBuisnessServiceLocal;
import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.Stage;

@Stateless
public class BuisnessService implements BuisnessServiceLocal, BuisnessServiceRemote {
	
	@EJB(beanName="StageService")
	StageServiceLocal serviceStage;
	
	@EJB(beanName="AddressService")
	AddressServiceLocal serviceAddress;
	
	@EJB(beanName="JobOfferService")
	JobOfferServiceLocal serviceJobOffer;
	
	@EJB(beanName="UserBuisnessService")
	UserBuisnessServiceLocal serviceUserBuisness;
	
	@PersistenceContext(unitName = "HRSmart-ejb")
	EntityManager em;

	@Override
	public Buisness add(Buisness Buisness) {
		 em.persist(Buisness);
		 em.refresh(Buisness);
		 return Buisness;
	}

	@Override
	public void update(Buisness Buisness) {
		em.merge(Buisness);
		
	}

	@Override
	public void remove(Buisness Buisness) {
		em.remove(em.merge(Buisness));
		
	}

	@Override
	public Buisness get(int id) {
		Buisness buisness = em.find(Buisness.class, id);
		buisness.setAddress(serviceAddress.getAllByBuisness(id));
		buisness.setStages(serviceStage.getAllByBuisness(id));
		buisness.setJobOffers(serviceJobOffer.getAllByBuisness(id));
		buisness.setUserBuisness(serviceUserBuisness.getByBuisness(id));
		return buisness;
	}

	@Override
	public List<Buisness> getAll() {
		
		Query query = em.createQuery("select b from Buisness b");
		return (List<Buisness>) query.getResultList();
	}
	
	@Override
	public List<Buisness> getAllBuisness() {
		Query query = em.createQuery("select b from Buisness b");
		List<Buisness> ls = (List<Buisness>) query.getResultList();
		for (Buisness buisness : ls) {
			buisness.setStages(serviceStage.getAllByBuisness(buisness.getId()));
			buisness.setAddress(serviceAddress.getAllByBuisness(buisness.getId()));
			buisness.setJobOffers(serviceJobOffer.getAllByBuisness(buisness.getId()));
			buisness.setUserBuisness(serviceUserBuisness.getByBuisness(buisness.getId()));
		}
		return ls;
	}

}
