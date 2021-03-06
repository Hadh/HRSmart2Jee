package pi.HRSmart.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pi.HRSmart.interfaces.StageServiceLocal;
import pi.HRSmart.interfaces.StageServiceRemote;
import pi.HRSmart.persistence.Buisness;
import pi.HRSmart.persistence.Rewards;
import pi.HRSmart.persistence.Stage;

@Stateless
public class StageService implements StageServiceLocal, StageServiceRemote{

	@PersistenceContext(unitName = "HRSmart-ejb")
	EntityManager em;
	@Override
	public void add(Stage Stage) {
		em.persist(Stage);
		
	}

	@Override
	public void update(Stage Stage) {
		em.merge(Stage);
		
	}

	@Override
	public void remove(Stage Stage) {
		em.remove(em.merge(Stage));
		
	}

	@Override
	public Stage get(int id) {
		return em.find(Stage.class, id);
	}

	@Override
	public List<Stage> getAll() {
		Query query = em.createQuery("select s from Stage s");
		return (List<Stage>) query.getResultList();
	}
	
	@Override
	public List<Stage> getAllByBuisness(int idBuisness){
		Query query = em.createQuery("Select s from Stage s where s.buisness = " + idBuisness);
		return (List<Stage>) query.getResultList();
	}

	@Override
	public void setBuisnessToStage(Stage stage, Buisness buisness) {
		if( get(stage.getId())== null){
			add(stage);
		}
		em.createQuery("UPDATE Stage s SET s.buisness="
		+buisness.getId()+" where s.id="+stage.getId()).executeUpdate();
	}

}
