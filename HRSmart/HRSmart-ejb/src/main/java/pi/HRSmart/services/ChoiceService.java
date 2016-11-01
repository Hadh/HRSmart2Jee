package pi.HRSmart.services;

import pi.HRSmart.interfaces.IChoiceServiceLocal;
import pi.HRSmart.persistence.Choice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alaa on 20/10/16.
 */
@Stateless
public class ChoiceService implements IChoiceServiceLocal{

    @PersistenceContext(unitName = "HRSmart-ejb")
    EntityManager em;


    @Override
    public void add(Choice choice) {
        em.persist(choice);
    }

    @Override
    public void update(Choice choice) {
        em.merge(choice);
    }

    @Override
    public void remove(Choice choice) {
        em.remove(em.merge(choice));
    }

    @Override
    public Choice get(int id) {
        return em.find(Choice.class, id);
    }

    @Override
    public List<Choice> all() {
        Query query = em.createQuery("select c from Choice c");
        return (List<Choice>) query.getResultList();
    }

    @Override
    public List<Choice> getMultiple(int[] ids) {
        Query query = em.createQuery(
                "select c from Choice c " +
                "where c.id = :id1 " +
                "or c.id = :id2 " +
                "or c.id = :id3 " +
                "or c.id = :id4 "

        );

        for(int i=0 ; i < ids.length ; i++){
            query.setParameter("id"+i , ids[i]);
        }

        return (ArrayList<Choice>) query.getResultList();
    }
}
