package pi.HRSmart.services;

import pi.HRSmart.interfaces.PostulationServiceLocal;
import pi.HRSmart.persistence.Postulation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;



/**
 * Created by BoB on 10/18/2016.
 */
@Stateless
public class PostulationService implements PostulationServiceLocal {

    @PersistenceContext(unitName = "HRSmart-ejb")
    EntityManager em;

    public PostulationService() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void add(Postulation postulation) {
        em.persist(postulation);
    }

    @Override
    public void update(Postulation postulation) {
        em.merge(postulation);
    }

    @Override
    public void delete(Postulation postulation) {
        em.remove(postulation);
    }

    @Override
    public Postulation getPostulation(int id) {
        return em.find(Postulation.class,id);
    }

    @Override
    public List<Postulation> getAllPostulations() {
        Query query =  em.createQuery("select p from Postulation p ");
        return (List<Postulation>) query.getResultList();
    }


}
