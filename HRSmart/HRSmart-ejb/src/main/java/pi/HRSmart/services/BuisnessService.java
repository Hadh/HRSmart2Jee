package pi.HRSmart.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pi.HRSmart.interfaces.AddressServiceLocal;
import pi.HRSmart.interfaces.BuisnessServiceLocal;
import pi.HRSmart.interfaces.JobOfferServiceLocal;
import pi.HRSmart.interfaces.StageServiceLocal;
import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.Stage;

@Stateless
public class BuisnessService implements BuisnessServiceLocal {
	
	@EJB(beanName="StageService")
	StageServiceLocal serviceStage;
	
	@EJB(beanName="AddressService")
	AddressServiceLocal serviceAddress;
	
	@EJB(beanName="JobOfferService")
	JobOfferServiceLocal serviceJobOffer;
	
	@PersistenceContext(unitName = "HRSmart-ejb")
	EntityManager em;

	@Override
	public void add(Buisness Buisness) {
		em.persist(Buisness);
		
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
		return em.find(Buisness.class, id);
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
		}
		return ls;
	}

	@Override
	public Buisness getById(int id) {
		return em.find(Buisness.class, id);
	}

}
