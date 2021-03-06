package pi.HRSmart.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pi.HRSmart.interfaces.AddressServiceLocal;
import pi.HRSmart.interfaces.AddressServiceRemote;
import pi.HRSmart.persistence.Address;
import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.Stage;

@Stateless
public class AddressService implements AddressServiceLocal, AddressServiceRemote{
	
	@PersistenceContext(unitName = "HRSmart-ejb")
	EntityManager em;

	@Override
	public void add(Address address) {
		em.persist(address);
		
	}

	@Override
	public void update(Address address) {
		em.merge(address);
		
	}

	@Override
	public void remove(Address address) {
		em.remove(em.merge(address));
		
	}

	@Override
	public Address get(int id) {
		return em.find(Address.class, id);
	}

	@Override
	public List<Address> getAll() {
		Query query = em.createQuery("select a from Address a");
		return (List<Address>) query.getResultList();
	}

	@Override
	public List<Address> getAllByBuisness(int idBuisness) {
		//Query query = em.createQuery("Select a from Address a where a.buisness = " + idBuisness);
		Query query = em.createQuery("Select a from Address a JOIN a.buisness c where c.id = " + idBuisness);
		return (List<Address>) query.getResultList();
	}
	
	@Override
	public void setBuisnessToAddress(Address address, Buisness buisness) {
		if( get(address.getId())== null){
			add(address);
		}
		em.createQuery("UPDATE Address a SET a.buisness="
		+buisness.getId()+" where a.id="+address.getId()).executeUpdate();
	}

}
